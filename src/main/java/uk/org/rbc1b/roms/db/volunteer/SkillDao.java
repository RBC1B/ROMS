/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

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
    @PreAuthorize("hasPermission('SKILL', 'READ')")
    @Transactional(readOnly = true)
    Skill findSkill(Integer skillId);

    /**
     * Find all matching skills.
     *
     * @return list of matching skills
     */
    @PreAuthorize("hasPermission('SKILL', 'READ')")
    @Transactional(readOnly = true)
    List<Skill> findSkills();

    /**
     * Save a skill.
     *
     * @param skill a skill to save
     */
    @PreAuthorize("hasPermission('SKILL','EDIT')")
    @Transactional
    void saveSkill(Skill skill);

    /**
     * Create a new skill.
     *
     * @param skill new skill to create
     */
    @PreAuthorize("hasPermission('SKILL', 'ADD')")
    @Transactional
    void createSkill(Skill skill);

    /**
     * Deletes a skill.
     *
     * @param skill to delete
     */
    @PreAuthorize("hasPermission('SKILL','DELETE')")
    @Transactional
    void deleteSkill(Skill skill);
}
