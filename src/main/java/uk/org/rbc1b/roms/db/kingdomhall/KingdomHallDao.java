/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.org.rbc1b.roms.db.kingdomhall;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import uk.org.rbc1b.roms.db.Congregation;

/**
 * Look up Kingdom Hall information.
 * @author oliver.elder.esq
 */
public interface KingdomHallDao {

    /**
     * Find the kingdom hall matching the name, or null if no match.
     * @param kingdomHallId kingdom hall id
     * @return kingdom hall
     */
    @PreAuthorize("hasPermission('KINGDOMHALL', 'READ')")
    @Transactional(readOnly = true)
    KingdomHall findKingdomHall(Integer kingdomHallId);

    /**
     * Find all matching KingdomHalls.
     * @return list of matching kingdom halls
     */
    @PreAuthorize("hasPermission('KINGDOMHALL', 'READ')")
    @Transactional(readOnly = true)
    List<KingdomHall> findKingdomHalls();

    /**
     * Find the list of kingdom halls matching the partial name.
     * @param name name
     * @return kingdom halls
     */
    @PreAuthorize("hasPermission('KINGDOMHALL','READ')")
    @Transactional(readOnly = true)
    List<KingdomHall> findKingdomHalls(String name);

    /**
     * Find the list of congregations that meet at a Kingdom Hall.
     * @param kingdomHallId id of the Kingdom Hall
     * @return congregations
     */
    @PreAuthorize("hasPermission('KINGDOMHALL','READ')")
    @Transactional(readOnly = true)
    List<Congregation> findCongregations(Integer kingdomHallId);

    /**
     * Create a new kingdom hall.
     * @param kingdomHall new kingdom hall to create
     */
    @PreAuthorize("hasPermission('KINGDOMHALL', 'ADD')")
    @Transactional
    void createKingdomHall(KingdomHall kingdomHall);

    /**
     * Update a kingdom hall.
     * @param kingdomHall new kingdom hall to create
     */
    @PreAuthorize("hasPermission('KINGDOMHALL', 'EDIT')")
    @Transactional
    void updateKingdomHall(KingdomHall kingdomHall);

    /**
     * Deletes a Kingdom Hall.
     * @param kingdomHall to delete
     */
    @PreAuthorize("hasPermission('KINGDOMHALL','DELETE')")
    @Transactional
    void deleteKingdomHall(KingdomHall kingdomHall);
}
