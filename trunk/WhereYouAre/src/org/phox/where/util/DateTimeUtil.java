package org.phox.where.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	
	private static final int SECOND = 1000;
	private static final int MINUTE = 60 * SECOND;
	private static final int HOUR = 60 * MINUTE;
	private static final int DAY = 24 * HOUR;
	private static final int MONTH = 30 * DAY;
	
	public static String getFuzzyTime(long interval)
	{
		if (interval < 1 * MINUTE)
		{
			return (interval / SECOND) <= 1 ? "a few seconds" : "" + (interval / SECOND + 1) + " seconds";
		}
		if (interval < 45 * MINUTE)
		{
			return "" + (interval / MINUTE + 1) + " minutes";
		}
		if (interval < 24 * HOUR)
		{
			return "" + (interval / HOUR + 1) + " hours";
		}
		if (interval < 30 * DAY)
		{
			return "" + (interval / DAY + 1) + " days";
		}
		
		return "a really long time";
	}
	
	public static String getFuzzyTimeDelta(long before, long after)
	{
		long delta = after - before;

		if (delta < 24 * HOUR)
		{
			return "today";
		}
		if (delta < 48 * HOUR)
		{
			return "yesterday";
		}
		if (delta < 30 * DAY)
		{
			return "" + (delta / DAY) + " days ago";
		}
		if (delta < 12 * MONTH)
		{
			long months = delta / MONTH;
			return months <= 1 ? "one month ago" : months + " months ago";
		}
		else
		{
			long years = (delta / MONTH) / 12;
			return years <= 1 ? "one year ago" : years + " years ago";
		}
	}

	public static String getFuzzyTimeDelta(long before, long after, long maxDelta)
	{
		long delta = after - before;
		
		if (delta < maxDelta) return getFuzzyTimeDelta(before, after);
		
		return new SimpleDateFormat("M/d/yy").format(new Date(after));
	}
}
