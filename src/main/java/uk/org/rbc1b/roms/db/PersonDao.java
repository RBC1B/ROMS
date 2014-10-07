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

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Add/edit/delete/update the core person data.
 * @author oliver
 */
public interface PersonDao {

    /**
     * Look up the person by the primary key.
     * @param personId person id
     * @return Person, or null if no matching instance
     */
    @Transactional(readOnly = true)
    Person findPerson(Integer personId);

    /**
     * Look up the list of people matching the criteria.
     * <p>
     * We look for exact match names
     * @param forename person's first name
     * @param surname person's last name
     * @return list of people
     */
    @Transactional(readOnly = true)
    List<Person> findPersons(String forename, String surname);

    /**
     * Look up the list of people matching the criteria. All criteria are optional.
     * @param searchCriteria search criteria
     * @return list of people
     */
    @Transactional(readOnly = true)
    List<Person> findPersons(PersonSearchCriteria searchCriteria);

    /**
     * Look up the number of people matching the criteria.
     * @param searchCriteria search criteria
     * @return list of people
     */
    @Transactional(readOnly = true)
    int findPersonsCount(PersonSearchCriteria searchCriteria);

    /**
     * Save the new person.
     * @param person person to persist
     */
    @Transactional
    void createPerson(Person person);

    /**
     * Update the person.
     * @param person person to persist
     */
    @Transactional
    void updatePerson(Person person);
}
