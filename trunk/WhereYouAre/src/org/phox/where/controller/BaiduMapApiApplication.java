package org.phox.where.controller;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKEvent;
import com.baidu.mapapi.MKGeneralListener;
import android.app.Application;
import android.widget.Toast;

public class BaiduMapApiApplication extends Application{

	static BaiduMapApiApplication baiduMapApp ;
	
	public BMapManager mBMapMgr = null ;
	
	public static final String BAIDU_KEY = "CB5CC089E3D8BFB17CF20A81FF235B35EBBC0A9A";
	
	public boolean m_bKeyRight = true;	// 授权Key正确，验证通过
	

	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
	public static class MyGeneralListener implements MKGeneralListener {
		@Override
		public void onGetNetworkState(int iError) {
			Toast.makeText(BaiduMapApiApplication.baiduMapApp.getApplicationContext(), "Check Network",
					Toast.LENGTH_LONG).show();
		}

		@Override
		public void onGetPermissionState(int iError) {
			if (iError ==  MKEvent.ERROR_PERMISSION_DENIED) {
				// 授权Key错误：
				Toast.makeText(BaiduMapApiApplication.baiduMapApp.getApplicationContext(), 
						"Check Key.",
						Toast.LENGTH_LONG).show();
				BaiduMapApiApplication.baiduMapApp.m_bKeyRight = false;
			}
		}
		
	}
	
	@Override
    public void onCreate() {
		baiduMapApp = this;
		mBMapMgr = new BMapManager(this);
		mBMapMgr.init(BAIDU_KEY, new MyGeneralListener());
		//super.onCreate();
	}
	@Override
	//建议在您app的退出之前调用mapadpi的destroy()函数，
	//避免重复初始化带来的时间消耗
	public void onTerminate() {
		if (mBMapMgr != null) {
			mBMapMgr.destroy();
			mBMapMgr = null;
		}
		super.onTerminate();
	}
	
}
