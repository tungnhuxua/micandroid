 /*
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */

package org.light.portal.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 
 * @author Jianmin Liu
 **/
public class DateUtil {
    
	public static final String _FORMAT_MM_dd_yyyy = "MM/dd/yyyy";
	public static final String _FORMAT_yyyy_MM_dd = "yyyy/MM/dd";
    
	public static String format(Date date){
    	return format(date,getLocale(),PropUtil.getString("default.date.format"));        
    }
	
	public static String format(Date date,String format){
		return format(date,getLocale(),format);
    }
	
	public static String format(Date date,Locale locale,String format){
		return format(date,locale,format,getTimeZone());       
    }
	
	public static String format(Date date,Locale locale,String format, TimeZone timeZone){
		String value = null;
        try{
        	if(locale == null) locale = Locale.getDefault();
        	if(timeZone == null) timeZone = getTimeZone();
        	if(StringUtil.isEmpty(format)) format = _FORMAT_MM_dd_yyyy;
        	 SimpleDateFormat formatter = (SimpleDateFormat)DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG, locale);
             formatter.applyPattern(format);
             formatter.setTimeZone(timeZone);
             value= formatter.format(date);
        }catch(Exception e){
        	
        }
        return value;
       
    }
	
	public static Date parse(String dateString,String format){
		return parse(dateString,format,getLocale(),getTimeZone());
	}
	
	public static Date parse(String dateString,String format,Locale locale,TimeZone timeZone){
		SimpleDateFormat formatter = (SimpleDateFormat)DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG, locale);
        formatter.applyPattern(format);
        formatter.setTimeZone(timeZone);
		Date date = null;
		try{
			date = formatter.parse(dateString);
		}catch(Exception e){
		}
		return date;
	}
	
	private static Locale getLocale(){
		return new Locale(PropUtil.getString("default.locale"));
	}
	private static TimeZone getTimeZone(){
		TimeZone timeZone = null;
		try{
			timeZone = TimeZone.getTimeZone(PropUtil.getString("default.timezone"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return timeZone;
	}
	public static String calculateDifference(Date a, Date b)
	{   
		
		int days = calculateDifferenceDays(a,b);
		if(days > 1){
			return days+" days ago";
		}
		else if(days == 1){
			int hours = calculateDifferenceHours(a,b);
			if(hours >= 0){
				return 1+" day ago";
			}else{
				return 24+hours+" hours ago";
			}
		}
		else{
			int hours = calculateDifferenceHours(a,b);
			if(hours > 1){
				return hours+" hours ago";
			}
			else if(hours == 1){
				int minutes = calculateDifferenceMinutes(a,b);	
				if(minutes >= 0){
					return 1+" hour ago";
				}else{
					return 60+minutes+" minutes ago";
				}
			}
			else{
				int minutes = calculateDifferenceMinutes(a,b);				
				if(minutes > 1){
					return minutes+" minutes ago";
				}
				else if(minutes == 1){
					return 1+" minute ago";
				}
				else{
					return "few seconds ago";
				}
			}
		}
//		
//		int yearDifference = 0;
//	    int tempDifference = 0;
//	    int hourDifference = 0;
//	    StringBuffer difference = new StringBuffer();
//	    Calendar earlier = Calendar.getInstance();
//	    Calendar later = Calendar.getInstance();
//	 
//	    if (a.compareTo(b) < 0)
//	    {
//	        earlier.setTime(a);
//	        later.setTime(b);
//	    }
//	    else
//	    {
//	        earlier.setTime(b);
//	        later.setTime(a);
//	    }
//	 	    
//	    yearDifference = later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR);
//        if(yearDifference > 1)
//        	difference.append(yearDifference+" years ");
//        else if(yearDifference == 1)
//        	difference.append(yearDifference+" year ");
//	     	 	   
//        tempDifference = later.get(Calendar.DAY_OF_YEAR) - earlier.get(Calendar.DAY_OF_YEAR);
//        if(tempDifference > 1)
//        	difference.append(tempDifference+" days ");
//        else if(tempDifference == 1)
//        	difference.append(tempDifference+" day ");
//        else if(yearDifference == 0){
//        	hourDifference = later.get(Calendar.HOUR_OF_DAY) - earlier.get(Calendar.HOUR_OF_DAY);
//	        
//	        tempDifference = later.get(Calendar.MINUTE) - earlier.get(Calendar.MINUTE);
//	        if(tempDifference > 0){
//	        	 if(hourDifference > 1)
//	 	        	difference.append(hourDifference+" hours ");
//	 	         else if(hourDifference == 1)
//	 	        	difference.append(hourDifference+" hour ");
//	        	 if(tempDifference == 1)
//	        		 difference.append(tempDifference+" minute ");
//	        	 else
//	        		 difference.append(tempDifference+" minutes ");
//	        }	        	
//	        else if(tempDifference < 0){
//	        	hourDifference--;
//	        	if(hourDifference > 1)
//	 	        	difference.append(hourDifference+" hours ");
//	 	        else if(hourDifference == 1)
//	 	        	difference.append(hourDifference+" hour ");
//	        	tempDifference = 60 + tempDifference;
//	        	if(tempDifference == 1)
//	        		 difference.append(tempDifference+" minute ");
//	        	 else
//	        		 difference.append(tempDifference+" minutes ");
//	        }
//        }
//        difference.append("ago");
//	    return difference.toString();
	}

	public static int calculateDifferenceDays(Date a, Date b)
	{
	    int tempDifference = 0;
	    int difference = 0;
	    Calendar earlier = Calendar.getInstance();
	    Calendar later = Calendar.getInstance();
	 
	    if (a.compareTo(b) < 0)
	    {
	        earlier.setTime(a);
	        later.setTime(b);
	    }
	    else
	    {
	        earlier.setTime(b);
	        later.setTime(a);
	    }
	 
	    while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR))
	    {
	        tempDifference = 365 * (later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR));
	        difference += tempDifference;
	 
	        earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
	    }
	 
	    if (earlier.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR))
	    {
	        tempDifference = later.get(Calendar.DAY_OF_YEAR) - earlier.get(Calendar.DAY_OF_YEAR);
	        difference += tempDifference;
	    }
	 
	    return difference;
	}

	public static int calculateDifferenceHours(Date a, Date b)
	{
	    int difference = 0;
	    Calendar earlier = Calendar.getInstance();
	    Calendar later = Calendar.getInstance();
	 
	    if (a.compareTo(b) < 0)
	    {
	        earlier.setTime(a);
	        later.setTime(b);
	    }
	    else
	    {
	        earlier.setTime(b);
	        later.setTime(a);
	    }
	 
	    difference = later.get(Calendar.HOUR_OF_DAY) - earlier.get(Calendar.HOUR_OF_DAY);
	 
	    return difference;
	}
	
	public static int calculateDifferenceMinutes(Date a, Date b)
	{
	    int difference = 0;
	    Calendar earlier = Calendar.getInstance();
	    Calendar later = Calendar.getInstance();
	 
	    if (a.compareTo(b) < 0)
	    {
	        earlier.setTime(a);
	        later.setTime(b);
	    }
	    else
	    {
	        earlier.setTime(b);
	        later.setTime(a);
	    }
	 
	    difference = later.get(Calendar.MINUTE) - earlier.get(Calendar.MINUTE);
	 
	    return difference;
	}
}
