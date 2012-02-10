package ningbq.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class FirstCategoryHelp {
	
	private static final String FIRST_ID = "firstId" ;
	private static final String NAME_CN = "name_cn" ;
	private static final String NAME_EN = "name_en" ;
	private static final String PRIMARY_ID = "_id" ;
	
	private final Context context ;
	private DataBaseHelp mDbHelp ;
	
	public FirstCategoryHelp(Context context){
		this.context = context ;
	} 
	
	
	private SQLiteDatabase openDB(boolean isWriter){
		if(isWriter){
			return mDbHelp.getWritableDatabase() ;
		}else{
			return mDbHelp.getReadableDatabase() ;
		}
	}

}
