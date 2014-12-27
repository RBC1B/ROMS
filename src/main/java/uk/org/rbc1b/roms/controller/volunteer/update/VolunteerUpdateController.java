/*
 * The MIT License
 *
 * Copyright 2014 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.org.rbc1b.roms.controller.volunteer.update;

import java.io.IOException;
import java.sql.Date;
import java.util.Properties;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.org.rbc1b.roms.controller.ForbiddenRequestException;
import uk.org.rbc1b.roms.controller.ResourceNotFoundException;
import uk.org.rbc1b.roms.controller.UnprocessableEntityRequestException;
import uk.org.rbc1b.roms.controller.common.DataConverterUtil;
import uk.org.rbc1b.roms.controller.common.HashGenerator;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.email.EmailDao;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import freemarker.template.TemplateException;

/**
 * Controller for checking and accepting requests to update contact details.
 *
 */
@Controller
@RequestMapping("/volunteer-contact")
public class VolunteerUpdateController {
    private static final String SECURITY_SALT = "security.salt";
    private static final String DATETIMEFORMAT = "yyyyMMddHHmm";
    private static final long MAXTIME = 86400000;
    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private EmailDao emailDao;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private VolunteerUpdateEmailGenerator volunteerUpdateEmailGenerator;
    @Resource(name = "edificeProperty")
    private Properties edificeProperty;

