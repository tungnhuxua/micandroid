package ningbq.SignUp;

import java.io.ByteArrayOutputStream;
import java.io.File;

import ningbq.main.LoginScreen;
import ningbq.main.R;
import ningbq.service.UserService;
import ningbq.util.AndroidFileUtil;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Description: Register Account
 * 
 * @author Devon.Ning
 * @2012-1-13
 * 
 */
public class CreateAccountScreen extends Activity implements OnClickListener {
	private static final String TAG = "CreateAccountScreen";

	private EditText edt_name;
	private EditText edt_email;
	private EditText edt_password;
	private ImageView but_Image;

	private String username;
	private String password;
	private String email;
	private RegisterProcess registerProcess = null;
	private ProgressDialog progressDialog = null;

	private static final int LOCAL_PHOTO = 100; // 本地相册
	private static final int TAKE_PHOTO = 101; // 拍照

	private static final int PHOTO_GRAPH = 1;// 拍照
	private static final int PHOTO_ZOOM = 2; // 缩放
	private static final int PHOTO_RESOULT = 3;// 结果
	private static final int NONE = 0;
	private static final String IMAGE_UNSPECIFIED = "image/*";
	protected UserService uService = new UserService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// for removing the title
		setContentView(R.layout.signup);
		createLayout();
		registerForContextMenu(but_Image); // register context menu.
	}

	private void createLayout() {
		Button but_Register = (Button) findViewById(R.id.Butregister);
		Button backSignIn = (Button) findViewById(R.id.backSignInView);

		edt_name = (EditText) findViewById(R.id.edtName);
		edt_email = (EditText) findViewById(R.id.edtEmail);
		edt_password = (EditText) findViewById(R.id.edtPassword);
		but_Image = (ImageView) findViewById(R.id.ButImage);

		but_Register.setOnClickListener(this);
		backSignIn.setOnClickListener(this);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == NONE) {
			return;
		}

		if (requestCode == PHOTO_GRAPH) {// 拍照
			// 设置文件保存路径
			File picture = new File(Environment.getExternalStorageDirectory()
					+ "/temp.jpg");
			// File picture = new File(getSystemDefaultHeaderPath() +
			// "header.jpg") ;
			startPhotoZoom(Uri.fromFile(picture));
		}

		if (data == null)
			return;

		if (requestCode == PHOTO_ZOOM) {// 读取相册缩放图片
			startPhotoZoom(data.getData());
		}

		if (requestCode == PHOTO_RESOULT) {// 处理结果
			Bundle extras = data.getExtras();
			if (extras != null) {
				Bitmap photo = extras.getParcelable("data");
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
				// 此处可以把Bitmap保存到sd卡中，
				but_Image.setImageBitmap(photo); // 把图片显示在ImageView控件上
			}

		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Intent intent = null;
		switch (item.getItemId()) {
		case TAKE_PHOTO:
			intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
					Environment.getExternalStorageDirectory(), "temp.jpg")));
			// intent.putExtra(MediaStore.ACTION_IMAGE_CAPTURE,
			// Uri.fromFile(new File(getSystemDefaultHeaderPath(),
			// "header.jpg")));
			startActivityForResult(intent, PHOTO_GRAPH);
			break;
		case LOCAL_PHOTO:
			intent = new Intent(Intent.ACTION_PICK, null);
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					IMAGE_UNSPECIFIED);
			startActivityForResult(intent, PHOTO_ZOOM);
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle(R.string.headImage_title);
		// menu.set

		menu.add(0, TAKE_PHOTO, 0, R.string.graph_photo);
		menu.add(0, LOCAL_PHOTO, 0, R.string.local_photo);
		// super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.Butregister:
			registerProcess = new RegisterProcess();
			registerProcess.execute();
			break;
		case R.id.backSignInView:
			intent.setClass(getApplicationContext(), LoginScreen.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	protected String getSystemDefaultHeaderPath() {
		String externalStorageState = Environment.getExternalStorageState();
		if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
			File files = new File(Environment.getExternalStorageDirectory()
					.getAbsolutePath()
					+ AndroidFileUtil.defaultSystemFilePath());
			boolean flag = files.exists();
			if (!flag) { // 不存在
				files.mkdirs();
			}
			return files.getAbsolutePath() + File.separator;
		} else {
			Log.e(TAG, "please insert sd card.");
			Toast.makeText(getApplicationContext(), "Please Insert SD Card!",
					Toast.LENGTH_LONG).show();
			return null;
		}
	}

	/**
	 * 收缩图片
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 100);
		intent.putExtra("outputY", 100);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTO_RESOULT);
	}

	class RegisterProcess extends AsyncTask<Void, Void, JSONObject> {

		@Override
		protected void onPreExecute() {

			progressDialog = ProgressDialog.show(
					CreateAccountScreen.this,
					null,
					getResources().getString(
							R.string.register_info_submit_loading));
			progressDialog.setCancelable(true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					RegisterProcess.this.cancel(true);
				}
			});
		}

		@Override
		protected JSONObject doInBackground(Void... params) {
			username = edt_name.getText().toString();
			password = edt_password.getText().toString();
			email = edt_email.getText().toString();

			JSONObject uJson = uService.register(username, password, email);
			Log.i(TAG, "register-json:" + uJson.toString());
			return uJson;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			if (result == null) {
				new AlertDialog.Builder(CreateAccountScreen.this)
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

			Intent intent = new Intent();
			intent.setClass(CreateAccountScreen.this, LoginScreen.class);
			progressDialog.dismiss();
			startActivity(intent);
			Log.i(TAG, "Account be created successfully!");
			CreateAccountScreen.this.finish();
		}
	}

}