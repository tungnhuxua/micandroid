package ningbq.main;

import ningbq.friend.FriendsScreen;
import ningbq.search.SearchScreen;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FooterScreen extends Activity implements OnClickListener {

	private static final String TAG = "FooterScreen";
	private Button but_Home;
	private Button but_Search;
	private Button but_Fev;
	private Button but_People;
	private Button but_Setting;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.footer);
		init();
	}

	private void init() {
		but_Home = (Button) findViewById(R.id.Buthome);
		but_Search = (Button) findViewById(R.id.ButSerarch);
		but_Fev = (Button) findViewById(R.id.ButFev);
		but_People = (Button) findViewById(R.id.ButPeople);
		but_Setting = (Button) findViewById(R.id.ButSetting);

		but_Home.setOnClickListener(this);
		but_Search.setOnClickListener(this);
		but_Fev.setOnClickListener(this);
		but_People.setOnClickListener(this);
		but_Setting.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.Buthome:
			intent.setClass(FooterScreen.this, MainScrrenWithRecentView.class);
			startActivity(intent);
			Log.i(TAG, "redirct home.");
			break;
		case R.id.ButSerarch:
			intent.setClass(FooterScreen.this, SearchScreen.class);
			startActivity(intent);
			break;
		case R.id.ButFev:
			Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
			finish();
			break;
		case R.id.ButPeople:
			intent.setClass(FooterScreen.this, FriendsScreen.class);
			startActivity(intent);
			break;
		case R.id.ButSetting:
			Toast.makeText(this, "Setting...", Toast.LENGTH_LONG).show();
			break;
		}
	}
}
