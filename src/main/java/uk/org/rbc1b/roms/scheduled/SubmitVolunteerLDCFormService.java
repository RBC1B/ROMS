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
package uk.org.rbc1b.roms.scheduled;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.volunteer.ldc.SubmitLDCFormVolunteerEmailGenerator;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.email.EmailDao;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.VolunteerSearchCriteria;

/**
 * Scheduled service to inform volunteers, via email, that they should
 * submit a new LDC form if their volunteer form is older than 2 and a half
 * years.
 *
 * @author rahulsingh
 */
@Component
public class SubmitVolunteerLDCFormService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitVolunteerLDCFormService.class);

    private static final Integer MAX_RECIPIENTS = 5;

    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private EmailDao emailDao;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SubmitLDCFormVolunteerEmailGenerator submitLDCFormVolunteerEmailGenerator;

    /**
     * Queue the volunteers every ten minutes for the email.
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void queueVolunteersForLDCFormEmail() {
        UserDetails system = userDetailsService.loadUserByUsername("System");
        Authentication authentication = new UsernamePasswordAuthenticationToken(system, system.getUsername(),
                system.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        VolunteerSearchCriteria searchCriteria = new VolunteerSearchCriteria();
        searchCriteria.setMaxResults(MAX_RECIPIENTS);

        List<Volunteer> volunteersForEmail = volunteerDao.findVolunteersWhoNeedToSubmitLDCForm(searchCriteria);
        for (Volunteer volunteer : volunteersForEmail) {
            try {
                Email email = submitLDCFormVolunteerEmailGenerator.generateEmailForVolunteers(volunteer);

                LOGGER.info("Volunteer: " + volunteer.getPersonId());
                if (email != null) {
                    emailDao.save(email);
                }
                // update the ldc email sent flag for the volunteer
                volunteer.setSubmitNewLDCFormEmailSent(true);

                volunteerDao.updateVolunteer(volunteer);
            } catch (IOException | TemplateException ex) {
                LOGGER.error("Failed to send the volunteer submit LDC form email: ", ex);
            }
        }
    }
}
