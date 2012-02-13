package ningbq.main;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class MainTestActivity extends Activity {
	//private static final String TAG = "MainTestActivity" ;
	
	private SlidingDrawer mSlidingDrawer ;
	private ImageButton openTextView ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.recentviewpanel) ;
		getWindow().setFormat(PixelFormat.RGBA_8888);   
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER) ;
		mSlidingDrawer = (SlidingDrawer)findViewById(R.id.indexPageSliding) ;
		openTextView = (ImageButton)findViewById(R.id.indexPageScreenHandle) ;
		
		mSlidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			@Override
			public void onDrawerOpened() {
				openTextView.setImageResource(R.drawable.ic_launcher_allshow); 
			}
		}) ;
		
		mSlidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				openTextView.setImageResource(R.drawable.ic_launcher_allhide); 
			}
		}) ;
		
	}


}
