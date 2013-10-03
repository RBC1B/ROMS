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
package uk.org.rbc1b.roms.controller.personchange;

import uk.org.rbc1b.roms.db.PersonChange;

/**
 * Generates the models for changes to person.
 *
 * @author ramindursingh
 */
public class PersonChangeModelFactory {

    private static final String BASE_URI = "/personchanges";

    /**
     * Generates the uri used to update the table.
     *
     * @param personChangeId personchange id
     * @return uri
     */
    public static String generatorUri(Integer personChangeId) {
        return personChangeId != null ? BASE_URI + "/" + personChangeId : BASE_URI;
    }

    /**
     * Create the model.
     *
     * @param personChange personChange
     * @return model
     */
    public PersonChangeModel generatePersonChangeModel(PersonChange personChange) {
        PersonChangeModel model = new PersonChangeModel();
        return model;
    }
}
