/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.qualification;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request form bean when creating/editing the name.
 *
 * @author Tina
 */
public class QualificationForm {

    private Integer qualificationId;
    @NotNull
    @Size(max = 50)
    private String name;
    @Size(max = 150)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the qualificationId
     */
    public Integer getQualificationId() {
        return qualificationId;
    }

    /**
     * @param qualificationId the qualificationId to set
     */
    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
    }
}
