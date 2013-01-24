/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import java.util.List;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

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
    Volunteer findVolunteer(Integer volunteerId);

    /**
     * Find all matching volunteers.
     *
     * @return list of matching volunteers
     */
    List<Volunteer> findVolunteers();
}
