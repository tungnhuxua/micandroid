package ningbq.address;

import ningbq.main.R;
import ningbq.util.VibratorUtil;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


/**
 * Description:添加位置信息
 *
 * @author Devon.Ning
 * @2012-2-8
 *
 */
public class LocationAddressScreen extends Activity implements OnClickListener {
	private static final String TAG = "LocationAddressScreen" ;

	private Button next_SignInView = null;
	private Button but_SaveLocation = null ;
	private EditText edt_LocationCnName = null ;
	private EditText edt_LocationEnName = null;
	private EditText edt_LocationCnAddress = null;
	private EditText edt_LocationEnAddress = null;
	private EditText edt_LocationTel = null;
	private String locationCnName = null ;
	private String locationEnName = null ;
	private String locationCnAddress = null ;
	private String locationEnAddress = null ;
	private String locationTel = null ;
	private ProgressDialog addLocationProgressDialog = null;
	
	private LocationAddProgress locationAddProgress = new LocationAddProgress() ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// remove the title.
		setContentView(R.layout.add_location_address);
		createLayout();

	}

	private void createLayout() {
		next_SignInView = (Button) findViewById(R.id.nextSignInView);
		but_SaveLocation = (Button)findViewById(R.id.ButSaveLocation) ;
		next_SignInView.setOnClickListener(this);
		but_SaveLocation.setOnClickListener(this) ;

		edt_LocationCnName = (EditText) findViewById(R.id.edtLocationCnName);
		edt_LocationEnName = (EditText) findViewById(R.id.edtLocationEnName);
		edt_LocationCnAddress = (EditText) findViewById(R.id.edtLocationCnAddress);
		edt_LocationEnAddress = (EditText) findViewById(R.id.edtLocationEnAddress);
		edt_LocationTel = (EditText) findViewById(R.id.edtLocationTel);

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		VibratorUtil.setVibrator(v.getContext());
		switch (v.getId()) {
		case R.id.nextSignInView:
			intent.setClass(LocationAddressScreen.this,
					LocationTakePhotoScreen.class);
			startActivity(intent);
			break;
		case R.id.ButSaveLocation:
			locationAddProgress.execute() ;
			//Toast.makeText(LocationAddressScreen.this, "添加成功.", Toast.LENGTH_LONG).show() ;
			break;
		default:
			break;
		}
	}

	
	/**
	 * Description:异步处理保存的数据.
	 *
	 * @author Devon.Ning
	 * @2012-2-8
	 *
	 */
	class LocationAddProgress extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			addLocationProgressDialog = ProgressDialog.show(
					LocationAddressScreen.this,
					getResources().getString(
							R.string.location_progress_dialog_title),
					getResources().getString(
							R.string.location_progress_dialog_loading));
			addLocationProgressDialog.setCancelable(true);
			addLocationProgressDialog
					.setOnCancelListener(new OnCancelListener() {
						@Override
						public void onCancel(DialogInterface dialog) {
							LocationAddProgress.this.cancel(true);
						}
					});
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			locationCnName = edt_LocationCnName.getText().toString() ;
			locationEnName = edt_LocationEnName.getText().toString() ;
			locationCnAddress = edt_LocationCnAddress.getText().toString() ;
			locationEnAddress = edt_LocationEnAddress.getText().toString() ;
			locationTel = edt_LocationTel.getText().toString() ;
			
			boolean flag = true;
			try {
				if (flag) {
					Log.i(TAG, "Add Location Information.") ;
					ContentValues values = new ContentValues() ;
					values.put("name_cn", locationCnName) ;
					values.put("name_en", locationEnName) ;
					values.put("address_cn", locationCnAddress) ;
					values.put("address_en", locationEnAddress) ;
					values.put("telephone", locationTel) ;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				flag = false;
			}
			return flag;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if(result){
				Log.i(TAG, "It is ok.") ;
				addLocationProgressDialog.dismiss() ;
			}
			//LocationAddressScreen.this.finish() ;
		}
	}

}
