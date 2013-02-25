/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.circuit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import uk.org.rbc1b.roms.db.Person;

/**
 * Request form bean when creating/editing the circuit.
 *
 * @author oliver.elder.esq
 */
public class CircuitForm {

    private Integer circuitId;
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    private Person circuitOverseer = new Person();

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * @return the circuitId
     */
    public Integer getCircuitId() {
        return circuitId;
    }

    /**
     * @param circuitId the circuitId to set
     */
    public void setCircuitId(Integer circuitId) {
        this.circuitId = circuitId;
    }
}
