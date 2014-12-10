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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.controller.common.DataConverterUtil;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.email.EmailDao;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.org.rbc1b.roms.controller.ForbiddenRequestException;
import uk.org.rbc1b.roms.controller.NotFoundException;
import uk.org.rbc1b.roms.controller.UnprocessableRequestException;

/**
 * Controller for checking and accepting requests to update contact details.
 *
 */
@Controller
@RequestMapping("/volunteer-contact")
public class UpdateRequestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateRequestController.class);
    private static final String BASE_URI = "/volunteer-contact";
    private static final String UPDATE_REQUEST_TEMPLATE = "volunteer-contact-update-request.ftl";
    private static final String POST_UPDATE_TEMPLATE = "volunteer-contact-update-confirmation.ftl";
    private static final String SUBJECT = "RBC (London & Home Counties) Volunteer Information Update";
    private static final String SECURITY_SALT = "security.salt";
    private static final String SERVER_URL = "edifice.url";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String DATETIMEFORMAT = "yyyyMMddHHmm";
    private static final long MAXTIME = 86400000;
    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private EmailDao emailDao;
    @Autowired
    private FreeMarkerConfigurer emailFreemarkerConfigurer;
    @Autowired
    private ContactUpdateModelFactory contactUpdateModelFactory;
    @Autowired
    private UserDetailsService userDetailsService;
    @Resource(name = "edificeProperty")
    private Properties edificeProperty;

    /**
     * Accepts and checks requests for updating contact when the volunteer puts
     * in his RVC ID and date of birth to trigger an email. There is no security
     * checks around this as this is initial request by the volunteer.
     *
     * @param form the user form
     * @param request the http request
     * @throws UnprocessableRequestException when it is unprocessible
     * @throws ForbiddenRequestException when data does not match
     * @throws NotFoundException when user is not on system
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void acceptRequest(@Valid RequestForm form, HttpServletRequest request)
            throws UnprocessableRequestException, ForbiddenRequestException, NotFoundException {
        Volunteer volunteer = volunteerDao.findVolunteerById(form.getPersonId());
        Date birthDate = DataConverterUtil.toSqlDate(form.getBirthDate());
        if (volunteer == null) {
            throw new NotFoundException("RBC ID " + form.getPersonId() + " does not exist");
        } else if (volunteer.getPerson().getBirthDate() == null || volunteer.getPerson().getBirthDate().compareTo(birthDate) != 0) {
            throw new ForbiddenRequestException("Insufficient information for:" + form.getPersonId());
        } else if (!checkEmail(volunteer)) {
            throw new UnprocessableRequestException("No valid email address on system for:" + form.getPersonId());
        } else {
            String uri = generateSecureUri(request, volunteer);
            try {
                prepareEmail(volunteer, uri);
            } catch (IOException e) {
                throw new UnprocessableRequestException("Problem reading email template");
            } catch (TemplateException e) {
                throw new UnprocessableRequestException("Problem sending email");
            }
        }
    }

    /**
     * Handles the request to update contact. There is no security checks around
     * this because it is done be the unauthenticated user. Security is added by
     * hashed urls.
     *
     * @param personId the person id
     * @param datetime the datetime of the email
     * @param hash the hash
     * @param model the mvc model
     * @return contact update form
     */
    @RequestMapping(value = "/{personId}/{datetime}/{hash}", method = RequestMethod.GET)
    public String showVolunteerContact(@PathVariable Integer personId, @PathVariable String datetime,
            @PathVariable String hash, ModelMap model) {
        String uri = BASE_URI + "/" + personId + "/" + datetime + "/" + hash;
        Volunteer volunteer = volunteerDao.findVolunteerById(personId);
        if (volunteer == null) {
            return "volunteer-contact/volunteer-incorrect-form";
        }
        if (checkWithinTime(datetime) && checkHash(volunteer, datetime, hash)) {
            ContactUpdateForm contactUpdateModel = contactUpdateModelFactory.generateContactUpdateModel(volunteer,
                    datetime, hash);
            model.addAttribute("contactUpdateModel", contactUpdateModel);
            model.addAttribute("submitUrl", uri);
            model.addAttribute("submitMethod", "POST");
            return "volunteer-contact/volunteer-contact-form";
        } else {
            return "volunteer-contact/volunteer-incorrect-form";
        }
    }

    /**
     * Handles update requests. There is no security checks around this because
     * it is done be the unauthenticated user. Security is added by hashed urls.
     *
     * @param personId the person Id
     * @param datetime the date and time of the original request
     * @param hash the hash
     * @param form the updated contact form
     * @throws UnprocessableRequestException when it is unprocessible
     * @throws ForbiddenRequestException when data does not match
     * @throws NotFoundException when volunteer ID does not exist
     */
    @RequestMapping(value = "/{personId}/{datetime}/{hash}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void acceptUpdate(@PathVariable Integer personId, @PathVariable String datetime, @PathVariable String hash,
            @Valid ContactUpdateForm form)
            throws UnprocessableRequestException, ForbiddenRequestException {
        LOGGER.error("Volunteer Contact Update Form for " + personId);
        Volunteer volunteer = volunteerDao.findVolunteerById(personId);
        if (volunteer == null) {
            throw new NotFoundException("Volunteer not found.");
        } else {
            if (checkWithinTime(datetime) && checkHash(volunteer, datetime, hash)) {
                volunteer.getPerson().setEmail(form.getEmail());
                volunteer.getPerson().setTelephone(form.getTelephone());
                volunteer.getPerson().setMobile(form.getMobile());
                volunteer.getPerson().setWorkPhone(form.getWorkPhone());
                volunteer.getPerson().getAddress().setStreet(form.getStreet());
                volunteer.getPerson().getAddress().setTown(form.getTown());
                volunteer.getPerson().getAddress().setCounty(form.getCounty());
                volunteer.getPerson().getAddress().setPostcode(form.getPostcode());
                try {
                    UserDetails system = userDetailsService.loadUserByUsername("System");
                    Authentication authentication = new UsernamePasswordAuthenticationToken(system,
                            system.getUsername(), system.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    volunteerDao.updateVolunteerByVolunteer(volunteer);
                    preparePostUpdateEmail(volunteer);
                } catch (IOException e) {
                    throw new UnprocessableRequestException("Problem reading email template.");
                } catch (TemplateException e) {
                    throw new UnprocessableRequestException("Problem with email template.");
                }
            } else {
                throw new ForbiddenRequestException("Security token expired or not valid.");
            }
        }
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
        String token = getSecureToken(volunteer, datetime);
        return token.compareTo(hash) == 0;
    }

    /**
     * Checks for valid email. Later, we can add further email validation, etc.
     *
     * @param volunteer the volunteer to check
     * @return boolean if valid email or not
     */
    private boolean checkEmail(Volunteer volunteer) {
        if (volunteer.getPerson().getEmail() == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(volunteer.getPerson().getEmail());
        return matcher.matches();
    }

    /**
     * Prepares an email to send to the volunteer.
     *
     * @param volunteer the volunteer to send
     * @param url the URI
     * @throws exception for IO and template
     */
    private void prepareEmail(Volunteer volunteer, String uri) throws IOException, TemplateException {
        Configuration conf = emailFreemarkerConfigurer.getConfiguration();
        Map<String, String> model = new HashMap<String, String>();
        model.put("forename", volunteer.getPerson().getForename());
        model.put("httpsurl", uri);
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(conf.getTemplate(UPDATE_REQUEST_TEMPLATE),
                model);
        Email email = new Email();
        email.setRecipient(volunteer.getPerson().getEmail());
        email.setSubject(SUBJECT);
        email.setText(text);
        emailDao.save(email);
    }

    private void preparePostUpdateEmail(Volunteer volunteer) throws IOException, TemplateException {
        Configuration conf = emailFreemarkerConfigurer.getConfiguration();
        Map<String, String> model = new HashMap<String, String>();
        model.put("forename", volunteer.getPerson().getForename());
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(conf.getTemplate(POST_UPDATE_TEMPLATE), model);
        Email email = new Email();
        email.setRecipient(volunteer.getPerson().getEmail());
        email.setSubject(SUBJECT + " - Confirmation");
        email.setText(text);
        emailDao.save(email);
    }

    /**
     * Gets the secure URI for a given volunteer
     *
     * @param request the https request
     * @param volunteer the volunteer
     * @return url string
     */
    private String generateSecureUri(HttpServletRequest request, Volunteer volunteer) {
        String url = edificeProperty.getProperty(SERVER_URL);
        if (url == null || url.isEmpty()) {
            LOGGER.error("JNDI property for this server's URL is not set.");
            url = request.getRequestURL().toString();
        } else {
            url = url + request.getRequestURI();
        }
        List<String> path = new ArrayList<String>();
        path.add(url);
        path.add(Integer.toString(volunteer.getPersonId()));
        String dateTime = getCurrentDateTime();
        path.add(dateTime);
        path.add(getSecureToken(volunteer, dateTime));

        return StringUtils.join(path, "/");
    }

    /**
     * Method that simply gets the current datetime.
     *
     * @return string representation of current date and time
     */
    private String getCurrentDateTime() {
        DateTime datetime = new DateTime();
        return datetime.toString(DATETIMEFORMAT);
    }

    /**
     * Gets the secure token.
     *
     * @param volunteer the volunteer
     * @param datetime for the current date time
     * @return token string
     */
    private String getSecureToken(Volunteer volunteer, String datetime) {
        String salt = edificeProperty.getProperty(SECURITY_SALT);
        if (salt == null || salt.isEmpty()) {
            salt = "er9bhmbsaa5ppdnoQP";
            LOGGER.error("JNDI property for security salt is not set - will use default.");
        }
        String text = datetime + ":" + volunteer.getPersonId() + volunteer.getPerson().getBirthDate().toString();
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        return encoder.encodePassword(salt, text);
    }
}
