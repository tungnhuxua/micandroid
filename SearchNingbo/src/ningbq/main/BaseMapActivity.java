package ningbq.main;

import ningbq.address.LocationAddressListScreen;
import ningbq.application.BaiduMapApiApplication;
import ningbq.main.widget.MyHorizontalScrollView;
import ningbq.main.widget.MyHorizontalScrollView.SizeCallback;
import ningbq.search.FindScreen;
import ningbq.search.SearchFirstCategoryScreen;
import ningbq.util.VibratorUtil;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapView;

public abstract class BaseMapActivity extends MapActivity implements OnClickListener{

	public MyHorizontalScrollView scrollView;
	public View menuView; // mainScreen
	public View appView; // appScreen
	public ViewGroup viewGroup; // button headBar
	public View[] children;
	public MapView mapView;
	public BaiduMapApiApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		LayoutInflater inflater = LayoutInflater.from(this);
		scrollView = (MyHorizontalScrollView) inflater.inflate(
				R.layout.indexpageview, null);
		setContentView(scrollView);
		menuView = inflater.inflate(R.layout.mainscreen, null);
		appView = inflater.inflate(getApplicationLayoutResource(), null);
		viewGroup = (ViewGroup)appView.findViewById(getViewGroupResource());
		//children = new View[] { menuView, appView };

		app = (BaiduMapApiApplication) this.getApplication();
		if (app.mBMapMgr == null) {
			app.mBMapMgr = new BMapManager(this.getApplicationContext());
			app.mBMapMgr.init(BaiduMapApiApplication.BAIDU_KEY,
					new BaiduMapApiApplication.MyGeneralListener());
		}
		app.mBMapMgr.start();
		initMapActivity(app.mBMapMgr);
		mapView = (MapView)findViewById(getMapViewResource()) ;
	}
	
	@Override
	public void onClick(View v) {
		
		VibratorUtil.setVibrator(v.getContext());
		switch (v.getId()) {
		case R.id.locationFirstRelative: // Search
			Intent intent1 = new Intent();
			intent1.setClass(v.getContext(),
					SearchFirstCategoryScreen.class);
			startActivity(intent1);
			break;
		case R.id.locationSecondRelative:
			Intent intent3 = new Intent() ;
			intent3.setClass(v.getContext(), FindScreen.class) ;
			startActivity(intent3) ;
			break;
		case R.id.locationFourthRelative: // Add location
			Intent intent2 = new Intent();
			intent2.setClass(v.getContext(),
					LocationAddressListScreen.class);
			startActivity(intent2);
			break;
		default:
			break;
		}
	}


	@Override
	protected void onPause() {
		BaiduMapApiApplication app = (BaiduMapApiApplication) this
				.getApplication();
		app.mBMapMgr.stop();
		super.onPause();
	}

	@Override
	protected void onResume() {
		BaiduMapApiApplication app = (BaiduMapApiApplication) this
				.getApplication();
		app.mBMapMgr.start();
		super.onResume();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public abstract int getApplicationLayoutResource();
	public abstract int getViewGroupResource();
	public abstract int getMapViewResource() ;

	public static class ClickListenerForScrolling implements OnClickListener {
		HorizontalScrollView scrollView;
		View menu;
		/**
		 * Menu must NOT be out/shown to start with.
		 */
		boolean menuOut = false;

		public ClickListenerForScrolling(HorizontalScrollView scrollView,
				View menu) {
			super();
			this.scrollView = scrollView;
			this.menu = menu;
		}

		@Override
		public void onClick(View v) {
			Context context = menu.getContext();
			int menuWidth = menu.getMeasuredWidth();

			// Ensure menu is visible
			menu.setVisibility(View.VISIBLE);
			VibratorUtil.setVibrator(context);
			if (!menuOut) {
				// Scroll to 0 to reveal menu
				int left = 0;
				scrollView.smoothScrollTo(left, 0);

			} else {
				// Scroll to menuWidth so menu isn't on screen.
				int left = menuWidth;
				scrollView.smoothScrollTo(left, 0);
				// intent.setClass(context, cls)
			}
			menuOut = !menuOut;
		}
	}

	/**
	 * Helper that remembers the width of the 'slide' button, so that the
	 * 'slide' button remains in view, even when the menu is showing.
	 */
	public static class SizeCallbackForMenu implements SizeCallback {
		int btnWidth;
		View btnSlide;

		public SizeCallbackForMenu(View btnSlide) {
			super();
			this.btnSlide = btnSlide;
		}

		@Override
		public void onGlobalLayout() {
			btnWidth = btnSlide.getMeasuredWidth();
		}

		@Override
		public void getViewSize(int idx, int w, int h, int[] dims) {
			dims[0] = w;
			dims[1] = h;
			final int menuIdx = 0;
			if (idx == menuIdx) {
				dims[0] = w - btnWidth;
			}
		}
	}

}
