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
package uk.org.rbc1b.roms.db;

import java.util.Set;
import java.io.Serializable;

/**
 * An object that defines an email.
 * There is no sender address as this is set by the JavaMail JNDI session.
 */

public class Email implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer emailId;
    private String receipient;
    private String subject;
    private String text;
    private Set<EmailAttachment> emailAttachments;

    /**
     * @return the emailId
     */
    public Integer getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(Integer emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the receipient
     */
    public String getReceipient() {
        return receipient;
    }

    /**
     * @param receipient the receipient to set
     */
    public void setReceipient(String receipient) {
        this.receipient = receipient;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the emailAttachments
     */
    public Set<EmailAttachment> getEmailAttachments() {
        return emailAttachments;
    }

    /**
     * @param emailAttachments the emailAttachments to set
     */
    public void setEmailAttachments(Set<EmailAttachment> emailAttachments) {
        this.emailAttachments = emailAttachments;
    }
}
