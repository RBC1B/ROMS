/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.category;

import org.springframework.stereotype.Component;

/**
 *
 * @author ramindursingh
 */
@Component
public class CategoryModelFactory {

    private static final String BASE_URI = "/categories/";

    /**
     * Generate the uri used to access the category pages.
     *
     * @param categoryId optional category id
     * @return uri
     */
    public String generateUri(Integer categoryId) {
        return categoryId != null ? BASE_URI + categoryId : BASE_URI;
    }
}
