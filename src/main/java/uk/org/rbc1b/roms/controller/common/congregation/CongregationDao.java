/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.common.congregation;

import uk.org.rbc1b.roms.db.Congregation;

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
    Congregation findCongregation(Integer congregationId);
}
