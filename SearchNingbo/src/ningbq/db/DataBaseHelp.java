package ningbq.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import ningbq.db.SoNingboTables.FirstCategoryTable;
import ningbq.db.SoNingboTables.LocationTable;
import ningbq.db.SoNingboTables.SecondCategoryAndLocationTable;
import ningbq.db.SoNingboTables.SecondCategoryTable;
import ningbq.main.R;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Description:创建数据库,创建和删除表.
 *
 * @author Devon.Ning
 * @2012-2-6
 *
 */
public class DataBaseHelp extends SQLiteOpenHelper {

	private static final String TAG = "DataBaseHelp";

	private static final String DB_NAME = "soningbo.db";
	
	private static final Integer VERSION = 1;
	
	private static final String DATABASE_PATH = "/sdcard/soningbo/data/data/ningbq.media/databases" ;
	public  final String databaseFileName = DATABASE_PATH + "/" + DB_NAME ;
	private Context mContext = null;
	private File file = null ;

	public DataBaseHelp(Context _context) {
		super(_context, DB_NAME, null, VERSION);
		this.mContext = _context ;
		checkDBExists();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			//createAllTables(db) ;
			Log.i(TAG, "Create Tables");
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e(TAG, "Create TablesError!");
		}

	}
	
	@Override
	public synchronized SQLiteDatabase getReadableDatabase() {
		checkDBExists();
		if (null == file) {
			file = new File(databaseFileName);
		}
		if (file.exists()) {
			return super.getReadableDatabase();
			// SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
			// databaseFileName, null);
		} else {
			return null;
		}
	}

	@Override
	public synchronized SQLiteDatabase getWritableDatabase() {
		checkDBExists();
		if (new File(databaseFileName).exists()) {
			// SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
			// databaseFileName, null);
			return super.getWritableDatabase();
		} else {
			return null;
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
	
	
	/**新建表*/
/*	private static void createAllTables(SQLiteDatabase db){
		db.execSQL(FirstCategoryTable.getCreateSQL()) ;
		db.execSQL(SecondCategoryTable.getCreateSQL()) ;
		db.execSQL(LocationTable.getCreateSQL()) ;
		db.execSQL(SecondCategoryAndLocationTable.getCreateSQL()) ;
	}*/
	
	/**删除表*/
	private static void dropAllTables(SQLiteDatabase db){
		db.execSQL(FirstCategoryTable.getDropSQL()) ;
		db.execSQL(SecondCategoryTable.getDropSQL()) ;
		db.execSQL(LocationTable.getDropSQL()) ;
		db.execSQL(SecondCategoryAndLocationTable.getDropSQL()) ;
	}
	
	private void checkDBExists() {
		File dir = new File(DATABASE_PATH) ;
		if(!dir.exists()){
			dir.mkdirs() ;
		}
		if(!new File(databaseFileName).exists()){
			try {
				// 3,读取资源并创建流
				InputStream is = mContext.getResources().openRawResource(
						R.raw.soningbo);
				FileOutputStream fos = new FileOutputStream(databaseFileName);
				// 4,复制
				byte[] buffer = new byte[8192];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				// 5,关闭流
				fos.close();
				is.close();
			} catch (NotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
