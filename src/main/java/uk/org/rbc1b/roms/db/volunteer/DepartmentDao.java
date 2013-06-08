/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 * Access the department and team information.
 *
 * @author oliver.elder.esq
 */
public interface DepartmentDao {

    /**
     * Find the team.
     *
     * @param teamId id
     * @return team information, or null if not found
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    Team findTeam(Integer teamId);

    /**
     * Find the department by id.
     *
     * @param departmentId id
     * @return department information, or null if not found
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    Department findDepartment(Integer departmentId);

    /**
     * Find the department by name.
     *
     * @param name department name
     * @return Department, or null if not found
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    Department findDepartmentByName(String name);

    /**
     * Get all Departments.
     *
     * @return List of departments
     */
    @Transactional(readOnly = true)
    List<Department> getAllDepartments();
}
