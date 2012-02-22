package ningbq.address;

import java.lang.reflect.Field;

import ningbq.bean.Location;
import ningbq.bean.SecondCategory;
import ningbq.db.dao.LocationAddressDao;
import ningbq.db.dao.SecondCategoryDao;
import ningbq.main.R;
import ningbq.util.VibratorUtil;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Description:添加位置信息
 * 
 * @author Devon.Ning
 * @2012-2-8
 * 
 */
public class LocationAddressScreen extends Activity implements OnClickListener {
	private static final String TAG = "LocationAddressScreen";

	private Button next_SignInView = null;
	private Button but_SaveLocation = null;
	private EditText edt_LocationCnName = null;
	private EditText edt_LocationEnName = null;
	private EditText edt_LocationCnAddress = null;
	private EditText edt_LocationEnAddress = null;
	private EditText edt_LocationTel = null;
	private String locationCnName = null;
	private String locationEnName = null;
	private String locationCnAddress = null;
	private String locationEnAddress = null;
	private String locationTel = null;
	private ProgressDialog addLocationProgressDialog = null;
	private Spinner firstCategorySpinner = null;
	private Spinner secondCategorySpinner = null;
	@SuppressWarnings("rawtypes")
	private ArrayAdapter firstCategoryAdapter = null;
	private static final int SEND_SECONDCATEGORY_MSG = 16;
	private String secondId = null;
	private FindSecondId threadS;

	private LocationAddProgress locationAddProgress = new LocationAddProgress();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// remove the title.
		setContentView(R.layout.add_location_address);
		createLayout();

	}

	private void createLayout() {
		next_SignInView = (Button) findViewById(R.id.nextSignInView);
		but_SaveLocation = (Button) findViewById(R.id.ButSaveLocation);
		next_SignInView.setOnClickListener(this);
		but_SaveLocation.setOnClickListener(this);
		edt_LocationCnName = (EditText) findViewById(R.id.edtLocationCnName);
		edt_LocationEnName = (EditText) findViewById(R.id.edtLocationEnName);
		edt_LocationCnAddress = (EditText) findViewById(R.id.edtLocationCnAddress);
		edt_LocationEnAddress = (EditText) findViewById(R.id.edtLocationEnAddress);
		edt_LocationTel = (EditText) findViewById(R.id.edtLocationTel);
		firstCategorySpinner = (Spinner) findViewById(R.id.selectLocationFirstCategory);
		secondCategorySpinner = (Spinner) findViewById(R.id.selectLocationSecondCategory);
		firstCategoryAdapter = ArrayAdapter.createFromResource(this,
				R.array.firstCategory, android.R.layout.simple_spinner_item);
		firstCategoryAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		firstCategorySpinner.setAdapter(firstCategoryAdapter);
		firstCategorySpinner.setOnItemSelectedListener(selectedItemListener);
		secondCategorySpinner
				.setOnItemSelectedListener(secondSelectedItemListener);
		threadS = new FindSecondId();
		threadS.start();
	}

	private Handler secondHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SEND_SECONDCATEGORY_MSG:
				break;
			default:
				break;
			}
		}

	};

	class FindSecondId extends Thread {
		@Override
		public void run() {
			String second_name = String.valueOf(secondCategorySpinner
					.getSelectedItem());
			Log.i(TAG, "second_name:" + second_name);
			secondHandler.sendEmptyMessage(SEND_SECONDCATEGORY_MSG);
		}

	}

	private void loadSecondId(String categoryName) {
		SecondCategoryDao sDao = new SecondCategoryDao(
				LocationAddressScreen.this);
		SecondCategory tmp = sDao.queryByCategoryName(categoryName);
		if (tmp != null) {
			secondId = tmp.getSecondId();
		}

		Log.i("SecondId:", String.valueOf(secondId));
	}

	OnItemSelectedListener secondSelectedItemListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			String name = secondCategorySpinner.getSelectedItem().toString();
			Log.i(TAG, "secondCategory-name:" + name);
			loadSecondId(name);

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};

	OnItemSelectedListener selectedItemListener = new OnItemSelectedListener() {
		@SuppressWarnings("rawtypes")
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			int pos = firstCategorySpinner.getSelectedItemPosition();
			int variable = Integer.valueOf(getVariableByName(
					"firstCategory" + pos).toString());
			if (variable != 0) {
				ArrayAdapter secondCategoryAdapter = ArrayAdapter
						.createFromResource(LocationAddressScreen.this,
								variable, android.R.layout.simple_spinner_item);
				secondCategoryAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				secondCategorySpinner.setAdapter(secondCategoryAdapter);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> view) {

		}

	};

	/**
	 * 根据变量名称，得到R.array类中的变量
	 * 
	 * @param varName
	 * @return
	 */
	public String getVariableByName(String varName) {
		// 获取对象对应类中的所有属性域
		Field[] fields = R.array.class.getDeclaredFields();
		for (int i = 0, len = fields.length; i < len; i++) {
			try {
				// 比较获取的属性名是否也传入的一致
				if (varName.equalsIgnoreCase(fields[i].getName())) {
					// 获取原来的访问控制权限
					boolean accessFlag = fields[i].isAccessible();
					// 修改访问控制权限
					fields[i].setAccessible(true);
					// 获取在对象中属性fields[i]对应的对象中的变量
					Object o = fields[i].get(R.array.class);
					// 恢复访问控制权限
					fields[i].setAccessible(accessFlag);
					return o.toString();
				}
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			}
		}
		return "0";
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
			locationAddProgress.execute();
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

			LocationAddressDao dao = new LocationAddressDao(
					LocationAddressScreen.this);

			locationCnName = edt_LocationCnName.getText().toString();
			locationEnName = edt_LocationEnName.getText().toString();
			locationCnAddress = edt_LocationCnAddress.getText().toString();
			locationEnAddress = edt_LocationEnAddress.getText().toString();
			locationTel = edt_LocationTel.getText().toString();

			boolean flag = true;
			try {
				if (flag) {
					Location loc = new Location();
					loc.setSecondId(secondId);
					loc.setName_cn(locationCnName);
					loc.setName_en(locationEnName);
					loc.setAddress_cn(locationCnAddress);
					loc.setAddress_en(locationEnAddress);
					loc.setTelephone(locationTel);
					loc.setLocationId("10010002");
					loc.setLatitude(0.0);
					loc.setLongitude(0.0);

					long ids = dao.insert(loc);
					Log.i(TAG, "return saved ids:" + ids);
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
			if (result) {
				Log.i(TAG, "Saved Location Success.");
				Toast.makeText(LocationAddressScreen.this, "保存成功.",
						Toast.LENGTH_LONG).show();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				Log.e(TAG, "Saved Location Fail.");
				Toast.makeText(LocationAddressScreen.this, "保存失败.",
						Toast.LENGTH_LONG).show();
			}
			addLocationProgressDialog.dismiss();
		}
	}

}
