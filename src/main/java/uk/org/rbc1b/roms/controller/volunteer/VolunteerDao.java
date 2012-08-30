/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;


import java.util.List;
import uk.org.rbc1b.roms.db.Volunteer;
/**
 *
 * @author rahulsingh
 */
public interface VolunteerDao {
    
    /*
     * Find a Volunteer by the RBCID number
     */
    Volunteer findVolunteer(long rbcid);
    
    /*
     * Find all Volunteers in the database.
     */
    List<Volunteer> findVolunteers();
}
