package ningbq.service;

import java.util.HashMap;
import java.util.Map;

import ningbq.db.DataBaseHelp;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CityService {
	private static final String TAG = "CityService" ;

	private DataBaseHelp dbHelp;
	

	public CityService(Context context) {
		this.dbHelp = new DataBaseHelp(context);
	}

	public void save(String cityName, String distance) {
		try {
			SQLiteDatabase db = dbHelp.getWritableDatabase();
			db.execSQL("insert into tb_city(city,distance) values(?,?)",
					new String[] { cityName, distance });
			Log.v(TAG,"saved success!") ;
		} catch (Exception ex) {

		}

	}

	public long getCount() {
		SQLiteDatabase db = dbHelp.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from tb_city", null);
		cursor.moveToFirst();
		return cursor.getLong(0);
	}
	
	public Map<String,String> getAll(){
		
		Map<String,String> citys = new HashMap<String,String>() ;
		
		SQLiteDatabase db = dbHelp.getReadableDatabase() ;
		Cursor cursor = db.rawQuery("select * from tb_city", null) ;
		while(cursor.moveToNext()){
			String cname = cursor.getString(1) ;
			String cdistance = cursor.getString(2) ;
			citys.put(cname,cdistance) ;
		}
		return citys ;
	}
	

}
