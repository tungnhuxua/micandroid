package ningbq.SignUp;

import ningbq.main.R;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Saurabh Solanki
 * Used for Create Account UI Part  
 * @Email : diamondsaurabh@gmail.com 
 */
public class CreateAccountScreen extends Activity implements OnClickListener
{
	 @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);//for removing the title bar
		    setContentView(R.layout.signup);
		  	createLayout();
		}
	 	 

	 	private void createLayout() 
	 	{
	 		Button but_Hometop 		= (Button)findViewById(R.id.ButHomeTop);
	 		Button but_Register 	= (Button)findViewById(R.id.Butregister);
	 		Button but_Home  		= (Button)findViewById(R.id.Buthome);
	 		Button but_Search 		= (Button)findViewById(R.id.ButSerarch);
	 		Button but_Fevriotes 	= (Button)findViewById(R.id.ButFev);
	 		Button but_Friend 		= (Button)findViewById(R.id.ButPeople);
	 		Button but_Setting 		= (Button)findViewById(R.id.ButSetting);
	 		but_Register.setOnClickListener(this);
			but_Home.setOnClickListener(this);
			but_Fevriotes.setOnClickListener(this);
			but_Friend.setOnClickListener(this);
			but_Setting.setOnClickListener(this);
			but_Search.setOnClickListener(this);
			but_Hometop.setOnClickListener(this);
			
			//setting font:
			TextView tv = (TextView)findViewById(R.id.txtHeading) ;
			Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Arial_Rounded_MT_Bold.ttf") ;
			tv.setTypeface(typeFace) ;
			
			//setting but_homeTop font
			but_Hometop.setTypeface(typeFace) ;
			//setting Butregister font
			but_Register.setTypeface(typeFace) ;
			
		}
	 @Override
		public void onClick(View v)
		{
		 if (v.getId() == R.id.Butregister)
		 {
			 Toast.makeText(this, "Web Service implimentation is not completed we will integrate in Next build", Toast.LENGTH_SHORT).show();
			 finish();
		 }
		 if (v.getId() == R.id.ButHomeTop)
		 {
			 finish();
		 }
		 if (v.getId() == R.id.ButSerarch)
		 {
			 Toast.makeText(this, "Under Costruction", Toast.LENGTH_SHORT).show();
		 }
		 if (v.getId() == R.id.ButFev)
		 {
			 Toast.makeText(this, "Under Costruction", Toast.LENGTH_SHORT).show();
				 
		 }
		 if (v.getId() == R.id.ButPeople)
		 {
			 Toast.makeText(this, "Under Costruction", Toast.LENGTH_SHORT).show();
				
		 }
		 if (v.getId() == R.id.ButSetting)
		 {
			 Toast.makeText(this, "Under Costruction", Toast.LENGTH_SHORT).show();
				
		 }
		}

}