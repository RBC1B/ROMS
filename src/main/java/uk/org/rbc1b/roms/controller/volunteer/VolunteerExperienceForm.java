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

import javax.validation.constraints.NotNull;

/**
 *
 * @author ramindursingh
 */
public class VolunteerExperienceForm {

    private Integer volunteerTradeId;
    @NotNull
    private String name;
    @NotNull
    private String experienceDescription;
    @NotNull
    private String experienceYears;

    /**
     * @return the volunteerTradeId
     */
    public Integer getVolunteerTradeId() {
        return volunteerTradeId;
    }

    /**
     * @param volunteerTradeId the volunteerTradeId to set
     */
    public void setVolunteerTradeId(Integer volunteerTradeId) {
        this.volunteerTradeId = volunteerTradeId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the experienceDescription
     */
    public String getExperienceDescription() {
        return experienceDescription;
    }

    /**
     * @param experienceDescription the experienceDescription to set
     */
    public void setExperienceDescription(String experienceDescription) {
        this.experienceDescription = experienceDescription;
    }

    /**
     * @return the experienceYears
     */
    public String getExperienceYears() {
        return experienceYears;
    }

    /**
     * @param experienceYears the experienceYears to set
     */
    public void setExperienceYears(String experienceYears) {
        this.experienceYears = experienceYears;
    }
}
