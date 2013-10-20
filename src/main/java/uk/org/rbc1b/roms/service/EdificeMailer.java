/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
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
package uk.org.rbc1b.roms.service;

import java.util.List;
import java.util.Set;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.Email;
import uk.org.rbc1b.roms.db.EmailAttachment;
import uk.org.rbc1b.roms.db.EmailDao;

/**
 *
 */
@Component
public class EdificeMailer {

    @Autowired
    private JavaMailSender mailGateway;
    @Autowired
    private EmailDao emailDao;

    /**
     * Prepares and sends out all outstanding email.
     *
     * @throws MessagingException messaging exception
     */
    public void prepareAndSendEmail()
            throws MessagingException {
        List<Email> emails = this.emailDao.findAll();
        for (Email email : emails) {
            MimeMessage mimeMessage = this.mailGateway.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(email.getReceipient());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText());
            Set<EmailAttachment> attachments = email.getEmailAttachments();
            /* To do - add attachements
            if (!attachments.isEmpty()) {
                for (EmailAttachment attachment : attachments) {
                    // To do
                }
            } */
            this.getMailGateway().send(mimeMessage);
            this.emailDao.delete(email);
        }
    }

    public JavaMailSender getMailGateway() {
        return mailGateway;
    }

    public void setMailGateway(JavaMailSender mailGateway) {
        this.mailGateway = mailGateway;
    }

    public EmailDao getEmailDao() {
        return emailDao;
    }

    public void setEmailDao(EmailDao emailDao) {
        this.emailDao = emailDao;
    }
}
