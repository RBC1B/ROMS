/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

/**
 *
 * @author oliver.elder.esq
 */
public class Fulltime {

    private Integer fulltimeId;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFulltimeId() {
        return fulltimeId;
    }

    public void setFulltimeId(Integer fulltimeId) {
        this.fulltimeId = fulltimeId;
    }

    @Override
    public String toString() {
        return "Fulltime{" + "fulltimeId=" + fulltimeId + '}';
    }
}
