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
package uk.org.rbc1b.roms.controller.circuit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.circuit.Circuit;

/**
 * Generate circuit models.
 */
@Component
public class CircuitModelFactory {
    private static final String BASE_CIRCUIT_URI = "/circuits";
    @Autowired
    private PersonModelFactory personModelFactory;
    @Autowired
    private PersonDao personDao;

    /**
     * Generate the uri used to access the circuit pages.
     * @param circuitId optional circuit id
     * @return uri
     */
    public static String generateCircuitUri(Integer circuitId) {
        return circuitId != null ? BASE_CIRCUIT_URI + "/" + circuitId : BASE_CIRCUIT_URI;
    }

    /**
     * Generate a new circuit model.
     * @param circuit circuit
     * @return model
     */
    public CircuitModel generateCircuitModel(Circuit circuit) {

        CircuitModel model = new CircuitModel();
        model.setCircuitId(circuit.getCircuitId());

        if (circuit.getCircuitOverseer() != null) {
            Person person = personDao.findPerson(circuit.getCircuitOverseer().getPersonId());
            model.setCircuitOverseer(personModelFactory.generatePersonModel(person));
        }

        model.setEditUri(generateCircuitUri(circuit.getCircuitId()) + "/edit");
        model.setName(circuit.getName());
        model.setUri(generateCircuitUri(circuit.getCircuitId()));

        return model;
    }

}
