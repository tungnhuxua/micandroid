package ningbq.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SoNingboDatabase {

	private static SoNingboDatabase instance = null;

	private DataBaseHelp mOpenHelper = null;

	private SoNingboDatabase(Context context) {
		mOpenHelper = new DataBaseHelp(context);
	}

	public static synchronized SoNingboDatabase getInstance(Context context) {
		if (instance == null) {
			instance = new SoNingboDatabase(context);
		}
		return instance;
	}

	/**
	 * Get SQLiteDatabase Open Helper
	 * 
	 * @return
	 */
	public SQLiteOpenHelper getSQLiteOpenHelper() {
		return mOpenHelper;
	}

	/**
	 * Get Database Connection
	 * 
	 * @param writeable
	 * @return
	 */
	public SQLiteDatabase getDb(boolean writeable) {
		if (writeable) {
			return mOpenHelper.getWritableDatabase();
		} else {
			return mOpenHelper.getReadableDatabase();
		}
	}
	
	public void close(){
		if(null != instance){
			mOpenHelper.close() ;
			instance = null ;
		}
	}
}
