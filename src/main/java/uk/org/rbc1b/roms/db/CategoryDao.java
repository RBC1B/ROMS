/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ramindursingh
 */
public interface CategoryDao {

    /**
     * Find the colour.
     *
     * @param categoryId id
     * @return colour, or null if not found
     */
    @Transactional(readOnly = true)
    Category findCategory(Integer categoryId);
}
