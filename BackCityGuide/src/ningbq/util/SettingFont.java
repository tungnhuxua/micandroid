package ningbq.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

public class SettingFont {

	// private final static String ARIAL_ROUNDED_MT_BOLD =
	// "Arial_Rounded_MT_Bold.ttf";

	private SettingFont() {

	}

	public static Typeface getTypeFace(AssetManager assetMgr) {
		return Typeface.createFromAsset(assetMgr,
				"fonts/Arial_Rounded_MT_Bold.ttf");
	}
	
	public static Drawable SettingBackgroundImage(int id,Context context){
		Resources resource = context.getResources() ;
		return resource.getDrawable(id) ;
	}

}
