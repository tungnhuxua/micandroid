package ningbq.address;

import ningbq.main.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class SelectionTakePhotoScreen extends Activity implements
		OnClickListener {

	private Button set_photo_cancel = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.set_profile_photo);
		createLayout();
	}

	private void createLayout() {
		set_photo_cancel = (Button) findViewById(R.id.set_photo_cancel);
		set_photo_cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.set_photo_cancel:
			intent.setClass(this, LocationTakePhotoScreen.class);
			overridePendingTransition(R.anim.selection_take_photo_fadeout,
					R.anim.selection_take_photo_fadein);
			break;

		default:
			break;
		}
	}

}
