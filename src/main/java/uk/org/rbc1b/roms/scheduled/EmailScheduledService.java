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

import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.email.EmailDao;

/**
 * Send pending emails. This is the main service class that sends queued emails
 * in the database.
 */
@Component
public class EmailScheduledService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailScheduledService.class);
    @Autowired
    private JavaMailSender mailGateway;
    @Autowired
    private EmailDao emailDao;

    /**
     * Send emails that have been queued up, stored in the database.
     * Scheduled to send once every 30 minutes.
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    //@Scheduled(cron = "0 0/30 * * * ?")
    public void sendEmails() {
        List<Email> emails = this.emailDao.findAll();
        for (Email email : emails) {
            try {
                sendEmail(email);
                this.emailDao.delete(email);
            } catch (MessagingException e) {
                LOGGER.error("Failed to send email [" + email + "]", e);
            }
        }
    }

    private void sendEmail(Email email) throws MessagingException {
        MimeMessage mimeMessage = this.mailGateway.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        if (email.getReplyTo() != null) {
            helper.setReplyTo(email.getReplyTo());
        }
        helper.setTo(email.getRecipient());
        if (email.getCc() != null) {
            helper.setCc(email.getCc());
        }
        helper.setSubject(email.getSubject());
        helper.setText(email.getText());
        this.mailGateway.send(mimeMessage);
    }

}
