/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.kingdomhall;

import java.util.List;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;

/**
 * Look up Kingdom Hall information.
 *
 * @author oliver.elder.esq
 */
public interface KingdomHallDao {

    /**
     * Find the kingdom hall matching the name, or null if no match.
     *
     * @param name kingdom hall name
     * @return kingdom hall
     */
    KingdomHall findKingdomHall(String name);

    /**
     * Find all matching KingdomHalls.
     *
     * @return list of matching kingdom halls
     */
    List<KingdomHall> findKingdomHalls();

    /**
     * Create a new kingdom hall.
     *
     * @param kingdomHall new kingdom hall to create
     */
    void createKingdomHall(KingdomHall kingdomHall);
}
