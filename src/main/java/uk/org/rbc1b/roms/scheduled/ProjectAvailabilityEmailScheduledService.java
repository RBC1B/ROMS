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
package uk.org.rbc1b.roms.scheduled;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
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
import uk.org.rbc1b.roms.controller.project.ProjectAvailabilityEmailGenerator;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.email.EmailDao;
import uk.org.rbc1b.roms.db.project.ProjectAvailability;
import uk.org.rbc1b.roms.db.project.ProjectAvailabilityDao;
import freemarker.template.TemplateException;

/**
 * Checks PersonAvailability table to see if there is anyone who needs to be
 * send an email.
 */
@Component
public class ProjectAvailabilityEmailScheduledService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectAvailabilityEmailScheduledService.class);
    @Autowired
    private ProjectAvailabilityEmailGenerator projectAvailabilityEmailGenerator;
    @Autowired
    private EmailDao emailDao;
    @Autowired
    private ProjectAvailabilityDao projectAvailabilityDao;
    @Autowired
    private UserDetailsService userDetailsService;
    @Resource(name = "edificeProperty")
    private Properties edificeProperty;

    /**
     * Checks volunteers who have not been sent an email.
     */
    // For testing
    // @Scheduled(cron = "0 0/5 * * * ?")
    @Scheduled(cron = "0 45 * * * ?")
    public void checkAvailability() {
        UserDetails system = userDetailsService.loadUserByUsername("System");
        Authentication authentication = new UsernamePasswordAuthenticationToken(system, system.getUsername(),
                system.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<ProjectAvailability> projectAvailabilityList = projectAvailabilityDao.findUnnotifiedVolunteers();
        if (projectAvailabilityList.isEmpty()) {
            return;
        }
        for (ProjectAvailability projectAvailability : projectAvailabilityList) {
            try {
                Email email = projectAvailabilityEmailGenerator
                        .generateVolunteerAvailabilityRequestEmail(projectAvailability);
                if (email == null) {
                    LOGGER.error("Cannot send email to RBC ID:" + projectAvailability.getPerson().getPersonId());
                } else {
                    emailDao.save(email);
                    projectAvailability.setEmailSent(true);
                    projectAvailabilityDao.update(projectAvailability);
                }
            } catch (IOException | TemplateException e) {
                LOGGER.error("Failed to send availability email:", e);
            }
        }
    }

    /**
     * Checks volunteers to whom confirmed dates can be sent by email.
     */
    //@Scheduled(cron = "0 0/5 * * * ?")
    @Scheduled(cron = "0 40 * * * ?")
    public void checkConfirmationDates() {
        UserDetails system = userDetailsService.loadUserByUsername("System");
        Authentication authentication = new UsernamePasswordAuthenticationToken(system, system.getUsername(),
                system.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        List<ProjectAvailability> projectAvailabilityList = projectAvailabilityDao.findUnconfirmedVolunteers();
        if (projectAvailabilityList.isEmpty()) {
            return;
        }
        for (ProjectAvailability projectAvailability : projectAvailabilityList) {
            try {
                Email email = projectAvailabilityEmailGenerator
                        .generateVolunteerAvailabilityConfirmationEmail(projectAvailability);
                if (email == null) {
                    LOGGER.error("Cannot send email to RBC ID:" + projectAvailability.getPerson().getPersonId());

                } else {
                    emailDao.save(email);
                    projectAvailability.setConfirmationEmail(true);
                    projectAvailabilityDao.update(projectAvailability);
                }
            } catch (IOException | TemplateException e) {
                LOGGER.error("Failed to send confirmation email:", e);
            }
        }
    }
}
