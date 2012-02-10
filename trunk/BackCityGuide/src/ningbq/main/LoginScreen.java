package ningbq.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ningbq.SignUp.CreateAccountScreen;
import ningbq.SignUp.ForgotPasswordScreen;
import ningbq.bean.User;
import ningbq.service.UserService;
import ningbq.util.CheckNetwork;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends Activity implements OnClickListener {
	private final static String TAG = "LOGINSCREEN";

	private Button but_Login = null;
	private Button but_register = null ;
	private EditText edt_email = null;
	private EditText edt_password = null;
	private LoginProcess loginProcess = null;
	private ProgressDialog progressDialog = null;
	private User user = null;
	private SharedPreferences settings = null;
	private Editor edit = null;
	private String mUserEmail = null;
	private String mPassword = null;
	private Button backImageView = null;
	private TextView txt_fPass = null ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// for removing the title
		setContentView(R.layout.login);
		createLayout();

	}

	private void createLayout() {

		but_Login = (Button) findViewById(R.id.ButSignIn);
		but_register = (Button)findViewById(R.id.ButSignUp) ;
		txt_fPass = (TextView) findViewById(R.id.txtforgotPassword);
		edt_email = (EditText) findViewById(R.id.edtEmail);
		edt_password = (EditText) findViewById(R.id.edtPassword);
		backImageView = (Button) findViewById(R.id.backRecentView);

		settings = PreferenceManager.getDefaultSharedPreferences(this);

		backImageView.setOnClickListener(this);
		but_Login.setOnClickListener(this);
		but_register.setOnClickListener(this) ;
		txt_fPass.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent() ;
		switch (v.getId()) {
		case R.id.ButSignIn:
			if (validate()) {
				loginProcess = new LoginProcess();
				loginProcess.execute();
			}
			Log.e(TAG, "Validate Error!");
			break;
		case R.id.backRecentView:
			intent.setClass(getApplicationContext(), MainScreenActivity.class) ;
			startActivity(intent) ;
			Log.i(TAG, "Recented Screen.") ;
			break;
		case R.id.ButSignUp:
			intent.setClass(LoginScreen.this, CreateAccountScreen.class) ;
			startActivity(intent) ;
			Log.i(TAG, "Register Screen.") ;
			break ;
		case R.id.txtforgotPassword:
			intent.setClass(LoginScreen.this, ForgotPasswordScreen.class) ;
			startActivity(intent) ;
			Log.i(TAG, "Forgot Password Screen.") ;
			break ;
		default:
			break;
		}
	}

	/** 检测网络连接与验证用户输入 */
	private boolean validate() {
		if (!CheckNetwork.check(this)) {
			Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Network Error");
			builder.setMessage(getResources().getString(R.string.network_open));
			builder.setPositiveButton("Setting",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent();
							ComponentName comp = new ComponentName(
									"com.android.settings",
									"com.android.settings.WirelessSettings");
							intent.setComponent(comp);
							intent.setAction("<SPAN class=hilite>android</SPAN>.intent.action.VIEW");
							startActivity(intent);
						}
					})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							}).create();
			builder.show();
			return false;
		}

		String regex = "^([a-zA-Z0-9_\\.-])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(edt_email.getText().toString());
		if (!m.matches()) {
			Toast.makeText(LoginScreen.this, "email error!", Toast.LENGTH_LONG)
					.show();
			return false;
		}
		if ("".equals(edt_password.getText().toString())) {
			Toast.makeText(LoginScreen.this, "password isn't empty!",
					Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}

	class LoginProcess extends AsyncTask<Void, Void, JSONObject> {

		@Override
		protected void onPreExecute() {
			mUserEmail = edt_email.getText().toString();
			mPassword = edt_password.getText().toString();
			progressDialog = ProgressDialog.show(LoginScreen.this, null,
					String.valueOf(R.string.signin_loading));
			progressDialog.setCancelable(true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					LoginProcess.this.cancel(true);
				}
			});
		}

		@Override
		protected JSONObject doInBackground(Void... none) {
			UserService userService = new UserService();
			JSONObject obj = userService.login(mUserEmail, mPassword);
			Log.i(TAG, "UserJson:" + obj);
			user = new User(obj);
			Log.i(TAG, "USERNAME:" + user.getUsername());
			Log.i(TAG, "date:" + user.getDate_time());
			return obj;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			if (result == null) {
				new AlertDialog.Builder(LoginScreen.this)
						.setMessage(
								getResources().getString(R.string.server_state))
						.setNegativeButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										progressDialog.dismiss();
									}
								}).show();
				return;
			}

			edit = settings.edit();
			Intent intent = new Intent();
			intent.putExtra("email", edt_email.getText().toString());
			edit.putString("email", edt_email.getText().toString());

			intent.putExtra("password", edt_password.getText().toString());
			edit.putString("password", edt_password.getText().toString());

			edit.commit();
			intent.setClass(LoginScreen.this, MainScreenActivity.class);
			progressDialog.dismiss();
			startActivity(intent);
			LoginScreen.this.finish();
		}

	}

}