    /**
     * Accepts and checks requests for updating contact when the volunteer puts
     * in his RVC ID and date of birth to trigger an email. There is no security
     * checks around this as this is initial request by the volunteer.
     *
     * @param form the user form
     * @param request the http request
     * @throws TemplateException on failure to render the email
     * @throws IOException  on failure to render the email
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void acceptRequest(@Valid VolunteerUpdateRequestForm form, HttpServletRequest request) throws IOException,
            TemplateException {
        Volunteer volunteer = volunteerDao.findVolunteer(form.getPersonId(), null);
        if (volunteer == null) {
            throw new ResourceNotFoundException("No volunteer #" + form.getPersonId());
        }

        assertVolunteerInformationIsValid(volunteer);

        Date birthDate = DataConverterUtil.toSqlDate(form.getBirthDate());

        if (volunteer.getPerson().getBirthDate().compareTo(birthDate) != 0) {
            throw new ForbiddenRequestException("Birth date does not match");
        }

        Email email = volunteerUpdateEmailGenerator.generateVolunteerUpdateRequestEmail(volunteer);
        emailDao.save(email);
    }

    private void assertVolunteerInformationIsValid(Volunteer volunteer) {
        if (volunteer.getPerson().getBirthDate() == null) {
            throw new UnprocessableEntityRequestException("Volunteer #" + volunteer.getPersonId()
                    + " birth date is not set");
        }
        if (volunteer.getPerson().getEmail() == null) {
            throw new UnprocessableEntityRequestException("Volunteer #" + volunteer.getPersonId() + " email is not set");
        }
        if (!EmailValidator.getInstance().isValid(volunteer.getPerson().getEmail())) {
            throw new UnprocessableEntityRequestException("Volunteer #" + volunteer.getPersonId()
                    + " email is not valid");
        }
    }

    /**
     * Handles the request to update contact. There is no security checks around
     * this because it is done be the unauthenticated user. Security is added by
     * hashed urls.
     *
     * @param volunteerId the volunteer id
     * @param datetime the datetime of the email
     * @param hash the hash
     * @param model the mvc model
     * @return contact update form
     */
    @RequestMapping(value = "/{volunteerId}/{datetime}/{hash}", method = RequestMethod.GET)
    public String showVolunteerContact(@PathVariable Integer volunteerId, @PathVariable String datetime,
            @PathVariable String hash, ModelMap model) {
        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, null);
        if (volunteer == null) {
            return "volunteer-contact/volunteer-incorrect-form";
        }
        if (checkWithinTime(datetime) && checkHash(volunteer, datetime, hash)) {
            VolunteerUpdateForm form = new VolunteerUpdateForm();
            form.setStreet(volunteer.getPerson().getAddress().getStreet());
            form.setTown(volunteer.getPerson().getAddress().getTown());
            form.setCounty(volunteer.getPerson().getAddress().getCounty());
            form.setPostcode(volunteer.getPerson().getAddress().getPostcode());
            form.setEmail(volunteer.getPerson().getEmail());
            form.setTelephone(volunteer.getPerson().getTelephone());
            form.setWorkPhone(volunteer.getPerson().getWorkPhone());
            form.setMobile(volunteer.getPerson().getMobile());

            model.addAttribute("forename", volunteer.getPerson().getForename());
            model.addAttribute("surname", volunteer.getPerson().getSurname());
            model.addAttribute("volunteer", form);
            return "volunteer-contact/volunteer-contact-form";
        } else {
            return "volunteer-contact/volunteer-incorrect-form";
        }
    }

    /**
     * Handles update requests. There is no security checks around this because
     * it is done be the unauthenticated user. Security is added by hashed urls.
     *
     * @param volunteerId the person Id
     * @param datetime the date and time of the original request
     * @param hash the hash
     * @param form the updated contact form
     * @throws TemplateException on failure to render the confirmation email
     * @throws IOException  on failure to render the confirmation email
     */
    @RequestMapping(value = "/{volunteerId}/{datetime}/{hash}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void acceptUpdate(@PathVariable Integer volunteerId, @PathVariable String datetime,
            @PathVariable String hash, @Valid VolunteerUpdateForm form) throws IOException, TemplateException {
        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, null);
        if (volunteer == null) {
            throw new ResourceNotFoundException("No volunteer #" + volunteerId);
        }

        if (!checkHash(volunteer, datetime, hash)) {
            throw new ForbiddenRequestException("Mismatched request hash");
        }
        if (!checkWithinTime(datetime)) {
            throw new ForbiddenRequestException("Expired request hash");
        }

        volunteer.getPerson().setEmail(form.getEmail());
        volunteer.getPerson().setTelephone(form.getTelephone());
        volunteer.getPerson().setMobile(form.getMobile());
        volunteer.getPerson().setWorkPhone(form.getWorkPhone());
        volunteer.getPerson().getAddress().setStreet(form.getStreet());
        volunteer.getPerson().getAddress().setTown(form.getTown());
        volunteer.getPerson().getAddress().setCounty(form.getCounty());
        volunteer.getPerson().getAddress().setPostcode(form.getPostcode());

        UserDetails system = userDetailsService.loadUserByUsername("System");
        Authentication authentication = new UsernamePasswordAuthenticationToken(system, system.getUsername(),
                system.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        volunteerDao.updateVolunteer(volunteer);

        Email email = volunteerUpdateEmailGenerator.generateVolunteerUpdateConfirmationEmail(volunteer);
        emailDao.save(email);
    }

    /**
     * Checks if the update request is within 24 hours of the email.
     *
     * @param datetime when the original request was sent
     * @return true or false if within 24 hours
     */
    private boolean checkWithinTime(String originalTime) {
        DateTime now = new DateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATETIMEFORMAT);
        DateTime then = formatter.parseDateTime(originalTime);
        long difference = now.getMillis() - then.getMillis();
        return MAXTIME > difference;
    }

    /**
     * Checks that the hash is correct for the given volunteer
     *
     * @param volunteer who wants to update his information
     * @param datetime of the original request in email
     * @param hash of the request
     * @return true or false if correct
     */
    private boolean checkHash(Volunteer volunteer, String datetime, String hash) {
        String value = datetime + ":" + volunteer.getPersonId() + volunteer.getPerson().getBirthDate().toString();
        String generatedHash = HashGenerator.generateHash(value, edificeProperty.getProperty(SECURITY_SALT));

        return generatedHash.equalsIgnoreCase(hash);
    }
}
