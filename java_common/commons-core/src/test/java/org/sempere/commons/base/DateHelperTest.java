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

import static junit.framework.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * Unit tests class for DateHelper class.
 * 
 * @author bsempere
 */
public class DateHelperTest {

	@Test
	public void formatDateWhenPatternIsNull() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.MONTH, Calendar.APRIL);
		calendar.set(Calendar.YEAR, 1980);
		Date date = DateHelper.getDateFromCalendar(calendar);

		String value = DateHelper.formatDate(date, null);
		assertEquals("", value);
	}

	@Test
	public void formatDateWhenPatternIsInvalid() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.MONTH, Calendar.APRIL);
		calendar.set(Calendar.YEAR, 1980);
		Date date = DateHelper.getDateFromCalendar(calendar);

		String value = DateHelper.formatDate(date, "invalid");
		assertEquals("", value);
	}

	@Test
	public void formatDateWhenDateIsNull() throws Exception {
		String value = DateHelper.formatDate(null, "dd.MM.yyyy");
		assertEquals("", value);
	}

	@Test
	public void formatDate() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.MONTH, Calendar.APRIL);
		calendar.set(Calendar.YEAR, 1980);
		Date date = DateHelper.getDateFromCalendar(calendar);

		String value = DateHelper.formatDate(date, "dd.MM.yyyy");
		assertEquals("25.04.1980", value);
	}

	@Test
	public void parseDateWhenPatternIsNull() throws Exception {
		Date date = DateHelper.parseDate("25.04.1980", null);
		assertNull(date);
	}

	@Test
	public void parseDateWhenPatternIsInvalid() throws Exception {
		Date date = DateHelper.parseDate("25.04.1980", "invalid");
		assertNull(date);
	}

	@Test
	public void parseDateWhenDateIsNull() throws Exception {
		Date date = DateHelper.parseDate(null, "dd.MM.yyyy");
		assertNull(date);
	}

	@Test
	public void parseDateWhenDateIsInvalid() throws Exception {
		Date date = DateHelper.parseDate("invalid", "dd.MM.yyyy");
		assertNull(date);
	}

	@Test
	public void parseDate() throws Exception {
		Date date = DateHelper.parseDate("25.04.1980", "dd.MM.yyyy");
		assertEquals("25.04.1980", DateHelper.formatDate(date, "dd.MM.yyyy"));
	}

	@Test
	public void formatCalendarWhenPatternIsNull() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.MONTH, Calendar.APRIL);
		calendar.set(Calendar.YEAR, 1980);

		String value = DateHelper.formatCalendar(calendar, null);
		assertEquals("", value);
	}

	@Test
	public void formatCalendarWhenPatternIsEmpty() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.MONTH, Calendar.APRIL);
		calendar.set(Calendar.YEAR, 1980);

		String value = DateHelper.formatCalendar(calendar, "");
		assertEquals("", value);
	}

	@Test
	public void formatCalendarWhenPatternIsInvalid() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.MONTH, Calendar.APRIL);
		calendar.set(Calendar.YEAR, 1980);

		String value = DateHelper.formatCalendar(calendar, "invalid");
		assertEquals("", value);
	}

	@Test
	public void formatCalendarWhenDateIsNull() throws Exception {
		String value = DateHelper.formatCalendar(null, "dd.MM.yyyy");
		assertEquals("", value);
	}

	@Test
	public void formatCalendar() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.MONTH, Calendar.APRIL);
		calendar.set(Calendar.YEAR, 1980);

		String value = DateHelper.formatCalendar(calendar, "dd.MM.yyyy");
		assertEquals("25.04.1980", value);
	}

	@Test
	public void parseCalendarWhenPatternIsNull() throws Exception {
		Calendar calendar = DateHelper.parseCalendar("25.04.1980", null);
		assertNull(calendar);
	}

	@Test
	public void parseCalendarWhenPatternIsEmpty() throws Exception {
		Calendar calendar = DateHelper.parseCalendar("25.04.1980", "");
		assertNull(calendar);
	}

	@Test
	public void parseCalendarWhenDateIsNull() throws Exception {
		Calendar calendar = DateHelper.parseCalendar(null, "dd.MM.yyyy");
		assertNull(calendar);
	}

	@Test
	public void parseCalendarWhenDateIsInvalid() throws Exception {
		Calendar calendar = DateHelper.parseCalendar("invalid", "dd.MM.yyyy");
		assertNull(calendar);
	}

	@Test
	public void parseCalendar() throws Exception {
		Calendar calendar = DateHelper.parseCalendar("25.04.1980", "dd.MM.yyyy");
		assertEquals("25.04.1980", DateHelper.formatCalendar(calendar, "dd.MM.yyyy"));
	}

	@Test
	public void getCalendarFromDateWhenDateIsNull() throws Exception {
		Calendar calendar = DateHelper.getCalendarFromDate(null);
		assertNull(calendar);
	}

	@Test
	public void getCalendarFromDate() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.MONTH, Calendar.APRIL);
		calendar.set(Calendar.YEAR, 1980);
		Date date = DateHelper.getDateFromCalendar(calendar);

		calendar = DateHelper.getCalendarFromDate(date);
		assertEquals(calendar.get(Calendar.DATE), 25);
		assertEquals(calendar.get(Calendar.MONTH), Calendar.APRIL);
		assertEquals(calendar.get(Calendar.YEAR), 1980);
	}

	@Test
	public void getDateFromCalendarWhenCalendarIsNull() throws Exception {
		Date date = DateHelper.getDateFromCalendar(null);
		assertNull(date);
	}

	@Test
	public void getDateFromCalendar() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.MONTH, Calendar.APRIL);
		calendar.set(Calendar.YEAR, 1980);

		Date date = DateHelper.getDateFromCalendar(calendar);
		calendar = DateHelper.getCalendarFromDate(date);
		assertEquals(calendar.get(Calendar.DATE), 25);
		assertEquals(calendar.get(Calendar.MONTH), Calendar.APRIL);
		assertEquals(calendar.get(Calendar.YEAR), 1980);
	}

	@Test
	public void isCalendarInCalendarsIntervalWhenCalendarIsNull() throws Exception {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.DATE, 25);
		startCalendar.set(Calendar.MONTH, Calendar.APRIL);
		startCalendar.set(Calendar.YEAR, 1980);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.DATE, 25);
		endCalendar.set(Calendar.MONTH, Calendar.APRIL);
		endCalendar.set(Calendar.YEAR, 1981);

		assertFalse(DateHelper.isCalendarInCalendarsInterval(null, startCalendar, endCalendar));
	}

	@Test
	public void isCalendarInCalendarsIntervalWhenStartCalendarIsNull() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 11);
		calendar.set(Calendar.MONTH, Calendar.AUGUST);
		calendar.set(Calendar.YEAR, 1980);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.DATE, 25);
		endCalendar.set(Calendar.MONTH, Calendar.APRIL);
		endCalendar.set(Calendar.YEAR, 1981);

		assertFalse(DateHelper.isCalendarInCalendarsInterval(calendar, null, endCalendar));
	}

	@Test
	public void isCalendarInCalendarsIntervalWhenEndCalendarIsNull() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 11);
		calendar.set(Calendar.MONTH, Calendar.AUGUST);
		calendar.set(Calendar.YEAR, 1980);

		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.DATE, 25);
		startCalendar.set(Calendar.MONTH, Calendar.APRIL);
		startCalendar.set(Calendar.YEAR, 1980);

		assertFalse(DateHelper.isCalendarInCalendarsInterval(calendar, startCalendar, null));
	}

	@Test
	public void isCalendarInCalendarsIntervalWhenCalendarIsEqualsToStartCalendar() throws Exception {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.DATE, 25);
		startCalendar.set(Calendar.MONTH, Calendar.APRIL);
		startCalendar.set(Calendar.YEAR, 1980);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.DATE, 25);
		endCalendar.set(Calendar.MONTH, Calendar.APRIL);
		endCalendar.set(Calendar.YEAR, 1981);

		assertTrue(DateHelper.isCalendarInCalendarsInterval(startCalendar, startCalendar, endCalendar));
	}

	@Test
	public void isCalendarInCalendarsIntervalWhenCalendarIsEqualsToEndCalendar() throws Exception {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.DATE, 25);
		startCalendar.set(Calendar.MONTH, Calendar.APRIL);
		startCalendar.set(Calendar.YEAR, 1980);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.DATE, 25);
		endCalendar.set(Calendar.MONTH, Calendar.APRIL);
		endCalendar.set(Calendar.YEAR, 1981);

		assertTrue(DateHelper.isCalendarInCalendarsInterval(endCalendar, startCalendar, endCalendar));
	}

	@Test
	public void isCalendarInCalendarsInterval() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 11);
		calendar.set(Calendar.MONTH, Calendar.AUGUST);
		calendar.set(Calendar.YEAR, 1980);

		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.DATE, 25);
		startCalendar.set(Calendar.MONTH, Calendar.APRIL);
		startCalendar.set(Calendar.YEAR, 1980);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.DATE, 25);
		endCalendar.set(Calendar.MONTH, Calendar.APRIL);
		endCalendar.set(Calendar.YEAR, 1981);

		assertTrue(DateHelper.isCalendarInCalendarsInterval(calendar, startCalendar, endCalendar));
	}

	@Test
	public void isCalendarInCalendarsIntervalWhenCalendarIsOutsideInterval() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 11);
		calendar.set(Calendar.MONTH, Calendar.AUGUST);
		calendar.set(Calendar.YEAR, 1983);

		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.DATE, 25);
		startCalendar.set(Calendar.MONTH, Calendar.APRIL);
		startCalendar.set(Calendar.YEAR, 1980);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.DATE, 25);
		endCalendar.set(Calendar.MONTH, Calendar.APRIL);
		endCalendar.set(Calendar.YEAR, 1981);

		assertFalse(DateHelper.isCalendarInCalendarsInterval(calendar, startCalendar, endCalendar));
	}

	@Test
	public void isDateInDatesIntervalWhenDateIsNull() throws Exception {
		Date startDate = DateHelper.parseDate("25.04.1980", "dd.MM.yyyy");
		Date endDate = DateHelper.parseDate("25.04.1981", "dd.MM.yyyy");

		assertFalse(DateHelper.isDateInDatesInterval(null, startDate, endDate));
	}

	@Test
	public void isDateInDatesIntervalWhenStartDateIsNull() throws Exception {
		Date startDate = DateHelper.parseDate("25.04.1980", "dd.MM.yyyy");
		Date endDate = DateHelper.parseDate("25.04.1981", "dd.MM.yyyy");

		assertFalse(DateHelper.isDateInDatesInterval(null, startDate, endDate));
	}

	@Test
	public void isDateInDatesIntervalWhenEndDateIsNull() throws Exception {
		Date startDate = DateHelper.parseDate("25.04.1980", "dd.MM.yyyy");
		Date date = DateHelper.parseDate("11.08.1980", "dd.MM.yyyy");

		assertFalse(DateHelper.isDateInDatesInterval(date, startDate, null));
	}

	@Test
	public void isDateInDatesIntervalWhenDateIsEqualsToStartDate() throws Exception {
		Date startDate = DateHelper.parseDate("25.04.1980", "dd.MM.yyyy");
		Date endDate = DateHelper.parseDate("25.04.1981", "dd.MM.yyyy");

		assertTrue(DateHelper.isDateInDatesInterval(startDate, startDate, endDate));
	}

	@Test
	public void isDateInDatesIntervalWhenDateIsEqualsToEndDate() throws Exception {
		Date startDate = DateHelper.parseDate("25.04.1980", "dd.MM.yyyy");
		Date endDate = DateHelper.parseDate("25.04.1981", "dd.MM.yyyy");

		assertTrue(DateHelper.isDateInDatesInterval(endDate, startDate, endDate));
	}

	@Test
	public void isDateInDatesInterval() throws Exception {
		Date startDate = DateHelper.parseDate("25.04.1980", "dd.MM.yyyy");
		Date endDate = DateHelper.parseDate("25.04.1981", "dd.MM.yyyy");
		Date date = DateHelper.parseDate("11.08.1980", "dd.MM.yyyy");

		assertTrue(DateHelper.isDateInDatesInterval(date, startDate, endDate));
	}

	@Test
	public void isDateInDatesIntervalWhenDateIsOutsideInterval() throws Exception {
		Date startDate = DateHelper.parseDate("25.04.1980", "dd.MM.yyyy");
		Date endDate = DateHelper.parseDate("25.04.1981", "dd.MM.yyyy");
		Date date = DateHelper.parseDate("11.08.1983", "dd.MM.yyyy");

		assertFalse(DateHelper.isDateInDatesInterval(date, startDate, endDate));
	}

	@Test
	public void getMidnightCalendar() throws Exception {
		Calendar calendar = DateHelper.parseCalendar("25.04.1980 21:15:37:200", "dd.MM.yyyy HH:mm:ss:SSS");
		assertEquals("25.04.1980 00:00:00:000", DateHelper.formatCalendar(DateHelper.getMidnight(calendar), "dd.MM.yyyy HH:mm:ss:SSS"));
	}

	@Test
	public void getMidnightDate() throws Exception {
		Date date = DateHelper.parseDate("25.04.1980 21:15:37:200", "dd.MM.yyyy HH:mm:ss:SSS");
		assertEquals("25.04.1980 00:00:00:000", DateHelper.formatDate(DateHelper.getMidnight(date), "dd.MM.yyyy HH:mm:ss:SSS"));
	}
}
