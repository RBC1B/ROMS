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

import java.io.Serializable;
import uk.org.rbc1b.roms.db.Person;

/**
 *
 * @author ramindursingh
 */
public class MailRecipient implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer mailRecipientId;
    private MailType mailType;
    private Person person;

    /**
     * @return the mailRecipientId
     */
    public Integer getMailRecipientId() {
        return mailRecipientId;
    }

    /**
     * @param mailRecipientId the mailRecipientId to set
     */
    public void setMailRecipientId(Integer mailRecipientId) {
        this.mailRecipientId = mailRecipientId;
    }

    /**
     * @return the mailType
     */
    public MailType getMailType() {
        return mailType;
    }

    /**
     * @param mailType the mailType to set
     */
    public void setMailType(MailType mailType) {
        this.mailType = mailType;
    }

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}
