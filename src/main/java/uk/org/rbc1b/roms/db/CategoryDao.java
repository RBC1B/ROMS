/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 *
 * @author ramindursingh
 */
public interface CategoryDao {

    /**
     * Find the category.
     *
     * @param categoryId id
     * @return Category, or null if not found
     */
    @Transactional(readOnly = true)
    Category findCategoryById(Integer categoryId);

    /**
     * Find the category by name.
     *
     * @param name category name
     * @return Category, or null if not found
     */
    @Transactional(readOnly = true)
    Category findCategoryByName(String name);

    /**
     * Get all Category.
     *
     * @return List Category
     */
    @Transactional(readOnly = true)
    List<Category> getAllCategories();
}
