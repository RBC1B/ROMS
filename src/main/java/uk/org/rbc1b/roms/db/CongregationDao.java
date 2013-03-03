/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Look up the congregation object instances.
 * @author oliver.elder.esq
 */
public interface CongregationDao {

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
     * @param name name
     * @return congregations
     */
    @Transactional(readOnly = true)
    List<Congregation> findCongregations(String name);

}
