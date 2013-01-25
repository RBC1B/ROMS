/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.skill;

import java.util.List;
import uk.org.rbc1b.roms.db.volunteer.Skill;

/**
 *
 * @author ramindursingh
 */
public interface SkillDao {

    /**
     * Find the skill matching the id, or null if no match.
     *
     * @param skillId id
     * @return skill
     */
    Skill findSkill(Integer skillId);

    /**
     * Find all matching skills.
     *
     * @return list of matching skills
     */
    List<Skill> findSkills();

    /**
     * Create a new skill.
     *
     * @param skill new skill to create
     */
    void createSkill(Skill skill);
}
