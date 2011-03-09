package team1.videoplay.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {

	public static String formatDate(Date date) {
		String strDate = "";
		//Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
		strDate = sdf.format(date);
		return strDate;
	}
	
/*	public static void main(String[] args) {
		
		System.out.println(new FormatDate().formatDate(new Date()));
	}
*/
	

}
