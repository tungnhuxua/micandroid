package org.javaside.cms.util;

import java.util.Calendar;

import org.apache.commons.lang.RandomStringUtils;

public class FileRule {
	//文件分隔符
	public static String SPT = System.getProperty("file.separator");
	//文件存放文件夹
	public static String fileRoot = SPT + "UploadImages";

	/**
	 * 按当前日期生产路径：/2008/5/20/，/年/月日/
	 * 
	 * @return
	 */
	public static String genFilePath() {
		StringBuilder sb = new StringBuilder();
		Calendar cal = Calendar.getInstance();
		sb.append(SPT).append(cal.get(Calendar.YEAR)).append(SPT).append(cal.get(Calendar.MONTH) + 1).append(SPT)
				.append(cal.get(Calendar.DAY_OF_MONTH)).append(SPT);
		return sb.toString();
	}

	/**
	 * 获得文件名 4位随机数加上当前时间
	 * 
	 * @return
	 */
	public static String genFileName() {
		String name = StrUtils.longToN36(System.currentTimeMillis());
		return RandomStringUtils.random(4, StrUtils.N36_CHARS) + name;
	}

}
