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
package uk.org.rbc1b.roms.controller.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.department.DepartmentModelFactory;
import uk.org.rbc1b.roms.controller.skill.category.CategoryModelFactory;
import uk.org.rbc1b.roms.db.volunteer.department.Department;
import uk.org.rbc1b.roms.db.volunteer.department.DepartmentDao;
import uk.org.rbc1b.roms.db.volunteer.skill.Skill;
import uk.org.rbc1b.roms.db.volunteer.skill.SkillCategory;
import uk.org.rbc1b.roms.db.volunteer.skill.SkillDao;

/**
 * Generate the skills models.
 * @author oliver.elder.esq
 */
@Component
public class SkillModelFactory {

    private static final String BASE_URI = "/skills";
    private DepartmentDao departmentDao;
    private CategoryModelFactory categoryModelFactory;
    private SkillDao skillDao;

    /**
     * Generate the uri used to access the skill pages.
     * @param skillId optional skill id
     * @return uri
     */
    public static String generateUri(Integer skillId) {
        return skillId != null ? BASE_URI + "/" + skillId : BASE_URI;
    }

    /**
     * Generate the model used in the skill list view.
     * @param skill skill
     * @return model
     */
    public SkillModel generateSkillModel(Skill skill) {
        SkillModel model = new SkillModel();
        model.setSkillId(skill.getSkillId());

        Department department = departmentDao.findDepartment(skill.getDepartment().getDepartmentId());

        EntityModel departmentModel = new EntityModel();
        departmentModel.setName(department.getName());
        departmentModel.setId(department.getDepartmentId());
        departmentModel.setUri(DepartmentModelFactory.generateUri(department.getDepartmentId()));

        model.setDepartment(departmentModel);
        model.setDescription(skill.getDescription());
        model.setName(skill.getName());
        model.setUri(generateUri(skill.getSkillId()));
        model.setEditUri(generateUri(skill.getSkillId()) + "/edit");

        SkillCategory category = skillDao.findSkillCategory(skill.getCategory().getSkillCategoryId());

        model.setCategory(categoryModelFactory.generateCategoryModel(category));

        return model;
    }

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Autowired
    public void setSkillDao(SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    @Autowired
    public void setCategoryModelFactory(CategoryModelFactory categoryModelFactory) {
        this.categoryModelFactory = categoryModelFactory;
    }

}
