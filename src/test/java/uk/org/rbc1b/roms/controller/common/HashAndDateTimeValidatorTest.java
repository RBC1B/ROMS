/*
 * The MIT License
 *
 * Copyright 2015 RBC1B.
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
package uk.org.rbc1b.roms.controller.common;

import java.util.Date;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for {@code HashAndDateTimeValidator}.
 *
 * @author rahulsingh
 */
public class HashAndDateTimeValidatorTest {
    private HashAndDateTimeValidator sut;

    /**
     * Prepare SUT.
     */
    @Before
    public void setUp() {
        sut = new HashAndDateTimeValidator();

        // set default values for properties of SUT
        sut.setDateTimeFormat("yyyyMMddHHmm");
        sut.setMaxTime(86400000);
        sut.setSalt("hs34hjjhsd23ja");

    }

    /**
     * The method {@code checkWithinTime} should return false
     * if the date time passed as an argument has expired.
     */
    @Test
    public void shouldReturnFalseWhenDateTimeHasExpiredMaxTime() {
        DateTime dt = new DateTime();
        dt = dt.minusDays(2);
        String dateTime = dt.toString(sut.getDateTimeFormat());

        // the original date time will be two days old
        assertFalse(sut.checkWithinTime(dateTime));
    }

    /**
     * The method {@code checkWithinTime} should return true
     * when the date time passed as an argument has not expired.
     */
    @Test
    public void shouldReturnTrueWhenDateTimeWithinMaxTimeLimit() {
        DateTime dt = new DateTime();
        dt = dt.minusHours(6);
        String dateTime = dt.toString(sut.getDateTimeFormat());

        assertTrue(sut.checkWithinTime(dateTime));
    }

    /**
     * The method {@code checkWithinTime} should return false
     * when the date time has just passed the max time by a minute.
     */
    @Test
    public void shouldReturnFalseWhenDateTimeIsJustOutsideTimeLimit() {
        DateTime dt = new DateTime();
        dt = dt.minusDays(1);
        String dateTime = dt.toString(sut.getDateTimeFormat());

        assertFalse(sut.checkWithinTime(dateTime));
    }

    /**
     * The method {@code checkWithinTime} should return true
     * when the date time is just within the max expiry time by a minute.
     */
    @Test
    public void shouldReturnTrueWhenDateTimeIsJustInsideTimeLimit() {
        DateTime dt = new DateTime();
        dt = dt.minusDays(1);
        dt = dt.plusMinutes(1);
        String dateTime = dt.toString(sut.getDateTimeFormat());

        assertTrue(sut.checkWithinTime(dateTime));
    }

    /**
     * With matching hash digests, the method {@code checkHash} should return
     * true.
     */
    @Test
    public void shouldReturnTrueForMatchingHash() {
        DateTime dt = new DateTime();

        String valueToBeHashed = dt.toString(sut.getDateTimeFormat()) + ":" + 5;
        String hash = HashGenerator.generateHash(valueToBeHashed, sut.getSalt());

        assertTrue(sut.checkHash(valueToBeHashed, hash));
    }

    /**
     * The method {@code checkHash} should return false when the value of the String
     * to be hashed changes.
     */
    @Test
    public void shouldReturnFalseForDifferentHashes() {
        DateTime dt = new DateTime();

        String valueToBeHashed = dt.toString(sut.getDateTimeFormat()) + ":" + 5;
        String hash = HashGenerator.generateHash(valueToBeHashed, sut.getSalt());
        dt = dt.minusDays(2);
        valueToBeHashed = dt.toString(sut.getDateTimeFormat()) + ":" + 5;

        assertFalse(sut.checkHash(valueToBeHashed, hash));
    }
}
