package uk.org.rbc1b.roms.db.circuit;

import uk.org.rbc1b.roms.db.Person;

/**
 * @author oliver.elder.esq
 */
public class Circuit {

    private String name;
    private Integer circuitId;
    private Person circuitOverseer;

    public Integer getCircuitId() {
        return circuitId;
    }

    public void setCircuitId(Integer circuitId) {
        this.circuitId = circuitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Circuit{" + "circuitId=" + circuitId + '}';
    }

    /**
     * @return the circuitOverseer
     */
    public Person getCircuitOverseer() {
        return circuitOverseer;
    }

    /**
     * @param circuitOverseer the circuitOverseer to set
     */
    public void setCircuitOverseer(Person circuitOverseer) {
        this.circuitOverseer = circuitOverseer;
    }
}
