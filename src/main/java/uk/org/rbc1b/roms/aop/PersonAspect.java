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
package uk.org.rbc1b.roms.aop;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import uk.org.rbc1b.roms.db.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.org.rbc1b.roms.controller.LoggingHandlerExceptionResolver;

/**
 *
 * @author ramindursingh
 */
@Aspect
public class PersonAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingHandlerExceptionResolver.class);

    /**
     * Pointcut definition for Person table.
     *
     * @param person the person to capture
     */
    @Pointcut("execution(* uk.org.rbc1b.roms.db.PersonDao.updatePerson(..)) && args(person,..)")
    public void personChange(Person person) {
    }

    /**
     * Captures updates to Person table.
     *
     * @param person the person to save
     *
     */
    @Before("personChange(person)")
    public void capturePersonChange(Person person) {
        // Still to do lots here...
        LOGGER.error("Captured updatePerson. ID:" + person.getPersonId() + "; Name:" + person.getForename() + " " + person.getSurname() + ".");
    }
}
