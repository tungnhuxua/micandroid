package ningbq.invite;

import java.util.Calendar;

import ningbq.main.R;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

/**
 * Description:Invite Friend
 * 
 * @author Devon.Ning
 * @2011-12-21
 * 
 */
public class InviteFriendsScreen extends Activity implements OnClickListener,
		OnTimeSetListener, OnDateSetListener {
	
	private final static String TAG = "InviteFriendsScreen" ;

	private Button but_Home;
	private Button but_Search;
	private Button but_Fevriotes;
	private Button but_Friend;
	private Button but_Setting;
	private EditText edt_Address; // EventAddress
	private EditText edt_Date; // EventDate
	private EditText edt_Time; // EventTime

	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;

	static final int TIME_DIALOG_ID = 0;
	static final int DATE_DIALOG_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.invitefriends);
		init();
	}

	private void init() {
		but_Home = (Button) findViewById(R.id.Buthome);
		but_Search = (Button) findViewById(R.id.ButSerarch);
		but_Fevriotes = (Button) findViewById(R.id.ButFev);
		but_Friend = (Button) findViewById(R.id.ButPeople);
		but_Setting = (Button) findViewById(R.id.ButSetting);
		edt_Address = (EditText) findViewById(R.id.edtAddress);
		edt_Date = (EditText) findViewById(R.id.edtDate);
		edt_Time = (EditText) findViewById(R.id.edtTime);

		final Calendar cal = Calendar.getInstance();
		mYear = cal.get(Calendar.YEAR);
		mMonth = cal.get(Calendar.MONTH);
		mDay = cal.get(Calendar.DAY_OF_MONTH);
		mHour = cal.get(Calendar.HOUR_OF_DAY);
		mMinute = cal.get(Calendar.MINUTE);

		edt_Date.setOnTouchListener(new EditOnTouchListener());
		edt_Time.setOnTouchListener(new EditOnTouchListener());
		edt_Date.setOnClickListener(this);
		edt_Time.setOnClickListener(this);
		but_Home.setOnClickListener(this);
		but_Fevriotes.setOnClickListener(this);
		but_Friend.setOnClickListener(this);
		but_Setting.setOnClickListener(this);
		but_Search.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Buthome:

			break;
		case R.id.ButSerarch:
			break;
		case R.id.ButFev:
			break;
		case R.id.ButPeople:
			break;
		case R.id.ButSetting:
			break;
		case R.id.edtDate:
			showDialog(DATE_DIALOG_ID) ;
			break;
		case R.id.edtTime:
			showDialog(TIME_DIALOG_ID) ;
			break;

		default:
			break;
		}
	}

	/**
	 * Description:Handle Soft Key.
	 * 
	 */
	class EditOnTouchListener implements OnTouchListener {
		EditText temp;

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			temp = (EditText) v;
			temp.setInputType(InputType.TYPE_NULL);
			return false;
		}

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, this, mHour, mMinute, true);
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, this, mYear, mMonth, mDay);
		}
		return null;
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		mHour = hourOfDay;
		mMinute = minute;
		StringBuffer temp = new StringBuffer();
		edt_Time.setText(temp.append(mHour).append(":").append(mMinute));
		Log.i(TAG, "TIME:" + temp.toString()) ;
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		mYear = year;
		mMonth = monthOfYear + 1;
		mDay = dayOfMonth;
		StringBuffer temp = new StringBuffer();
		edt_Date.setText(temp.append(mYear).append("-").append(mMonth).append("-")
				.append(mDay));
		Log.i(TAG, "DATE:" + temp.toString()) ;
	}

}
