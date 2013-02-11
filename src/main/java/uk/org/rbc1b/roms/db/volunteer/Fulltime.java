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

    public static final Integer BETHEL = 1;
    public static final Integer PUBLISHER = 2;
    public static final Integer REGULAR_PIONEER = 3;
    private Integer fulltimeId;
    private String description;

    /**
     * Default constructor.
     */
    public Fulltime() {
        // do nothing
    }

    /**
     * Constructor.
     *
     * @param fulltimeId full time id
     */
    public Fulltime(Integer fulltimeId) {
        this.fulltimeId = fulltimeId;
    }

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
