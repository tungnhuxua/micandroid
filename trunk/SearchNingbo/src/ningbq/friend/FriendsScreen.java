package ningbq.friend;

import ningbq.Constant.Constaints;
import ningbq.main.R;
import ningbq.util.SettingFont;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class FriendsScreen extends Activity implements OnClickListener {

	private GridView friends_list;
	private Button but_home ;
	private Button but_friend ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friends) ;
		createLayout();
		friends_list = (GridView) findViewById(R.id.friendsList);
		ImageList adapter = new ImageList(this) ;
		friends_list.setAdapter(adapter);

	}

	private void createLayout() {
		but_home = (Button) findViewById(R.id.Buthome);
		but_friend = (Button) findViewById(R.id.ButPeople);
		but_home.setBackgroundDrawable(SettingFont.SettingBackgroundImage(R.drawable.home_off, this));
		but_friend.setBackgroundDrawable(SettingFont.SettingBackgroundImage(R.drawable.friends_on, this));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	private class ImageList extends BaseAdapter {

		Activity activity;

		public ImageList(Activity activity) {
			this.activity = activity;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Constaints.HEAD_IMAGES.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return Constaints.HEAD_IMAGES[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView iv ;
			if(convertView == null){//think memory 
				iv = new ImageView(activity);
				iv.setImageResource(Constaints.HEAD_IMAGES[position]);
			}else{
				iv = (ImageView)convertView ;
			}
			return iv;
		}

	}

}
