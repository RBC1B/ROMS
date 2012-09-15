package uk.org.rbc1b.roms.db.congregation;

/**
 * @author oliver.elder.esq
 */
public class Circuit {

    private String name;
    private Integer circuitId;

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
}
