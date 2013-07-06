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

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.controller.LoggingHandlerExceptionResolver;
import uk.org.rbc1b.roms.db.Category;
import uk.org.rbc1b.roms.db.CategoryDao;
import uk.org.rbc1b.roms.db.volunteer.Department;
import uk.org.rbc1b.roms.db.volunteer.DepartmentDao;
import uk.org.rbc1b.roms.db.volunteer.Skill;
import uk.org.rbc1b.roms.db.volunteer.SkillDao;

/**
 *
 * @author ramindursingh
 */
@Controller
@RequestMapping("/skills")
public class SkillsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingHandlerExceptionResolver.class);
    private SkillDao skillDao;
    private SkillModelFactory skillModelFactory;
    private CategoryDao categoryDao;
    private DepartmentDao departmentDao;

    /**
     * Display the list of skills.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showSkillList(ModelMap model) {

        List<Skill> skills = skillDao.findSkills();
        List<SkillListModel> modelList = new ArrayList<SkillListModel>(skills.size());
        for (Skill skill : skills) {
            modelList.add(skillModelFactory.generateSkillListModel(skill));
        }

        model.addAttribute("skills", modelList);

        return "skills/list";
    }

    /**
     * Display a specified skill.
     *
     * @param skillId skill id (primary key)
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to look up the
     * skill
     */
    @RequestMapping(value = "{skillId}", method = RequestMethod.GET)
    public String showSkill(@PathVariable Integer skillId, ModelMap model) throws NoSuchRequestHandlingMethodException {

        Skill skill = skillDao.findSkill(skillId);

        if (skill == null) {
            throw new NoSuchRequestHandlingMethodException("No skill #" + skillId, this.getClass());
        }

        model.addAttribute("skill", skill);

        return "skills/show";
    }

    /**
     * Displays a skill for editing.
     *
     * @param skillId skill ID to edit
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to find the skill
     */
    @RequestMapping(value = "{skillId}/edit", method = RequestMethod.GET)
    public String showEditSkillForm(@PathVariable Integer skillId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        Skill skill = this.skillDao.findSkill(skillId);
        if (skill == null) {
            throw new NoSuchRequestHandlingMethodException("No Skill #" + skillId, this.getClass());
        } else {
            SkillForm form = new SkillForm(skill);
            List<Department> departments = departmentDao.getAllDepartments();
            form.setDepartment(skill.getDepartment().getName());
            form.setCategory(skill.getCategory().getName());
            List<Category> categories = categoryDao.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("departments", departments);
            model.addAttribute("skill", form);
            return "skills/edit";
        }
    }

    /**
     * Display the form to create a new skill.
     *
     * @param model mvc model
     * @return view name
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String showCreateSkillForm(ModelMap model) {

        // initialise the form bean
        model.addAttribute("skill", new SkillForm());
        List<Department> departments = departmentDao.getAllDepartments();
        List<Category> categories = categoryDao.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("departments", departments);

        return "skills/edit";
    }

    /**
     * Create a new skill.
     *
     * @param skillForm form bean
     * @return view name
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createSkill(@Valid SkillForm skillForm) {

        Skill skill = new Skill();
        if (skillForm.getSkillId() != null) {
            skill.setSkillId(skillForm.getSkillId());
        }
        skill.setName(skillForm.getName());
        skill.setDescription(skillForm.getDescription());
        skill.setCategory(categoryDao.findCategoryByName(skillForm.getCategory()));
        skill.setDepartment(departmentDao.findDepartmentByName(skillForm.getDepartment()));

        skillDao.saveSkill(skill);

        return "redirect:skills";
    }

    /**
     * Deletes a skill.
     *
     * @param request http servlet request
     * @param model spring mvc model
     * @return mvc redirect
     * @throws NoSuchRequestHandlingMethodException on
     * failure to find the skill
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteSkill(HttpServletRequest request, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        Integer skillId;
        skillId = Integer.parseInt(request.getParameter("skillId"));
        Skill skill = this.skillDao.findSkill(skillId);
        if (skill == null) {
            throw new NoSuchRequestHandlingMethodException("No Skill #" + skillId, this.getClass());
        } else {
            this.skillDao.deleteSkill(skill);

            LOGGER.error("Deleted Skill:" + skillId);
            return "redirect:/skills";
        }
    }

    @Autowired
    public void setSkillDao(SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    @Autowired
    public void setSkillModelFactory(SkillModelFactory skillModelFactory) {
        this.skillModelFactory = skillModelFactory;
    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
}
