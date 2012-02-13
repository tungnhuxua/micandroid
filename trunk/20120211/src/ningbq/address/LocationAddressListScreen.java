package ningbq.address;

import com.baidu.location.LocationClient;
import ningbq.Constant.Constaints;
import ningbq.application.BaiduMapApiApplication;
import ningbq.main.BaseActivity;
import ningbq.main.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class LocationAddressListScreen extends BaseActivity {

	private ListView listView = null;
	private TextView locationInfo = null;
	private LocationClient mLocClient;

	@Override
	public boolean _onCreate(Bundle savedInstanceState) {
		if (super._onCreate(savedInstanceState)) {
			locationInfo = (TextView) viewGroup.findViewById(R.id.catName); // head
																			// information
			ImageView imageView = (ImageView) viewGroup
					.findViewById(R.id.BtnSlide);
			ViewGroup addLocationGroup = (ViewGroup) appView
					.findViewById(R.id.locactionListViewForButton);

			listView = (ListView) appView.findViewById(R.id.addLactionViewList);

			mLocClient = ((BaiduMapApiApplication) getApplication()).locationClient;
			((BaiduMapApiApplication) getApplication()).txt_LocationInfo = locationInfo;
			locationInfo.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mLocClient.start();
					mLocClient
							.addRecerveListener(((BaiduMapApiApplication) getApplication()).new MyReceiveListenner());
					try {
						Thread.sleep(5000);
						mLocClient.getLocation();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

			listView.setAdapter(new EfficientAdapter(this));
			imageView.setOnClickListener(new ClickListenerForScrolling(
					scrollView, menuView));

			scrollView.initViews(children, 1,
					new SizeCallbackForMenu(imageView));

			Button but_addLocation = (Button) addLocationGroup
					.findViewById(R.id.ButAddLocation);
			but_addLocation.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(LocationAddressListScreen.this,
							LocationAddressScreen.class);
					startActivity(intent);
				}
			});

			return true;
		} else {
			return false;
		}

	}

	@Override
	public int getApplicationLayoutResource() {
		return R.layout.list_location_address;
	}

	@Override
	public int getViewGroupResource() {
		return R.id.locationListViewHeadBar;
	}

	/** 模拟数据 */
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

			Log.i("", String.valueOf(position));
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

	@Override
	public void onDestroy() {
		mLocClient.stop();
		super.onDestroy();
	}
}
