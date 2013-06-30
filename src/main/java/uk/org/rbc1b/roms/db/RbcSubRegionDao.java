/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Looks up the RBC's sub regions.
 *
 * @author ramindursingh
 */
public interface RbcSubRegionDao {

    /**
     * Get a list of subregions.
     *
     * @return rbcsubregions
     */
    @Transactional(readOnly = true)
    List<RbcSubRegion> findAllRbcSubRegion();
}
