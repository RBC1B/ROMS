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
package uk.org.rbc1b.roms.db.application;

import java.util.List;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;

/**
 * Look up application access information for user.
 */
public interface ApplicationAccessDao {

    /**
     * Look up permission by person id.
     *
     * @param personId the person
     * @return list of permissions
     */
    @Transactional(readOnly = true)
    List<ApplicationAccess> findUserPermissions(Integer personId);

    /**
     * Saves application access.
     *
     * @param applicationAccess set
     */
    @Transactional
    void saveApplicationAccess(Set<ApplicationAccess> applicationAccess);

    /**
     * Deletes all ACLs for a person.
     *
     * @param personId the person id to delete
     */
    @Transactional
    void deleteAclForPerson(Integer personId);
}
