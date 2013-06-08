/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.io.Serializable;
import java.util.Date;

/**
 * Basic implementation of the auditable interface, supporting the auditable properties.
 *
 * @author oliver.elder.esq
 */
public class DefaultAuditable implements Auditable, Serializable {

    private Date updateTime;
    private Integer updatedBy;

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
}
