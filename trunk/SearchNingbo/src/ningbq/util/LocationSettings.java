package ningbq.util;

import android.content.Intent;
import android.location.Criteria;


/**
 * Description:设置定位的参数.
 *
 * @author Devon.Ning
 * @2012-2-16
 *
 */
public class LocationSettings {

	public static Criteria getDefaultCriteriaSetting() {
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		criteria.setAltitudeRequired(false);
		criteria.setSpeedRequired(false);
		criteria.setBearingRequired(false);
		return criteria;
	}

	/**
	 * 位置更新最短时间和距离的默认设置
	 * 
	 * @param intent
	 */
	public static void getLocatUpdtSetting(Intent intent) {
		intent.putExtra("minTime", 3000L);
		intent.putExtra("minDistance", 10.0F);
	}

}
