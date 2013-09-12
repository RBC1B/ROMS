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
package uk.org.rbc1b.roms.controller.skill.category;

import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.volunteer.skill.SkillCategory;

/**
 * Access the skill categories.
 */
@Component
public class CategoryModelFactory {

    private static final String BASE_URI = "/skill-categories/";

    /**
     * Generate the uri used to access the category pages.
     * @param skillCategoryId optional category id
     * @return uri
     */
    public static String generateUri(Integer skillCategoryId) {
        return skillCategoryId != null ? BASE_URI + skillCategoryId : BASE_URI;
    }

    /**
     * Generate the model representing a skill category.
     * @param category category
     * @return model
     */
    public SkillCategoryModel generateCategoryModel(SkillCategory category) {
        SkillCategoryModel model = new SkillCategoryModel();
        model.setSkillCategoryId(category.getSkillCategoryId());
        model.setName(category.getName());
        model.setUri(generateUri(category.getSkillCategoryId()));
        model.setAppearOnBadge(category.isAppearOnBadge());
        model.setColour(category.getColour());
        return model;
    }

}
