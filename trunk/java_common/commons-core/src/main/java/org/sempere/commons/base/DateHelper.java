/*
 * =============================================================================
 * Copyright by Benjamin Sempere,
 * All rights reserved.
 * =============================================================================
 * Author  : Benjamin Sempere
 * E-mail  : benjamin@sempere.org
 * Homepage: www.sempere.org
 * =============================================================================
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.sempere.commons.base;

import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Helper class that provides methods to work with dates and calendars.
 *
 * @author bsempere
 */
public final class DateHelper {

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    private DateHelper() {
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    /**
     * Formats a date using the given pattern
     *
     * @param date
     * @param pattern
     * @return String
     */
    public static String formatDate(Date date, String pattern) {
        String result = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            result = sdf.format(date);
        } catch (Exception e) {
            result = "";
        }

        return result;
    }

    /**
     * Parses a string into a date using the given pattern
     *
     * @param date
     * @param pattern
     * @return Date
     */
    public static Date parseDate(String date, String pattern) {
        Date result = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            result = sdf.parse(date);
        } catch (Exception e) {
            // Nothing to do here
        }

        return result;
    }

    /**
     * Formats a calendar using the given pattern
     *
     * @param calendar
     * @param pattern
     * @return String
     */
    public static String formatCalendar(Calendar calendar, String pattern) {
        String result = null;
        try {
            Date date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            result = sdf.format(date);
        } catch (Exception e) {
            result = "";
        }

        return result;
    }

    /**
     * Parses a string into a calendar using the given pattern
     *
     * @param dateString
     * @param pattern
     * @return Calendar
     */
    public static Calendar parseCalendar(String dateString, String pattern) {
        Calendar result = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date dateTmp = sdf.parse(dateString);
            result = Calendar.getInstance();
            result.setTime(dateTmp);
        } catch (Exception e) {
            // Nothing to do here
        }

        return result;
    }

    /**
     * Returns a calendar instance from a date one
     *
     * @param date
     * @return Calendar
     */
    public static Calendar getCalendarFromDate(Date date) {
        Calendar calendar = null;
        if (date != null) {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }

        return calendar;
    }

    /**
     * Returns a date instance from a calendar one
     *
     * @param calendar
     * @return Date
     */
    public static Date getDateFromCalendar(Calendar calendar) {
        Date date = null;
        if (calendar != null) {
            date = calendar.getTime();
        }

        return date;
    }

    /**
     * Indicates if a calendar instance is included in a specific interval
     *
     * @param calendar
     * @param fromCalendar
     * @param toCalendar
     * @return boolean
     */
    public static boolean isCalendarInCalendarsInterval(Calendar calendar, Calendar fromCalendar, Calendar toCalendar) {
        boolean res = false;
        if (calendar != null && fromCalendar != null && toCalendar != null) {
            if (calendar.getTimeInMillis() >= fromCalendar.getTimeInMillis() && calendar.getTimeInMillis() <= toCalendar.getTimeInMillis()) {
                res = true;
            }
        }

        return res;
    }

    /**
     * Indicates if a date instance is included in a specific interval
     *
     * @param date
     * @param fromDate
     * @param toDate
     * @return boolean
     */
    public static boolean isDateInDatesInterval(Date date, Date fromDate, Date toDate) {
        return isCalendarInCalendarsInterval(getCalendarFromDate(date), getCalendarFromDate(fromDate), getCalendarFromDate(toDate));
    }

    /**
     * Returns the midnight of a specific calendar
     *
     * @param calendar
     * @return Calendar
     */
    public static Calendar getMidnight(Calendar calendar) {
        return DateHelper.getCalendarFromDate(getMidnight(DateHelper.getDateFromCalendar(calendar)));
    }

    /**
     * Returns the midnight of a specific date
     *
     * @param date
     * @return Date date
     */
    public static Date getMidnight(Date date) {
        Date midnight = DateUtils.setHours(date, 0);
        midnight = DateUtils.setMinutes(midnight, 0);
        midnight = DateUtils.setSeconds(midnight, 0);
        midnight = DateUtils.setMilliseconds(midnight, 0);

        return midnight;
    }
}
