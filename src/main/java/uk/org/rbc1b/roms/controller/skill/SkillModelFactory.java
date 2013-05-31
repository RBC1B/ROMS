/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.department.DepartmentModelFactory;
import uk.org.rbc1b.roms.db.volunteer.Department;
import uk.org.rbc1b.roms.db.volunteer.DepartmentDao;
import uk.org.rbc1b.roms.db.volunteer.Skill;

/**
 * Generate the skills models.
 *
 * @author oliver.elder.esq
 */
@Component
public class SkillModelFactory {

    private static final String BASE_URI = "/skills/";
    private DepartmentDao departmentDao;
    private DepartmentModelFactory departmentModelFactory;

    /**
     * Generate the uri used to access the skill pages.
     *
     * @param skillId optional skill id
     * @return uri
     */
    public String generateUri(Integer skillId) {
        return skillId != null ? BASE_URI + skillId : BASE_URI;
    }

    /**
     * Generate the model used in the skill list view.
     *
     * @param skill skill
     * @return model
     */
    public SkillListModel generateSkillListModel(Skill skill) {

        SkillListModel model = new SkillListModel();
        model.setId(skill.getSkillId());
        model.setAppearOnBadge(skill.isAppearOnBadge());

        Department department = departmentDao.findDepartment(skill.getDepartmentId());

        EntityModel departmentModel = new EntityModel();
        departmentModel.setName(department.getName());
        departmentModel.setId(department.getDepartmentId());
        departmentModel.setUri(departmentModelFactory.generateUri(department.getDepartmentId()));

        model.setDepartment(departmentModel);
        model.setDescription(skill.getDescription());
        model.setName(skill.getName());
        model.setUri(generateUri(skill.getSkillId()));

        return model;
    }

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Autowired
    public void setDepartmentModelFactory(DepartmentModelFactory departmentModelFactory) {
        this.departmentModelFactory = departmentModelFactory;
    }
}
