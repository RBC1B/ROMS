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
package uk.org.rbc1b.roms.db.volunteer.qualification;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 * Look up qualification information.
 *
 * @author Tina
 */
public interface QualificationDao {

    /**
     * Find the qualification matching the name, or null if no match.
     *
     * @param name qualification name
     * @return qualification
     */
    @PreAuthorize("hasPermission('SKILL', 'READ')")
    @Transactional(readOnly = true)
    Qualification findQualification(String name);

    /**
     * Find the qualification matching id, or null if no match found.
     *
     * @param qualificationId qualification id
     * @return qualification
     *
     */
    @PreAuthorize("hasPermission('SKILL', 'READ')")
    @Transactional(readOnly = true)
    Qualification findQualification(Integer qualificationId);

    /**
     * Find all matching qualifications.
     *
     * @return list of matching qualifications
     */
    @PreAuthorize("hasPermission('SKILL', 'READ')")
    @Transactional(readOnly = true)
    List<Qualification> findQualifications();

    /**
     * Save a qualification.
     *
     * @param qualification a qualification to save
     */
    @PreAuthorize("hasPermission('SKILL','EDIT')")
    @Transactional
    void saveQualification(Qualification qualification);


    /**
     * Deletes a qualification.
     *
     * @param qualification to delete
     */
    @PreAuthorize("hasPermission('SKILL', 'DELETE')")
    @Transactional
    void deleteQualification(Qualification qualification);
}
