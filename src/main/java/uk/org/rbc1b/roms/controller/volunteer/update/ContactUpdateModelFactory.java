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
package uk.org.rbc1b.roms.controller.volunteer.update;

import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

/**
 * Generates the contact update form.
 *
 */
@Component
public class ContactUpdateModelFactory {

    /**
     * Generates the form for update form.
     *
     * @param volunteer the volunteer to update
     * @param datetime the datetime of the original request
     * @param hash the security hash
     * @return form
     */
    public ContactUpdateForm generateContactUpdateModel(Volunteer volunteer, String datetime, String hash) {
        ContactUpdateForm form = new ContactUpdateForm();
        form.setPersonId(volunteer.getPersonId());
        form.setForename(volunteer.getPerson().getForename() == null ? "" : volunteer.getPerson().getForename());
        form.setSurname(volunteer.getPerson().getSurname() == null ? "" : volunteer.getPerson().getSurname());
        form.setStreet(volunteer.getPerson().getAddress().getStreet() == null ? "" : volunteer.getPerson().getAddress().getStreet());
        form.setTown(volunteer.getPerson().getAddress().getTown() == null ? "" : volunteer.getPerson().getAddress().getTown());
        form.setCounty(volunteer.getPerson().getAddress().getCounty() == null ? "" : volunteer.getPerson().getAddress().getCounty());
        form.setPostcode(volunteer.getPerson().getAddress().getPostcode() == null ? "" : volunteer.getPerson().getAddress().getPostcode());
        form.setEmail(volunteer.getPerson().getEmail() == null ? "" : volunteer.getPerson().getEmail());
        form.setTelephone(volunteer.getPerson().getTelephone() == null ? "" : volunteer.getPerson().getTelephone());
        form.setWorkPhone(volunteer.getPerson().getWorkPhone() == null ? "" : volunteer.getPerson().getWorkPhone());
        form.setMobile(volunteer.getPerson().getMobile() == null ? "" : volunteer.getPerson().getMobile());
        return form;
    }
}
