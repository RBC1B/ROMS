/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

/**
 *
 * @author oliver.elder.esq
 */
public class MaritalStatus {

    public static final MaritalStatus SINGLE = new MaritalStatus(5);
    private Integer maritalStatusId;
    private String description;

    /**
     * Default constructor.
     */
    public MaritalStatus() {
        // do nothing
    }

    /**
     * Constructor.
     *
     * @param maritalStatusId id
     */
    public MaritalStatus(Integer maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Integer maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    @Override
    public String toString() {
        return "MaritalStatus{" + "maritalStatusId=" + maritalStatusId + '}';
    }
}
