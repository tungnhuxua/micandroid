package ningbq.application;

import java.util.ArrayList;
import java.util.List;

import ningbq.bean.Addresx;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

/**
 * 继承Service覆写方法 即完成了一个自定义的服务 本服务的作用是每3秒, 
 * 监听50米距离的位置变化 一旦位置改变 就广播发送数据
 * (绑定的Activity可以接受)
 * 
 */
public class MyLBSService extends Service {

	public static final String LOCATION_UPDATE = "Location_Update";
	public static final String LBS = Context.LOCATION_SERVICE;
	// 用于与Activity绑定
	private final IBinder binder = new MyBinder();
	private LocationManager lbsManager = null;
	private Location currLocate = null;
	// 记录得到的地址位置列表数据
	private List<Addresx> AddressList = null;

	private boolean isRunning = false;
	private String iProvider = null;
	public double globleLatitude  = 0.0 ;
	public double globleLongitude = 0.0 ;

	@Override
	public void onCreate() {
		super.onCreate();
		this.lbsManager = (LocationManager) getSystemService(LBS);
		this.iProvider = this.lbsManager.getBestProvider(getDefaultCriteria(),
				true);
		this.AddressList = new ArrayList<Addresx>();

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onStart(android.content.Intent, int)
	 * 
	 *      在Service启动时获取用户设置, 得到最合适的位置提供器
	 */
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		this.isRunning = true;
		long minTime = 3000L;
		float minDistance = 50F;
		if (intent != null) {
			minTime = intent.getLongExtra("minTime", 3000L);
			minDistance = intent.getFloatExtra("minDistance", 50F);
		}
		this.lbsManager.requestLocationUpdates(this.iProvider, minTime, minDistance,
				new LBSListener());

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return this.binder;
	}

	public class MyBinder extends Binder {
		// 返回当前Service对象 为了使Activity中的成员得到Service的引用
		// 来使用Service中的public方法
		public MyLBSService getService() {
			return MyLBSService.this;
		}
	}
	
	

	@Override
	public void onDestroy() {
		this.isRunning = false ;
		super.onDestroy();
	}

	class LBSListener implements LocationListener {

		// 监听器发现位置改变时 回调
		@Override
		public void onLocationChanged(Location location) {
			currLocate = location ;
			Intent intent = new Intent(LOCATION_UPDATE);
			intent.putExtra("Lat", location.getLatitude());
			intent.putExtra("Lng", location.getLongitude());
			
			globleLatitude  = location.getLatitude() ;
			globleLongitude = location.getLongitude() ;
			// 广播数据
			sendBroadcast(intent);
			addNewLocation() ;
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			currLocate = null ;

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

	}

	public static final Criteria getDefaultCriteria() {
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		criteria.setAltitudeRequired(false);
		criteria.setSpeedRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(false);
		return criteria;
	}

	private void addNewLocation() {
		Addresx address = new Addresx(this.currLocate);
		this.AddressList.add(address);
	}

	public List<Addresx> getAddressList() {
		return this.AddressList;
	}

	public boolean isRunning() {
		return this.isRunning;
	}

	public Location getNowLocation() {
		return this.currLocate;
	}

}
