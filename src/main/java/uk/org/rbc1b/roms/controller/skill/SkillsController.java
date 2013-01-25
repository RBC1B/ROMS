/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.skill;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.db.volunteer.Skill;

/**
 *
 * @author ramindursingh
 */
@Controller
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    private SkillDao skillDao;

    /**
     * Display the list of skills.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasPermission('SKILL', 'READ')")
    @Transactional(readOnly = true)
    public String handleList(ModelMap model) {

        model.addAttribute("skills", skillDao.findSkills());

        return "skills/list";
    }

    /**
     * Display a specified skill.
     *
     * @param skillId skill id (primary key)
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to look up the
     * circuit
     */
    @RequestMapping(value = "{skillId}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('SKILL', 'READ')")
    @Transactional(readOnly = true)
    public String handleSkill(@PathVariable Integer skillId, ModelMap model) throws NoSuchRequestHandlingMethodException {

        Skill skill = skillDao.findSkill(skillId);

        if (skill == null) {
            throw new NoSuchRequestHandlingMethodException("No skill #" + skillId, this.getClass());
        }

        model.addAttribute("skill", skill);

        return "skills/show";
    }

    /**
     * Display the form to create a new skill.
     *
     * @param model mvc model
     * @return view name
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('SKILL', 'ADD')")
    public String handleNewForm(ModelMap model) {

        // initialise the form bean
        model.addAttribute("skill", new SkillForm());

        return "skills/edit";
    }

    /**
     * Create a new circuit.
     *
     * @param skillForm form bean
     * @return view name
     */
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasPermission('SKILL', 'ADD')")
    @Transactional
    public String handleNewSubmit(@Valid SkillForm skillForm) {

        Skill skill = new Skill();
        skill.setName(skillForm.getName());
        skill.setDescription(skillForm.getDescription());
        skill.setAppearOnBadge(skillForm.isAppearOnBadge());

        skillDao.createSkill(skill);



        return "redirect:skills/" + skill.getSkillId();
    }

    /**
     * @param skillDao circuit dao
     */
    public void setSkillDao(SkillDao skillDao) {
        this.skillDao = skillDao;
    }
}
