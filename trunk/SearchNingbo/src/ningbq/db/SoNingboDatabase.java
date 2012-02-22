package ningbq.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SoNingboDatabase {

	private static SoNingboDatabase instance = null;

	private DataBaseHelp dataBaseHelp = null; // 数据库与表相关

	private SoNingboDatabase(Context context) {
		dataBaseHelp = new DataBaseHelp(context);
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

		return dataBaseHelp;
	}

	/**
	 * Get Database Connection
	 * 
	 * @param writeable
	 * @return
	 */
	public SQLiteDatabase getDB() {
		try {
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
					dataBaseHelp.databaseFileName, null);
			
			return db;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public void close() {
		if (null != instance) {
			dataBaseHelp.close();
			instance = null;
		}
	}
}
