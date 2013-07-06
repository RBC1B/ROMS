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
import uk.org.rbc1b.roms.controller.category.CategoryModelFactory;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.department.DepartmentModelFactory;
import uk.org.rbc1b.roms.db.Category;
import uk.org.rbc1b.roms.db.CategoryDao;
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
    private CategoryDao categoryDao;
    private CategoryModelFactory categoryModelFactory;

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
        model.setSkillId(skill.getSkillId());


        Department department = departmentDao.findDepartment(skill.getDepartment().getDepartmentId());

        EntityModel departmentModel = new EntityModel();
        departmentModel.setName(department.getName());
        departmentModel.setId(department.getDepartmentId());
        departmentModel.setUri(departmentModelFactory.generateUri(department.getDepartmentId()));

        model.setDepartment(departmentModel);
        model.setDescription(skill.getDescription());
        model.setName(skill.getName());
        model.setUri(generateUri(skill.getSkillId()));

        Category category = categoryDao.findCategoryById(skill.getCategory().getCategoryId());

        EntityModel categoryModel = new EntityModel();
        categoryModel.setId(category.getCategoryId());
        categoryModel.setName(category.getName());
        categoryModel.setUri(categoryModelFactory.generateUri(category.getCategoryId()));

        model.setCategory(categoryModel);


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

    @Autowired
    public void setColourDao(CategoryDao colourDao) {
        this.categoryDao = colourDao;
    }

    @Autowired
    public void setColourModelFactory(CategoryModelFactory colourModelFactory) {
        this.categoryModelFactory = colourModelFactory;
    }
}
