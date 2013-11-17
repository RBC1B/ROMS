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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.Email;
import uk.org.rbc1b.roms.db.EmailAttachment;
import uk.org.rbc1b.roms.db.EmailDao;

/**
 * Edifice Mailer that sends out emails.
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
     * @throws MessagingException
     *             messaging exception
     * @throws SQLException
     *             SQL exception
     * @throws IOException
     *             IO exception
     */
    public void prepareAndSendEmail() throws MessagingException, SQLException,
            IOException {
        List<Email> emails = this.emailDao.findAll();
        for (Email email : emails) {
            MimeMessage mimeMessage = this.mailGateway.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(email.getRecipient());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText());
            Set<EmailAttachment> emailAttachments = email.getEmailAttachments();
            if (!emailAttachments.isEmpty()) {
                for (EmailAttachment emailAttachment : emailAttachments) {
                    InputStream inputStream = new BufferedInputStream(
                            emailAttachment.getAttachment().getBinaryStream());
                    DataSource source = new ByteArrayDataSource(inputStream,
                            emailAttachment.getFileType());
                    helper.addAttachment(emailAttachment.getFilename(), source);
                }
            }
            this.mailGateway.send(mimeMessage);
            this.emailDao.delete(email);
        }
    }
}
