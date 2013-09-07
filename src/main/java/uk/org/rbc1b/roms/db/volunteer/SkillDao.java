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
package uk.org.rbc1b.roms.db.volunteer;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ramindursingh
 */
public interface SkillDao {

    /**
     * Find the skill matching the id, or null if no match.
     * @param skillId id
     * @return skill
     */
    @PreAuthorize("hasPermission('SKILL', 'READ')")
    @Transactional(readOnly = true)
    Skill findSkill(Integer skillId);

    /**
     * Find all matching skills.
     * @param searchCriteria search criteria
     * @return list of matching skills
     */
    @PreAuthorize("hasPermission('SKILL', 'READ')")
    @Transactional(readOnly = true)
    List<Skill> findSkills(SkillSearchCriteria searchCriteria);

    /**
     * Save a skill.
     * @param skill a skill to save
     */
    @PreAuthorize("hasPermission('SKILL','EDIT')")
    @Transactional
    void saveSkill(Skill skill);

    /**
     * Create a new skill.
     * @param skill new skill to create
     */
    @PreAuthorize("hasPermission('SKILL', 'ADD')")
    @Transactional
    void createSkill(Skill skill);

    /**
     * Deletes a skill.
     * @param skill to delete
     */
    @PreAuthorize("hasPermission('SKILL','DELETE')")
    @Transactional
    void deleteSkill(Skill skill);

    /**
     * Find the category.
     * @param categoryId id
     * @return Category, or null if not found
     */
    @PreAuthorize("hasPermission('SKILL', 'READ')")
    @Transactional(readOnly = true)
    Category findCategory(Integer categoryId);

    /**
     * Get all Category.
     * @return List Category
     */
    @PreAuthorize("hasPermission('SKILL', 'READ')")
    @Transactional(readOnly = true)
    List<Category> findCategories();

}
