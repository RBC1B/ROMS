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
package uk.org.rbc1b.roms.db.volunteer;

import java.util.List;
import java.util.Set;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 * Look up volunteer information.
 *
 * @author rahulsingh
 */
public interface VolunteerDao {

    /**
     * Find the volunteer with matching id, or null with no match.
     *
     * @param volunteerId id
     * @param data additional data to populate
     * @return volunteer
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    Volunteer findVolunteer(Integer volunteerId, Set<VolunteerData> data);

    /**
     * Find all matching volunteers.
     *
     * @param searchCriteria search criteria
     * @return list of matching volunteers
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    List<Volunteer> findVolunteers(VolunteerSearchCriteria searchCriteria);

    /**
     * @return the total number of volunteers
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    int findTotalVolunteersCount();

    /**
     * Look up the number of volunteers matching the criteria.
     *
     * @param searchCriteria search criteria
     * @return list of people
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    int findVolunteersCount(VolunteerSearchCriteria searchCriteria);

    /**
     * Save a volunteer.
     *
     * @param volunteer volunteer to
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'ADD')")
    @Transactional
    void createVolunteer(Volunteer volunteer);

    /**
     * Update a volunteer.
     *
     * @param volunteer volunteer to
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'EDIT')")
    @Transactional
    void updateVolunteer(Volunteer volunteer);

    /**
     * Find the volunteer assignments.
     *
     * @param volunteerId id
     * @return list of assignments
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    List<Assignment> findAssignments(Integer volunteerId);

    /**
     * Find the first or primary assignment of a volunteer.
     *
     * @param volunteerId id
     * @return assignment
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    Assignment findPrimaryAssignment(Integer volunteerId);

    /**
     * Find the volunteer skills.
     *
     * @param volunteerId id
     * @return list of skills
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    List<VolunteerSkill> findSkills(Integer volunteerId);

    /**
     * Find the volunteer qualifications.
     *
     * @param volunteerId id
     * @return list of qualifications
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    List<VolunteerQualification> findQualifications(Integer volunteerId);

    /**
     * Additional data to pull in when generating the volunteer details.
     */
    public static enum VolunteerData {

        SPOUSE, EMERGENCY_CONTACT, TRADES, INTERVIEWER;
    }
}
