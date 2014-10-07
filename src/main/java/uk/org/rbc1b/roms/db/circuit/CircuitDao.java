/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.org.rbc1b.roms.db.circuit;

import java.util.List;
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
    @Transactional(readOnly = true)
    Circuit findCircuit(Integer circuitId);

    /**
     * Find all matching circuits.
     *
     * @return list of matching circuits
     */
    @Transactional(readOnly = true)
    List<Circuit> findCircuits();

    /**
     * Create a new circuit.
     *
     * @param circuit object to create
     */
    @Transactional
    void createCircuit(Circuit circuit);

    /**
     * Update a circuit using the matching Id.
     *
     * @param circuit object to update
     */
    @Transactional
    void updateCircuit(Circuit circuit);
}
