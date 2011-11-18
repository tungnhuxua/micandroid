package ningbq.util;

import android.content.res.AssetManager;
import android.graphics.Typeface;

public class SettingFont {

	// private final static String ARIAL_ROUNDED_MT_BOLD =
	// "Arial_Rounded_MT_Bold.ttf";

	private SettingFont() {

	}

	public static Typeface getTypeFace(AssetManager assetMgr) {
		return Typeface.createFromAsset(assetMgr,
				"fonts/Arial_Rounded_MT_Bold.ttf");
	}

}
