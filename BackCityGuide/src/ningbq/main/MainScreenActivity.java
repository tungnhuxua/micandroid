package ningbq.main;

import ningbq.Constant.Constaints;
import ningbq.main.widget.MyHorizontalScrollView;
import ningbq.main.widget.MyHorizontalScrollView.SizeCallback;
import ningbq.util.VibratorUtil;
import ningbq.util.ViewUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainScreenActivity extends Activity {

	MyHorizontalScrollView scrollView;
	View menu;
	View app;
	ImageView btnInside;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE) ;
		LayoutInflater inflater = LayoutInflater.from(this);
		scrollView = (MyHorizontalScrollView) inflater.inflate(
				R.layout.indexpageview, null);
		setContentView(scrollView);

		menu = inflater.inflate(R.layout.mainscreen, null);
		app = inflater.inflate(R.layout.recentview, null);
		
		
		
		ViewGroup group = (ViewGroup)app.findViewById(R.id.recentViewHeadBar) ;
		btnInside = (ImageView) group.findViewById(R.id.BtnSlide);
		btnInside.setOnClickListener(new ClickListenerForScrolling(scrollView,
				menu));
		
		ViewGroup group1 = (ViewGroup)app.findViewById(R.id.recentViewContent) ;
		ListView listView = (ListView)group1.findViewById(R.id.recentViewList) ;
		listView.setAdapter(new EfficientAdapter(this)) ;
		
		ViewGroup group2 = (ViewGroup)app.findViewById(R.id.recentViewForButton) ;
		Button but_SignIn = (Button)group2.findViewById(R.id.ButSignIn) ;
		but_SignIn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent() ;
				intent.setClass(getApplicationContext(), LoginScreen.class) ;
				startActivity(intent) ;
			}
		}) ;
		
		
		RelativeLayout rlayout = (RelativeLayout)menu.findViewById(R.id.locationFirstRelative) ;
		ViewUtil.initView(this, rlayout, 0) ;
		final View[] children = new View[] { menu, app };

        // Scroll to app (view[1]) when layout finished.
        int scrollToViewIdx = 1;
        scrollView.initViews(children, scrollToViewIdx, new SizeCallbackForMenu(btnInside));

	}
	
	private class EfficientAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		// Constructor
		public EfficientAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		/**
		 * Return number if rows to create
		 */
		public int getCount() {
			return Constaints.CITY.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		/**
		 * change the View of List Row overright function
		 */
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			
			Log.i("", String.valueOf(position)) ;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.listrow, null);
				holder = new ViewHolder();
				holder.VenueName = (TextView) convertView
						.findViewById(R.id.txtDistance);
				holder.VenueDistance = (TextView) convertView
						.findViewById(R.id.txtName);

				holder.VenueName.setText(Constaints.CITY[position][1]);
				holder.VenueDistance.setText(Constaints.CITY[position][0]);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			return convertView;
		}
	}

	static class ViewHolder {
		public TextView VenueName;
		public TextView VenueDistance;
	}

	
	
	/**
	 * Helper for examples with a HSV that should be scrolled by a menu View's
	 * width.
	 */
	static class ClickListenerForScrolling implements OnClickListener {
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
			VibratorUtil.setVibrator(context) ;
			if (!menuOut) {
				// Scroll to 0 to reveal menu
				int left = 0;
				scrollView.smoothScrollTo(left, 0);
				
			} else {
				// Scroll to menuWidth so menu isn't on screen.
				int left = menuWidth;
				scrollView.smoothScrollTo(left, 0);
				//intent.setClass(context, cls)
			}
			menuOut = !menuOut;
		}
	}
	
	
    /**
     * Helper that remembers the width of the 'slide' button, so that the 'slide' button remains in view, even when the menu is
     * showing.
     */
    static class SizeCallbackForMenu implements SizeCallback {
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
