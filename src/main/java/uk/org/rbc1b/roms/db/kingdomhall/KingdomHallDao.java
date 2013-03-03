/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.kingdomhall;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 * Look up Kingdom Hall information.
 *
 * @author oliver.elder.esq
 */
public interface KingdomHallDao {

    /**
     * Find the kingdom hall matching the name, or null if no match.
     *
     * @param kingdomHallId kingdom hall id
     * @return kingdom hall
     */
    @PreAuthorize("hasPermission('KINGDOMHALL', 'READ')")
    @Transactional(readOnly = true)
    KingdomHall findKingdomHall(Integer kingdomHallId);

    /**
     * Find all matching KingdomHalls.
     *
     * @return list of matching kingdom halls
     */
    @PreAuthorize("hasPermission('KINGDOMHALL', 'READ')")
    @Transactional(readOnly = true)
    List<KingdomHall> findKingdomHalls();

    /**
     * Create a new kingdom hall.
     *
     * @param kingdomHall new kingdom hall to create
     */
    @PreAuthorize("hasPermission('KINGDOMHALL', 'ADD')")
    @Transactional
    void createKingdomHall(KingdomHall kingdomHall);
}
