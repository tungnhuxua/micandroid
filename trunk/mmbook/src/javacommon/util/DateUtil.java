package javacommon.util;

/**
 *  
 * 完成日常日期转换
 */

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.sql.Timestamp;
import java.util.Date;

public class DateUtil {

	public static String dateFormat(Date date) {
		String result = null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (date == null) {
			return "";
		} else {
			result = sd.format(date);
		}
		return result;
	}
	/**
	 * 按日期生成文件名称
	 * @return
	 */
	public static String createFileName(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmm");
		String mDateTime = formatter.format(cal.getTime());
		String newfilename = mDateTime + RandomNum.getRandom();
		return newfilename;
	}
	/**
	 * 按日期生成文件名称
	 * @return
	 */
	public static String createFileNameNew(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
		String mDateTime = formatter.format(cal.getTime());
		String newfilename = mDateTime + RandomNum.getRandom();
		return newfilename;
	}	
	/**
	 * 根据格式获得日期字符串 
	 * @return
	 */
	public static String getDateStr(String sFormat) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		String nowTime = sdf.format(now);
		return (nowTime);
	}

	public static Date getDate(String date) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date result = null;
		try {
			result = sd.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	public static String getDate() {
		Calendar cal  = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mDateTime=formatter.format(cal.getTime());
		return mDateTime;
	}
	
	public static void main(String[] args){
		System.out.println(getDateStr("yyyymmdd"));
	}
}
