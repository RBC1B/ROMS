/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Looks up the RBC regions.
 *
 * @author ramindursingh
 */
public interface RbcRegionDao {

    /**
     * Get a list of RBC regions.
     *
     * @return rbcregions
     */
    @Transactional(readOnly = true)
    List<RbcRegion> findAllRbcRegion();
}
