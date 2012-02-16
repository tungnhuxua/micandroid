package ningbo.media;

import ningbo.media.screen.SecondLocationSceen;
import ningbo.media.service.LocationService;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainScreenActivity extends Activity {

	private Button currentLoc = null;
	private Button other_Button = null;
	private EditText txt_Latitude = null;
	private EditText txt_Longitude = null;
	private double lat = 0.0;
	private double lng = 0.0;
	private LocateReceiver receiver = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		currentLoc = (Button) findViewById(R.id.currentLocationBtn);
		txt_Latitude = (EditText) findViewById(R.id.txtLatitude);
		txt_Longitude = (EditText) findViewById(R.id.txtLongitude);
		other_Button = (Button)findViewById(R.id.othcerLocationBtn) ;
		other_Button.setOnClickListener(buttonListener) ;
		currentLoc.setOnClickListener(buttonListener);

	}

	OnClickListener buttonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.currentLocationBtn:
				startLocateService();
				break;
			case R.id.othcerLocationBtn:
				Intent itent = new Intent() ;
				itent.setClass(MainScreenActivity.this, SecondLocationSceen.class) ;
				startActivity(itent) ;
			default:
				break;
			}

		}
	};
	
	


	private void startLocateService() {
		Intent intent = new Intent(this,LocationService.class) ;
		startService(intent) ;
		IntentFilter filter = new IntentFilter(LocationService.LOCATION_CHANGE) ;
		this.receiver = new LocateReceiver() ;
		registerReceiver(receiver, filter) ;
	}

	class LocateReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			lat = intent.getDoubleExtra("lat", lat);
			lng = intent.getDoubleExtra("lng", lng);
			txt_Latitude.setText(String.valueOf(lat)) ;
			txt_Longitude.setText(String.valueOf(lng)) ;
		}

	}

}