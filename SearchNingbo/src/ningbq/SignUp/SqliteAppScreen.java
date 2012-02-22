package ningbq.SignUp;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import ningbq.main.R;
import ningbq.service.CityService;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SqliteAppScreen extends Activity implements OnClickListener {
	
	CityService cityService = new CityService(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sqliteapp);
		createLayout();
	}

	private void createLayout() {
		Button sqlite_submit_btn = (Button) findViewById(R.id.sqliteSubmitBtn);
		Button sqlite_view_btn = (Button)findViewById(R.id.sqliteViewBtn) ;
		sqlite_submit_btn.setOnClickListener(this);
		sqlite_view_btn.setOnClickListener(this) ;
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.sqliteSubmitBtn) {
			// Intent intent = new Intent(this,MainScrrenWithRecentView.class) ;
			// startActivity(intent) ;

			EditText tmpcity = (EditText) findViewById(R.id.sqliteETxtUn);
			EditText tmpdistance = (EditText) findViewById(R.id.sqliteEtxtPs);

			String city = tmpcity.getText().toString();
			String distance = tmpdistance.getText().toString();
			try {
				cityService.save(city, distance);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		
		if(v.getId() == R.id.sqliteViewBtn){
			Map<String, String> mcity = cityService.getAll();
			Iterator<Entry<String, String>> it = mcity.entrySet()
					.iterator();
			if (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				Toast.makeText(this, "city:" + entry.getKey()
						+ "   distance:" + entry.getValue(),
						Toast.LENGTH_SHORT).show();
			}
		}
	}

}
