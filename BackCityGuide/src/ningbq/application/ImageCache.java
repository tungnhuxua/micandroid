package ningbq.application;

import ningbq.main.R;
import android.graphics.Bitmap;

public interface ImageCache {
	public static Bitmap mDefaultBitmap = ImageManager
			.drawableToBitmap(SearchNingboApplication.mContext.getResources()
					.getDrawable(R.drawable.add_photo_without_cross));

	public Bitmap get(String url);

	public void put(String url, Bitmap bitmap);

}
