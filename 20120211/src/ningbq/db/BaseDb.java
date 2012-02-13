package ningbq.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDb {
	
	protected String mPrimaryKey = "_id" ;
	
	protected SQLiteOpenHelper mDbHelper ;
	
	public BaseDb(SQLiteOpenHelper mDbHelper){
		this.mDbHelper = mDbHelper ;
	}
	
	public BaseDb(SQLiteOpenHelper mDbHelper,String mPrimaryKey){
		this(mDbHelper) ;
		setmPrimaryKey(mPrimaryKey) ;
	}
	
	
	/**
	 * description:Get database Connection.
	 * 
	 * @param isWriter :Is Written to the database. 
	 * 
	 * @return SQLiteDatabase
	 */
	public SQLiteDatabase getDb(boolean isWriter){
		if(isWriter){
			return mDbHelper.getWritableDatabase() ;
		}else{
			return mDbHelper.getReadableDatabase() ;
		}
	}

	public String getmPrimaryKey() {
		return mPrimaryKey;
	}

	public void setmPrimaryKey(String mPrimaryKey) {
		this.mPrimaryKey = mPrimaryKey;
	}
	
	
	/**
	 * description:According to field and the field of value delete this line data.
	 * @param table:the table of name
	 * @param field:the field
	 * @param value:the field of value 
	 * 
	 * @return int
	 */
	public int deleteByField(String table,String field,String value){
		return getDb(true).delete(table, field + "=? ", new String[]{value}) ;
	}
	
	
	/**
	 * description:According to the primary key delete this line data.
	 * 
	 * @param table:the table of name
	 * @param id   :the primary key. 
	 * 
	 * @return int
	 */
	public int deleteById(String table,String id){
		return deleteByField(table,mPrimaryKey,id) ;
	}
	
	/**
	 * description:According to the primary key update this line data . 
	 * 
	 * @param table:the table of name
	 * @param id   :the primary key
	 * 
	 * @return int
	 */
	public int updateById(String table,String id,ContentValues values){
		return getDb(true).update(table, values, mPrimaryKey + "=? ", new String[]{id}) ; 
	}
	

}
