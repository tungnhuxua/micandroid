package ningbq.util;

import ningbq.address.LocationAddressListScreen;
import ningbq.main.R;
import ningbq.search.SearchFirstCategoryScreen;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ViewUtil implements OnClickListener {

	private Context context = null;


	public static void initView(Context context, View view, int layout) {
		final Intent intent = new Intent();
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Context context = v.getContext();
				VibratorUtil.setVibrator(context);
				switch (v.getId()) {
				case R.id.locationFirstRelative:
					intent.setClass(context, SearchFirstCategoryScreen.class);
					context.startActivity(intent);
					break;
				case R.id.locationSecondRelative:
					break;
				case R.id.locationFourthRelative:
					intent.setClass(context, LocationAddressListScreen.class);
					context.startActivity(intent);
					break;
				default:
					Toast.makeText(context, "success", Toast.LENGTH_LONG)
							.show();
					break;
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		context = v.getContext() ;
		VibratorUtil.setVibrator(context);
		switch (v.getId()) {
		case R.id.locationFirstRelative: // Search
			intent.setClass(context, SearchFirstCategoryScreen.class);
			context.startActivity(intent);
			break;
		case R.id.locationSecondRelative:
			break;
		case R.id.locationFourthRelative: // Add location
			intent.setClass(context, LocationAddressListScreen.class);
			context.startActivity(intent);
			break;
		default:
			Toast.makeText(context, "success", Toast.LENGTH_LONG).show();
			break;
		}
	}
}
