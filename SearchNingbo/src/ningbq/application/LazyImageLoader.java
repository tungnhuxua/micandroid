package ningbq.application;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class LazyImageLoader {
	private static final String TAG = "LazyImageLoader";
	public static final int HANDLER_MESSAGE_ID = 1;
	public static final String EXTRA_BITMAP = "extra_bitmap";
	public static final String EXTRA_IMAGE_URL = "extra_image_url";

	private ImageManager mImageMgr = new ImageManager(
			BaiduMapApiApplication.mContext);

	private CallbackManager mCallBackMgr = new CallbackManager();

	private BlockingQueue<String> mUrlList = new ArrayBlockingQueue<String>(50);
	private GetImageTask mTask = new GetImageTask();

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case HANDLER_MESSAGE_ID:
				final Bundle bundle = msg.getData();
				String url = bundle.getString(EXTRA_IMAGE_URL);
				Bitmap bitmap = (Bitmap) (bundle.get(EXTRA_BITMAP));
				// callback
				mCallBackMgr.call(url, bitmap);
				break;
			default:
				// do nothing.
			}
		}
	};
	
	
	/**
	 * 取图片, 可能直接从cache中返回, 或下载图片后返回
	 * 
	 * @param url
	 * @param callback
	 * @return
	 */
	public Bitmap get(String url, ImageLoaderCallback callback) {
		Bitmap bitmap = ImageCache.mDefaultBitmap;
		if (mImageMgr.isContains(url)) {
			bitmap = mImageMgr.get(url);
		} else {
			// bitmap不存在，启动Task进行下载
			mCallBackMgr.put(url, callback);
			startDownloadThread(url);
		}
		return bitmap;
	}

	private void startDownloadThread(String url) {
		if(url != null){
			addUrlToDownloadQueue(url) ;
		}
		State state = mTask.getState();
		if(Thread.State.NEW == state){
			mTask.start() ;
		}else if(Thread.State.TERMINATED == state){
			mTask = new GetImageTask();
			mTask.start() ;
		}
	}
	/**添加一个URL下载.*/
	private void addUrlToDownloadQueue(String url) {
		if (!mUrlList.contains(url)) {
			try {
				mUrlList.put(url);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class GetImageTask extends Thread {
		private volatile boolean mTaskTerminated = false;
		private static final int TIMEOUT = 3 * 60;
		private boolean isPermanent = true;

		@Override
		public void run() {
			try {
				while (!mTaskTerminated) {
					String url;
					if (isPermanent) {
						url = mUrlList.take();
					} else {
						url = mUrlList.poll(TIMEOUT, TimeUnit.SECONDS); // waiting
						if (null == url) {
							break;
						} // no more, shutdown
					}
					// Bitmap bitmap = ImageCache.mDefaultBitmap;
					final Bitmap bitmap = mImageMgr.safeGet(url);
					// use handler to process callback
					final Message m = handler.obtainMessage(HANDLER_MESSAGE_ID);
					Bundle bundle = m.getData();
					bundle.putString(EXTRA_IMAGE_URL, url);
					bundle.putParcelable(EXTRA_BITMAP, bitmap);
					handler.sendMessage(m);
				}
			} catch (InterruptedException e) {
				Log.e(TAG, e.getMessage());
			} catch (Exception ex) {
				Log.e(TAG, ex.getMessage());
			} finally {
				Log.v(TAG, "Get image task terminated.");
				mTaskTerminated = true;
			}
		}

		@SuppressWarnings("unused")
		public boolean isPermanent() {
			return isPermanent;
		}

		@SuppressWarnings("unused")
		public void setPermanent(boolean isPermanent) {
			this.isPermanent = isPermanent;
		}

		@SuppressWarnings("unused")
		public void shutDown() throws InterruptedException {
			mTaskTerminated = true;
		}

	}

	public static class CallbackManager {
		private static final String TAG = "CallbackManager";
		private ConcurrentHashMap<String, List<ImageLoaderCallback>> mCallbackMap;

		public CallbackManager() {
			mCallbackMap = new ConcurrentHashMap<String, List<ImageLoaderCallback>>();
		}

		public void put(String url, ImageLoaderCallback callBack) {
			Log.v(TAG, "url = " + url);
			if (!mCallbackMap.containsKey(url)) {
				mCallbackMap.put(url, new ArrayList<ImageLoaderCallback>());
			}

			mCallbackMap.get(url).add(callBack);
			Log.v(TAG,
					"Add callback to list, count(url)="
							+ mCallbackMap.get(url).size());
		}

		public void call(String url, Bitmap bitmap) {
			Log.v(TAG, "call url=" + url);
			List<ImageLoaderCallback> callBackList = mCallbackMap.get(url);
			if (callBackList == null) {
				Log.e(TAG, "callbackList=null");
				return;
			}
			for (ImageLoaderCallback callback : callBackList) {
				if (callback != null) {
					callback.refresh(url, bitmap);
				}
			}
			callBackList.clear();
			mCallbackMap.remove(url);
		}

	}

	public interface ImageLoaderCallback {
		void refresh(String url, Bitmap bitmap);
	}
}
