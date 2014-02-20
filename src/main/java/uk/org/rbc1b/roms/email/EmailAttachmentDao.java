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

package uk.org.rbc1b.roms.email;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * Accesses the EmailAttachment table.
 */
public interface EmailAttachmentDao {

    /**
     * Gets a list of email attachments.
     *
     * @return emailAttachments list or null if there is none
     */
    @Transactional(readOnly = true)
    List<EmailAttachment> findAll();

    /**
     * Gets a list of email attachments by email id.
     *
     * @param email the email for which to get the attachments
     * @return emailAttachments list or null if there is none
     */
    @Transactional(readOnly = true)
    List<EmailAttachment> findByEmail(Email email);

    /**
     * Saves an emailAttachment to the table.
     *
     * @param emailAttachment the email attachment to save
     */
    @Transactional
    void save(EmailAttachment emailAttachment);

    /**
     * Deletes a row from the table.
     *
     * @param emailAttachment the email attachment to delete
     */
    @Transactional
    void delete(EmailAttachment emailAttachment);
}
