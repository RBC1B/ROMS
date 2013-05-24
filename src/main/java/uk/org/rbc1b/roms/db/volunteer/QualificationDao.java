/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

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
