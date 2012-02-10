package ningbq.util;

import android.content.Context;
import android.os.Vibrator;

public class VibratorUtil {
	
	private VibratorUtil(){
		
	}
	public static void setVibrator(Context context){
		Vibrator vibrator =(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE) ;
		vibrator.vibrate(new long[]{5,5,5,5}, -1) ;
	}
}
