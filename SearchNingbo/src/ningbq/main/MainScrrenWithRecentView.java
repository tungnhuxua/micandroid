package ningbq.main;

import ningbq.Constant.Constaints;
import ningbq.SignUp.SqliteAppScreen;
import ningbq.friend.FriendsScreen;
import ningbq.invite.InviteFriendsScreen;
import ningbq.search.SearchFirstCategoryScreen;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Saurabh Solanki Used for Recent view UI Part
 * @Email : diamondsaurabh@gmail.com
 */

public class MainScrrenWithRecentView extends ListActivity implements
		OnClickListener {
	
	private Button but_Login  ;
	//private Button but_Home ;
	private Button but_Search ;
	private Button but_Fevriotes ;
	//private Button but_Friend ;
	private Button but_Setting ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// for removing the title
		setContentView(R.layout.recentview);
		createLayout();
		
		setListAdapter(new EfficientAdapter(this));
		
	}

	private void createLayout() {
		but_Login = (Button) findViewById(R.id.ButSignIn);
		//but_Home = (Button) findViewById(R.id.Buthome);
		but_Search = (Button) findViewById(R.id.ButSerarch);
		but_Fevriotes = (Button) findViewById(R.id.ButFev);
		//but_Friend = (Button) findViewById(R.id.ButPeople);
		but_Setting = (Button) findViewById(R.id.ButSetting);
		
		
		//but_Login.setVisibility(View.GONE) ;
		

		but_Login.setOnClickListener(this);
		//but_Home.setOnClickListener(this);
		but_Fevriotes.setOnClickListener(this);
		//but_Friend.setOnClickListener(this);
		but_Setting.setOnClickListener(this);
		but_Search.setOnClickListener(this);

		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"fonts/Arial_Rounded_MT_Bold.ttf");
		but_Login.setTypeface(typeFace);

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
	public void onClick(View v) {
		if (v.getId() == R.id.ButSignIn) {
			Intent intent = new Intent(this, LoginScreen.class);
			startActivity(intent);
		}
/*		if (v.getId() == R.id.Buthome) {

		}
		
		if (v.getId() == R.id.ButPeople) {
			
			Intent intent = new Intent(this,FriendsScreen.class) ;
			startActivity(intent) ;

		}*/
		if (v.getId() == R.id.ButFev) {
			Intent intent = new Intent(this, MainScreenActivity.class);
			startActivity(intent);
		}
		if (v.getId() == R.id.ButSerarch) {
			Intent intent = new Intent(this, SearchFirstCategoryScreen.class);
			startActivity(intent);
		}
		if (v.getId() == R.id.ButSetting) {
		
			Intent intent = new Intent(this, SearchFirstCategoryScreen.class);
			startActivity(intent);
		}
	}

}
