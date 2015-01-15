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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.DataConverterUtil;
import uk.org.rbc1b.roms.controller.volunteer.contactdetails.VolunteerContactDetailsEmailGenerator;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.email.EmailDao;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.VolunteerSearchCriteria;

/**
 *
 * @author rahulsingh
 */
@Component
public class DailyVolunteerEmailScheduledService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DailyVolunteerEmailScheduledService.class);
    private static final int NUMBER_OF_DAYS_IN_YEAR = 365;
    private static final int NUMBER_OF_DAYS_IN_LEAP_YEAR = 366;

    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private VolunteerContactDetailsEmailGenerator volunteerContactDetailsEmailGenerator;
    @Autowired
    private EmailDao emailDao;

    /**
     * Scheduled execution method for formatting the email and saving
     * into the database which will then be sent using {@code EmailScheduledService}.
     * This will be executed every day at noon.
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void queueVolunteerInformationEmails() {
        VolunteerSearchCriteria searchCriteria = new VolunteerSearchCriteria();
        searchCriteria.setMaxResults(findMaxVolunteersForEmail());

        List<Volunteer> volunteersForEmail = volunteerDao.findVolunteersWhoNeedBiannualEmail(searchCriteria);
        for (Volunteer volunteer : volunteersForEmail) {
            try {
                Email email = volunteerContactDetailsEmailGenerator.generateEmailForVolunteers(volunteer);

                if (email != null) {
                    emailDao.save(email);
                }
                // update the update contact details email last sent date to today for volunteer
                final DateTime dt = new DateTime();
                volunteer.setUpdateContactDetailsEmailLastSent(DataConverterUtil.toSqlDate(dt));

                volunteerDao.updateVolunteer(volunteer);
            } catch (IOException | TemplateException ex) {
                LOGGER.error("Failed to send the volunteer contact details email: ", ex);
            }
        }
    }

    /**
     * Helper method to find the maximum number of volunteers who
     * should receive the contact details email based on the six month
     * regularity of this service.
     *
     * @return integer of max number of volunteers
     */
    private int findMaxVolunteersForEmail() {
        VolunteerSearchCriteria searchCriteria = new VolunteerSearchCriteria();
        int totalVolunteerCount = volunteerDao.findVolunteersCount(searchCriteria);
        final DateTime dateTime = new DateTime();

        if (dateTime.year().isLeap()) {
            return new BigDecimal(totalVolunteerCount).divide(new BigDecimal(NUMBER_OF_DAYS_IN_LEAP_YEAR),
                RoundingMode.UP).intValue();
        }

        return new BigDecimal(totalVolunteerCount).divide(new BigDecimal(NUMBER_OF_DAYS_IN_YEAR),
                RoundingMode.UP).intValue();
    }
}
