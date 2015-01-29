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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Determines whether a given hash digest is invalid.
 * Determines whether a date time within a URI has expired as dependant on a
 * max time.
 *
 * @author rahulsingh
 */
public class HashAndDateTimeValidator {

    private String dateTimeFormat;
    private long maxTime;
    private String salt;

    /**
     * Check that the original date time is within the max time
     * of the current date time.
     *
     * @param originalTime original time
     * @return true if within accepted time frame, false otherwise
     */
    public boolean checkWithinTime(String originalTime) {
        final DateTime now = new DateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern(dateTimeFormat);
        DateTime then = formatter.parseDateTime(originalTime);
        long difference = now.getMillis() - then.getMillis();
        return maxTime > difference;
    }

    /**
     * Determine if the hash produced by the value parameter matches
     * the hash parameter.
     *
     * @param value value to be hashed
     * @param hash benchmark hash
     * @return true if the hash of value matches the hash in the second parameter
     */
    public boolean checkHash(String value, String hash) {
        String generatedHash = HashGenerator.generateHash(value, salt);

        return generatedHash.equalsIgnoreCase(hash);
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
