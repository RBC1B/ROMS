/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.skill;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
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
     * @param skillId skill ID to delete
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to find the skill
     */
    @RequestMapping(value = "{skillId}/delete", method = RequestMethod.GET)
    public String deleteSkill(@PathVariable Integer skillId)
            throws NoSuchRequestHandlingMethodException {
        Skill skill = this.skillDao.findSkill(skillId);
        if (skill == null) {
            throw new NoSuchRequestHandlingMethodException("No Skill #" + skillId, this.getClass());
        } else {
            this.skillDao.deleteSkill(skill);
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
