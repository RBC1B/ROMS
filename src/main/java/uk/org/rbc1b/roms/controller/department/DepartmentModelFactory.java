/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.department;

import org.springframework.stereotype.Component;

/**
 * Generate models for the departments.
 * @author oliver.elder.esq
 */
@Component
public class DepartmentModelFactory {

    private static final String BASE_URI = "/departments/";

    /**
     * Generate the uri used to access the department pages.
     *
     * @param departmentId optional department id
     * @return uri
     */
    public String generateUri(Integer departmentId) {
        return departmentId != null ? BASE_URI + departmentId : BASE_URI;
    }
}
