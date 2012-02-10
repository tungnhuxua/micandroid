package ningbq.db;

import ningbq.db.SoNingboTables.FirstCategoryTable;
import ningbq.db.SoNingboTables.LocationTable;
import ningbq.db.SoNingboTables.SecondCategoryTable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelp extends SQLiteOpenHelper {

	private static final String TAG = "DataBaseHelp";

	private static final String DB_NAME = "soningbo.db";
	
	private static final Integer VERSION = 1;

	public DataBaseHelp(Context _context) {
		super(_context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			createAllTables(db) ;
			Log.i(TAG, "Create Tables Success!");
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e(TAG, "Create TablesError!");
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		try {
			Log.w(TAG, "Starting Drop All Tables...");
			dropAllTables(db) ;
			Log.i(TAG, "All Tables Remove Success!");
			Log.i(TAG, "All Tables Ready Create...");
			onCreate(db);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e(TAG, "Create TablesError!");
		}
	}
	
	
	private static void createAllTables(SQLiteDatabase db){
		db.execSQL(FirstCategoryTable.getCreateSQL()) ;
		db.execSQL(SecondCategoryTable.getCreateSQL()) ;
		db.execSQL(LocationTable.getCreateSQL()) ;
	}
	
	
	private static void dropAllTables(SQLiteDatabase db){
		db.execSQL(FirstCategoryTable.getDropSQL()) ;
		db.execSQL(SecondCategoryTable.getDropSQL()) ;
		db.execSQL(LocationTable.getDropSQL()) ;
	}

}
