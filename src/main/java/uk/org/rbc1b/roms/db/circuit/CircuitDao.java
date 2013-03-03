/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.circuit;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

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
    @PreAuthorize("hasPermission('CIRCUIT', 'READ')")
    @Transactional(readOnly = true)
    Circuit findCircuit(Integer circuitId);

    /**
     * Find all matching circuits.
     *
     * @return list of matching circuits
     */
    @PreAuthorize("hasPermission('CIRCUIT', 'READ')")
    @Transactional(readOnly = true)
    List<Circuit> findCircuits();

    /**
     * Update or create a new circuit using the matching Id.
     *
     * @param circuit object to update
     */
    @PreAuthorize("hasPermission('CIRCUIT', 'EDIT')")
    @Transactional
    void saveCircuit(Circuit circuit);
}
