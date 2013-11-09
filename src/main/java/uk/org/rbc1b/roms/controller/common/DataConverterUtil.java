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
package uk.org.rbc1b.roms.controller.common;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * Utility class for data conversions.
 * @author oliver.elder.esq
 */
public final class DataConverterUtil {

    private DataConverterUtil() {
        // hidden constructor for final class
    }

    /**
     * Convert a java.sql.Date into a joda datetime object.
     * @param date incoming date
     * @return converted date, or null
     */
    public static DateTime toDateTime(java.sql.Date date) {
        if (date == null) {
            return null;
        }

        return LocalDate.fromDateFields(date).toDateTimeAtStartOfDay();
    }

    /**
     * Convert a joda DateTime object into a java.sql.Date, set at midnight.
     * @param dateTime incoming date time
     * @return sql date, or null if not set
     */
    public static java.sql.Date toSqlDate(DateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return new java.sql.Date(dateTime.toDateMidnight().getMillis());
    }

    /**
     * Convert a joda time value into a java util date.
     * @param dateTime incoming date time
     * @return date, or null if not set
     */
    public static java.util.Date toDate(DateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return new java.util.Date(dateTime.getMillis());
    }

    /**
     * Convert the string to an integer.
     * @param value string value
     * @return integer, or null if the value is null or empty
     */
    public static Integer toInteger(String value) {
        String trimmed = StringUtils.trimToNull(value);
        if (trimmed == null) {
            return null;
        }
        try {
            return Integer.valueOf(trimmed);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to convert [" + value + "] to an Integer");
        }
    }

}
