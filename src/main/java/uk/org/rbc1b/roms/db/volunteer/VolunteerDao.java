/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import java.util.List;
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
     * @return volunteer
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    Volunteer findVolunteer(Integer volunteerId);

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
    void saveVolunteer(Volunteer volunteer);
}