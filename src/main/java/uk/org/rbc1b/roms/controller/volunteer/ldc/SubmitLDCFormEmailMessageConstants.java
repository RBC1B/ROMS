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
package uk.org.rbc1b.roms.controller.volunteer.ldc;

/**
 * Enumeration representing the message constants that should go into the Submit
 * LDC form notification email.
 *
 * @author rahulsingh
 */
public enum SubmitLDCFormEmailMessageConstants {

    FORM_DATE_UNKNOWN("unknown"),
    FORM_DATE_TWO_HALF_YRS("approaching the three year renewal period"),
    FORM_DATE_THREE_YRS("older than three years");

    private final String message;

    /**
     * Private constructor to prevent instantiation.
     *
     * @param message the message
     */
    private SubmitLDCFormEmailMessageConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
