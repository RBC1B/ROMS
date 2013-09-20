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
package uk.org.rbc1b.roms.db.volunteer.skill;

import org.hibernate.envers.Audited;
import uk.org.rbc1b.roms.db.DefaultUpdateAuditable;

/**
 * @author ramindursingh
 */
@Audited
public class SkillCategory extends DefaultUpdateAuditable {
    private static final long serialVersionUID = -1733787944477712949L;
    private Integer skillCategoryId;
    private String name;
    private String colour;
    private boolean appearOnBadge;

    public Integer getSkillCategoryId() {
        return skillCategoryId;
    }

    public void setSkillCategoryId(Integer skillCategoryId) {
        this.skillCategoryId = skillCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public boolean isAppearOnBadge() {
        return appearOnBadge;
    }

    public void setAppearOnBadge(boolean appearOnBadge) {
        this.appearOnBadge = appearOnBadge;
    }

    @Override
    public String toString() {
        return name;
    }
}
