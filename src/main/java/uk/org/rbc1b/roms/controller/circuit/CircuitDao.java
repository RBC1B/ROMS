/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.circuit;

import java.util.List;
import uk.org.rbc1b.roms.db.circuit.Circuit;

/**
 * Look up circuit information.
 *
 * @author oliver.elder.esq
 */
public interface CircuitDao {

    /**
     * Find the circuit matching the id, or null if no match.
     *
     * @param circuitId id
     * @return circuit
     */
    Circuit findCircuit(Integer circuitId);

    /**
     * Find all matching circuits.
     *
     * @return list of matching circuits
     */
    List<Circuit> findCircuits();

    /**
     * Update or create a new circuit using the matching Id.
     *
     * @param circuit object to update
     */
    void saveCircuit(Circuit circuit);
}
