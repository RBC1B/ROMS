/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
