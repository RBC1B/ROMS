/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 * Look up the congregation object instances.
 *
 * @author oliver.elder.esq
 */
public interface CongregationDao {

    /**
     * Get a list of congregations.
     *
     * @return congregations
     */
    @Transactional(readOnly = true)
    List<Congregation> findAllCongregations();

    /**
     * Look up the congregation by the primary key.
     *
     * @param congregationId congregation id
     * @return Congregation, or null if no matching instance
     */
    @Transactional(readOnly = true)
    Congregation findCongregation(Integer congregationId);

    /**
     * Find the list of congregations matching the partial name.
     *
     * @param name name
     * @return congregations
     */
    @Transactional(readOnly = true)
    List<Congregation> findCongregations(String name);

    /**
     * Saves a congregation.
     *
     * @param congregation to save
     */
    @PreAuthorize("hasPermission('CONGREGATION','EDIT')")
    @Transactional
    void saveCongregation(Congregation congregation);

    /**
     * Creates a new congregation.
     *
     * @param congregation to create
     */
    @PreAuthorize("hasPermission('CONGREGATION','ADD')")
    @Transactional
    void createCongregation(Congregation congregation);

    /**
     * Deletes a congregation.
     *
     * @param congregation to delete
     */
    @PreAuthorize("hasPermission('CONGREGATION','DELETE')")
    @Transactional
    void deleteCongregation(Congregation congregation);
}
