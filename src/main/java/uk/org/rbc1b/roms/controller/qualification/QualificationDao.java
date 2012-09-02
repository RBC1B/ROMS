/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.qualification;

import java.util.List;
import uk.org.rbc1b.roms.db.volunteer.Qualification;

/**
 * Look up qualification information.
 * @author Tina
 */
public interface QualificationDao {

    /**
     * Find the qualification matching the name, or null if no match.
     * @param name qualification name
     * @return  qualification
     */
    Qualification findQualification(String name);

    /**
     * Find all matching qualifications.
     * @return list of matching qualifications
     */
    List<Qualification> findQualifications();

    /**
     * Create a new qualification.
     * @param qualification new qualification to create
     */
    void createQualification(Qualification qualification);

}
