package ningbq.Constant;

import java.util.Calendar;

import ningbq.main.R;

import android.graphics.Typeface;

/**
 * 
 * @author Saurabh Solanki
 * @description : class used for common values used in project
 * 
 */
public class Constaints {
	public static double LATITUDE = 0;
	public static double LONGITUDE = 0;
	//模拟主页面的数据
	public static String[][] CITY = { { "Beijing", "12 miles" },
			{ "Shanghai", "1.5 miles" }, { "Guangzhou", "66 miles" },
			{ "Shenzhen", "57 miles" }, { "Hangzhou", "4.5 miles" },
			{ "Chengdu", "5.6 miles" }, { "Xi'an", "42 miles" },
			{ "Suzhou", "59 miles" }, { "Guilin", "90 miles" },
			{ "Chongqing", "60 miles" } };

	//模拟好友头像的数据
	public static int[] HEAD_IMAGES = { R.drawable.add_photo_without_cross,
			R.drawable.add_photo_without_cross,
			R.drawable.add_photo_without_cross,
			R.drawable.add_photo_without_cross };

	public static Typeface fontNormal = null;
	public static Typeface fontBold = null;
	public static String SIGNUPURL = "www.searchningbo.com/xml/signin.php";

	/**
	 * 
	 * @param latitude1
	 *            : latitude in double
	 * @param longitude1
	 *            : longitude in double
	 * @return Distance in Double
	 * @description find the distance of given place form current loaction
	 */
	public static double distance(double latitude1, double longitude1) {
		return (3959 * Math.acos(Math.cos(Math.toRadians(LATITUDE))
				* Math.cos(Math.toRadians(latitude1))
				* Math.cos(Math.toRadians(longitude1)
						- Math.toRadians(LONGITUDE))
				+ Math.sin(Math.toRadians(LATITUDE))
				* Math.sin(Math.toRadians(latitude1))));
	}

	public static String currentTime() {
		Calendar c = Calendar.getInstance();
		String curTime = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH)
				+ "-" + c.get(Calendar.DAY_OF_MONTH) + " "
				+ c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE)
				+ ":" + c.get(Calendar.SECOND);
		return curTime;
	}

}
