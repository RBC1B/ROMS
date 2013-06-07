/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.util.Date;

/**
 * Interface used to defined tables that track who last updated them, and when.
 * @author oliver.elder.esq
 */
public interface Auditable {

    /**
     * @return timestamp of the last time the row was updated.
     */
    Date getUpdateTime();

    /**
     * @return user id of the last person to update the row.
     */
    Integer getUpdatedBy();

}
