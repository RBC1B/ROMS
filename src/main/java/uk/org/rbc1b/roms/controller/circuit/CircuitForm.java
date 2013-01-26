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

    @NotNull
    @Size(max = 50)
    private String name;
    private Person person = new Person();

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
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}
