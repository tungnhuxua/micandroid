package ningbq.main;

import ningbq.Constant.Constaints;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @author Saurabh Solanki
 * Used for Recent view UI Part  
 * @Email : diamondsaurabh@gmail.com 
 */

public class MainScrrenWithRecentView extends ListActivity implements OnClickListener
{
		
	 	 @Override
	 	    public void onCreate(Bundle savedInstanceState) 
	 	    {
	 	        super.onCreate(savedInstanceState);
	 	        requestWindowFeature(Window.FEATURE_NO_TITLE);//for removing the title bar
		        setContentView(R.layout.recentview);
		    	setListAdapter(new EfficientAdapter(this));
		    	createLayout();
		   }
	 	 

	 	private void createLayout() 
	 	{
	 		Button but_Login 		= (Button)findViewById(R.id.ButSignIn);
	 		Button but_Home  		= (Button)findViewById(R.id.Buthome);
	 		Button but_Search 		= (Button)findViewById(R.id.ButSerarch);
	 		Button but_Fevriotes 	= (Button)findViewById(R.id.ButFev);
	 		Button but_Friend 		= (Button)findViewById(R.id.ButPeople);
	 		Button but_Setting 		= (Button)findViewById(R.id.ButSetting);
	 		
			but_Login.setOnClickListener(this);
			but_Home.setOnClickListener(this);
			but_Fevriotes.setOnClickListener(this);
			but_Friend.setOnClickListener(this);
			but_Setting.setOnClickListener(this);
			but_Search.setOnClickListener(this);
			
	 		
	 		
	 		Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Arial_Rounded_MT_Bold.ttf") ;
			but_Login.setTypeface(typeFace) ;
			
		}


		private class EfficientAdapter extends BaseAdapter 
	 	{
	 	       private LayoutInflater mInflater;     

	 	       //Constructor 
	 	       public EfficientAdapter(Context context) 
	 	       {
	 	            mInflater = LayoutInflater.from(context);   
	 	       }
	 	       /**
	 	        * Return number if rows to create 
	 	        */
	 	       public int getCount()
	 	       {
	 	    		   return Constaints.CITY.length; 
	 	       }
	 	    
	 	       public Object getItem(int position) 
	 	       {
	 	           return position;
	 	       }
	 	       
	 	       public long getItemId(int position) 
	 	       {
	 	           return position;
	 	       }
	 	       /**
	 	        * change the View of List Row 
	 	        * overright function
	 	        */
	 	      public View getView(int position, View convertView, ViewGroup parent)
	 	      {
	 	    	  ViewHolder holder = null;
	 	    	
	 	    	  if (convertView == null) 
	 	    	  {   
	 	    		    holder = new ViewHolder();
	 	            	convertView = mInflater.inflate(R.layout.listrow, null);
	 					holder = new ViewHolder();
	 					holder.VenueName = (TextView)convertView.findViewById(R.id.txtDistance);
	 					holder.VenueDistance = (TextView)convertView.findViewById(R.id.txtName);
	 					holder.VenueName.setText(Constaints.CITY[position][1]);
	 					holder.VenueDistance.setText(Constaints.CITY[position][0]);
	 		 	  }
	 	    	  else 
	 	    	  {
	 	               holder = (ViewHolder) convertView.getTag();
	 	    	  }
	 	         return convertView;
	 	      }
	 	   }
	 	
	 		static class ViewHolder 
	 	    {
	 			public TextView 		  VenueName; 
	 			public TextView  		  VenueDistance; 
	 		}

			@Override
			public void onClick(View v)
			{
			 if (v.getId() == R.id.ButSignIn)
			 {
				     Intent intent = new Intent(this,LoginScreen.class);
		 	    	 startActivity(intent);
			 }
			 if (v.getId() == R.id.Buthome)
			 {
				 
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
