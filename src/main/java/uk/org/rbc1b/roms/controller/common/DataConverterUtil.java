/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.common;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * Utility class for data conversions.
 *
 * @author oliver.elder.esq
 */
public final class DataConverterUtil {

    private DataConverterUtil() {
        // hidden constructor for final class
    }

    /**
     * Convert a java.sql.Date into a joda datetime object.
     *
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
     * @return  sql date, or null if not set
     */
    public static java.sql.Date toSqlDate(DateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return new java.sql.Date(dateTime.toDateMidnight().getMillis());
    }
}
