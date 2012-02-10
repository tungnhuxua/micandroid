package ningbq.main.widget;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ExplandPanel extends LinearLayout implements
		GestureDetector.OnGestureListener {

	private final static String TAG = "ExplandPanel";
	private final static int HANDLE_WIDTH = 30;
	private final static int MOVE_WIDTH = 20;
	private Button btnHandler;
	private Context mContext;
	private GestureDetector mGestureDetector;
	private LinearLayout panelContainer;
	private int mRightMargin = 0;
	private boolean mIsScrolling = false;
	private float mScrollX;
	private PanelClosedEvent panelClosedEvent = null;
	private PanelOpenedEvent panelOpenedEvent = null;

	public ExplandPanel(Context context, View otherView, int width, int height) {
		super(context);
		this.mContext = context;

		mGestureDetector = new GestureDetector(mContext, this);
		mGestureDetector.setIsLongpressEnabled(false);

		// 改变Panel附近组件的属性
		LayoutParams otherLP = (LayoutParams) otherView.getLayoutParams();
		otherLP.weight = 1;
		otherView.setLayoutParams(otherLP);

		// 设置Panel本身的属性
		LayoutParams lp = new LayoutParams(width, height);
		lp.rightMargin = -lp.width + HANDLE_WIDTH;
		mRightMargin = Math.abs(lp.rightMargin);
		this.setLayoutParams(lp);
		this.setOrientation(LinearLayout.HORIZONTAL);

		// 设置Handler的属性
		btnHandler = new Button(context);
		btnHandler.setLayoutParams(new LayoutParams(HANDLE_WIDTH, height));
		btnHandler.setOnTouchListener(handlerTouchEvent);
		this.addView(btnHandler);

		// 设置Container的属性
		panelContainer = new LinearLayout(context);
		panelContainer.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		this.addView(panelContainer);
	}
	
	
	@Override
	public boolean onDown(MotionEvent e) {
		mScrollX=0;
		mIsScrolling=false;
		return false;
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		LayoutParams lp = (LayoutParams) ExplandPanel.this.getLayoutParams();
		if (lp.rightMargin < 0)// CLOSE的状态
			new AsynMove().execute(new Integer[] { MOVE_WIDTH });// 正数展开
		else if (lp.rightMargin >= 0)// OPEN的状态
			new AsynMove().execute(new Integer[] { -MOVE_WIDTH });// 负数收缩
		return false;
	}
	
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		mIsScrolling=true;
		mScrollX+=distanceX;
		
		LayoutParams lp=(LayoutParams) ExplandPanel.this.getLayoutParams();
		if (lp.rightMargin < -1 && mScrollX > 0) {//往左拖拉
			lp.rightMargin = Math.min((lp.rightMargin + (int) mScrollX),0);
			ExplandPanel.this.setLayoutParams(lp);
			Log.e(TAG,lp.rightMargin+"");
		} 
		else if (lp.rightMargin > -(mRightMargin) && mScrollX < 0) {//往右拖拉
			lp.rightMargin = Math.max((lp.rightMargin + (int) mScrollX),-mRightMargin);
			ExplandPanel.this.setLayoutParams(lp);
		}
		
		if(lp.rightMargin==0 && panelOpenedEvent!=null){//展开之后
			panelOpenedEvent.onPanelOpened(ExplandPanel.this);//调用OPEN回调函数
		}
		else if(lp.rightMargin==-(mRightMargin) && panelClosedEvent!=null){//收缩之后
			panelClosedEvent.onPanelClosed(ExplandPanel.this);//调用CLOSE回调函数
		}
		Log.e(TAG,lp.rightMargin+"");
		
		return false;
	}


	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}


	@Override
	public void onShowPress(MotionEvent e) {

	}

	/**
	 * 定义收缩时的回调函数
	 * 
	 * @param event
	 */
	public void setPanelClosedEvent(PanelClosedEvent event) {
		this.panelClosedEvent = event;
	}

	/**
	 * 定义展开时的回调函数
	 * 
	 * @param event
	 */
	public void setPanelOpenedEvent(PanelOpenedEvent event) {
		this.panelOpenedEvent = event;
	}

	/**
	 * 把View放在Panel的Container
	 * 
	 * @param v
	 */
	public void fillPanelContainer(View v) {
		panelContainer.addView(v);
	}

	private View.OnTouchListener handlerTouchEvent = new View.OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_UP && // onScroll时的ACTION_UP
					mIsScrolling == true) {
				LayoutParams lp = (LayoutParams) ExplandPanel.this
						.getLayoutParams();
				if (lp.rightMargin >= (-mRightMargin / 2)) {// 往左超过一半
					new AsynMove().execute(new Integer[] { MOVE_WIDTH });// 正数展开
				} else if (lp.rightMargin < (-mRightMargin / 2)) {// 往右拖拉
					new AsynMove().execute(new Integer[] { -MOVE_WIDTH });// 负数收缩
				}
			}
			return mGestureDetector.onTouchEvent(event);
		}
	};

	/**
	 * 异步移动Panel
	 * 
	 */
	class AsynMove extends AsyncTask<Integer, Integer, Void> {

		@Override
		protected Void doInBackground(Integer... params) {
			int times;
			if (mRightMargin % Math.abs(params[0]) == 0)// 整除
				times = mRightMargin / Math.abs(params[0]);
			else
				// 有余数
				times = mRightMargin / Math.abs(params[0]) + 1;

			for (int i = 0; i < times; i++) {
				publishProgress(params);
				try {
					Thread.sleep(Math.abs(params[0]));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... params) {
			LayoutParams lp = (LayoutParams) ExplandPanel.this
					.getLayoutParams();
			if (params[0] < 0)
				lp.rightMargin = Math.max(lp.rightMargin + params[0],
						(-mRightMargin));
			else
				lp.rightMargin = Math.min(lp.rightMargin + params[0], 0);

			if (lp.rightMargin == 0 && panelOpenedEvent != null) {// 展开之后
				panelOpenedEvent.onPanelOpened(ExplandPanel.this);// 调用OPEN回调函数
			} else if (lp.rightMargin == -(mRightMargin)
					&& panelClosedEvent != null) {// 收缩之后
				panelClosedEvent.onPanelClosed(ExplandPanel.this);// 调用CLOSE回调函数
			}
			ExplandPanel.this.setLayoutParams(lp);
		}
	}

	public interface PanelClosedEvent {
		void onPanelClosed(View panel);
	}

	public interface PanelOpenedEvent {
		void onPanelOpened(View panel);
	}

}
