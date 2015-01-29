/*
 * The MIT License
 *
 * Copyright 2015 RBC1B.
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
package uk.org.rbc1b.roms.controller.volunteer.contactdetails;

import java.util.EnumSet;
import java.util.Properties;
import java.util.Set;
import javax.annotation.Resource;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.org.rbc1b.roms.controller.common.DataConverterUtil;
import uk.org.rbc1b.roms.controller.common.HashAndDateTimeValidator;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao.VolunteerData;

/**
 * Controller for volunteer contact details confirmation.
 * @author rahulsingh
 */
@Controller
@RequestMapping("/volunteer-contact-details-confirmation")
public class VolunteerContactDetailsConfirmationController {

    private static final String SECURITY_SALT = "security.salt";
    private static final String DATETIMEFORMAT = "yyyyMMddHHmm";
    private static final long MAXTIME = 86400000;
    private static final Set<VolunteerData> VOLUNTEER_DATA = EnumSet.of(VolunteerData.SPOUSE,
            VolunteerData.EMERGENCY_CONTACT, VolunteerData.TRADES, VolunteerData.INTERVIEWER);

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private VolunteerDao volunteerDao;
    @Resource(name = "edificeProperty")
    private Properties edificeProperty;

    /**
     * Show the volunteer contact details confirmed page when a volunteer
     * accesses this URI endpoint.
     *
     * @param volunteerId the volunteer
     * @param dateTime date time for the expiration of this URI
     * @param hash the hash
     * @return String view
     */
    @RequestMapping(value = "/{volunteerId}/{dateTime}/{hash}", method = RequestMethod.GET)
    public String showContactDetailsConfirmation(@PathVariable Integer volunteerId,
            @PathVariable String dateTime, @PathVariable String hash) {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, VOLUNTEER_DATA);

        if (volunteer == null) {
            return "volunteers/contact-details-confirmation/error";
        }

        HashAndDateTimeValidator hashDateTimeValidator = new HashAndDateTimeValidator();
        hashDateTimeValidator.setDateTimeFormat(DATETIMEFORMAT);
        hashDateTimeValidator.setMaxTime(MAXTIME);
        hashDateTimeValidator.setSalt(edificeProperty.getProperty(SECURITY_SALT));

        String value = dateTime + ":" + volunteerId;
        if (hashDateTimeValidator.checkWithinTime(dateTime) && hashDateTimeValidator.checkHash(value, hash)) {
            final DateTime dt = new DateTime();
            volunteer.setContactDetailsLastConfirmed(DataConverterUtil.toSqlDate(dt));

            UserDetails system = userDetailsService.loadUserByUsername("System");
            Authentication authentication = new UsernamePasswordAuthenticationToken(system, system.getUsername(),
                system.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            volunteerDao.updateVolunteer(volunteer);
            return "volunteers/contact-details-confirmation/view";
        }

        return "volunteers/contact-details-confirmation/error";
    }
}
