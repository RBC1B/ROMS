/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.db.volunteer.Assignment;
import uk.org.rbc1b.roms.db.volunteer.Department;
import uk.org.rbc1b.roms.db.volunteer.DepartmentDao;
import uk.org.rbc1b.roms.db.volunteer.Team;
import uk.org.rbc1b.roms.db.reference.ReferenceDao;

/**
 * Factory class to create the assignment model.
 *
 * @author oliver.elder.esq
*/
@Component
public class AssignmentModelFactory {

    private static final String BASE_TEAMS_URI = "/teams/";
    private static final String BASE_DEPARTMENTS_URI = "/departments/";
    private ReferenceDao referenceDao;
    private DepartmentDao departmentDao;

    /**
     * Generate the uri used to access the team pages.
     *
     * @param teamId optional team id
     * @return uri
     */
    public String generateTeamUri(Integer teamId) {
        return teamId != null ? BASE_TEAMS_URI + teamId : BASE_TEAMS_URI;
    }

    /**
     * Generate the uri used to access the department pages.
     *
     * @param departmentId optional department id
     * @return uri
     */
    public String generateDepartmentUri(Integer departmentId) {
        return departmentId != null ? BASE_DEPARTMENTS_URI + departmentId : BASE_DEPARTMENTS_URI;
    }

    /**
     * Create the assignment model.
     *
     * @param assignment assignment
     * @return model
     */
    public AssignmentModel generateAssignmentModel(Assignment assignment) {

        AssignmentModel model = new AssignmentModel();
        model.setAssignedDate(assignment.getAssignedDate());
        model.setDepartment(createDepartmentModel(departmentDao.findDepartment(assignment.getDepartmentId())));
        model.setId(assignment.getAssignmentId());
        model.setRole(referenceDao.findAssignmentRoleValues().get(assignment.getRoleId()));
        model.setTeam(createTeamModel(departmentDao.findTeam(assignment.getTeamId())));
        model.setTradeNumber(referenceDao.findTradeNumbers().get(assignment.getTradeNumberId()));

        return model;
    }

    private EntityModel createDepartmentModel(Department department) {
        EntityModel model = new EntityModel();
        model.setId(department.getDepartmentId());
        model.setName(department.getName());
        model.setUri(generateDepartmentUri(department.getDepartmentId()));
        return model;
    }

    private EntityModel createTeamModel(Team team) {
        EntityModel model = new EntityModel();
        model.setId(team.getTeamId());
        model.setName(team.getDescription());
        model.setUri(generateTeamUri(team.getTeamId()));
        return model;
    }

    @Autowired
    public void setReferenceDao(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
}
