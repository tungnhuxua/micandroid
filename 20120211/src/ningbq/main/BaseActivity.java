package ningbq.main;

import ningbq.main.widget.MyHorizontalScrollView;
import ningbq.main.widget.MyHorizontalScrollView.SizeCallback;
import ningbq.util.VibratorUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {

	public MyHorizontalScrollView scrollView;
	public View menuView; //mainScreen
	public View appView; //appScreen
	public ViewGroup viewGroup ; //button headBar
	public View[] children  ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE) ;
		_onCreate(savedInstanceState) ;
	}
	
	
	public boolean _onCreate(Bundle savedInstanceState){
		boolean flag = true ;
		if(flag){
			LayoutInflater inflater = LayoutInflater.from(this);
			scrollView = (MyHorizontalScrollView) inflater.inflate(
					R.layout.indexpageview, null);
			setContentView(scrollView);
			
			menuView = inflater.inflate(R.layout.mainscreen, null);
			appView = inflater.inflate(getApplicationLayoutResource(), null) ;
			viewGroup = (ViewGroup)appView.findViewById(getViewGroupResource()) ;
			children = new View[] { menuView, appView };
		}else{
			flag = false ;
		}
		return flag ;
	}
	
	public abstract int getApplicationLayoutResource() ;
	
	public abstract int getViewGroupResource() ;
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater  inflater = new MenuInflater(this) ;
		inflater.inflate(R.menu.menu, menu) ;
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent() ;
		switch (item.getItemId()) {
		case R.id.home:
			intent.setClass(getApplicationContext(), MainScreenActivity.class) ;
			startActivity(intent) ;
			return true ;
		case R.id.about:
			Toast.makeText(getApplicationContext(), "About", Toast.LENGTH_LONG).show() ;
			return true ;
		case R.id.exit:
			Toast.makeText(getApplicationContext(), "Exit", Toast.LENGTH_LONG).show() ;
			return true ;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}





	/**
	 * Helper for examples with a HSV that should be scrolled by a menu View's
	 * width.
	 */
	public static class ClickListenerForScrolling implements OnClickListener {
		HorizontalScrollView scrollView;
		View menu;
		/**
		 * Menu must NOT be out/shown to start with.
		 */
		boolean menuOut = false;

		public ClickListenerForScrolling(HorizontalScrollView scrollView,
				View menu) {
			super();
			this.scrollView = scrollView;
			this.menu = menu;
		}

		@Override
		public void onClick(View v) {
			Context context = menu.getContext();
			int menuWidth = menu.getMeasuredWidth();

			// Ensure menu is visible
			menu.setVisibility(View.VISIBLE);
			VibratorUtil.setVibrator(context) ;
			if (!menuOut) {
				// Scroll to 0 to reveal menu
				int left = 0;
				scrollView.smoothScrollTo(left, 0);
				
			} else {
				// Scroll to menuWidth so menu isn't on screen.
				int left = menuWidth;
				scrollView.smoothScrollTo(left, 0);
				//intent.setClass(context, cls)
			}
			menuOut = !menuOut;
		}
	}
	
	
    /**
     * Helper that remembers the width of the 'slide' button, so that the 'slide' button remains in view, even when the menu is
     * showing.
     */
    public static class SizeCallbackForMenu implements SizeCallback {
        int btnWidth;
        View btnSlide;

        public SizeCallbackForMenu(View btnSlide) {
            super();
            this.btnSlide = btnSlide;
        }

        @Override
        public void onGlobalLayout() {
            btnWidth = btnSlide.getMeasuredWidth();
        }

        @Override
        public void getViewSize(int idx, int w, int h, int[] dims) {
            dims[0] = w;
            dims[1] = h;
            final int menuIdx = 0;
            if (idx == menuIdx) {
                dims[0] = w - btnWidth;
            }
        }
    }
    
}
