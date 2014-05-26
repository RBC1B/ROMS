/*
 * The MIT License
 *
 * Copyright 2014 RBC1B.
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
package uk.org.rbc1b.roms.controller.volunteer;

/**
 * Model grouping the skill and it's related department.
 */
public class DepartmentSkillNameModel implements Comparable<DepartmentSkillNameModel> {

    private final String departmentName;
    private final String skillName;

    /**
     * Constructor.
     * @param departmentName name of the department the skill is linked to
     * @param skillName skill name
     */
    public DepartmentSkillNameModel(String departmentName, String skillName) {
        this.departmentName = departmentName;
        this.skillName = skillName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getSkillName() {
        return skillName;
    }

    @Override
    public int compareTo(DepartmentSkillNameModel other) {
        return this.toString().compareTo(other.toString());
    }

    @Override
    public String toString() {
        return departmentName + ": " + skillName;
    }
}
