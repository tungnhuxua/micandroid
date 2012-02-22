package ningbq.SignUp;

import ningbq.main.LoginScreen;
import ningbq.main.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * @author Saurabh Solanki Used for Forgotten Password UI Part
 * @Email : diamondsaurabh@gmail.com
 */
public class ForgotPasswordScreen extends Activity implements OnClickListener {
	
	private Button but_SendPass = null ;
	private Button back_SignInView = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// for removing the title
		setContentView(R.layout.forgotpassword);
		createLayout();
	}

	private void createLayout() {
		but_SendPass = (Button) findViewById(R.id.ButSendPassword);
		back_SignInView = (Button)findViewById(R.id.backSignInView) ;
		but_SendPass.setOnClickListener(this);
		back_SignInView.setOnClickListener(this) ;
		
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent() ;
		if (v == but_SendPass ) {
			Toast.makeText(
					this,
					"Web Service implimentation is not completed we will integrate in Next build",
					Toast.LENGTH_SHORT).show();
			finish();
		}
		
		if(v == back_SignInView){
			intent.setClass(getApplicationContext(), LoginScreen.class) ;
			startActivity(intent) ;
		}
	}

}