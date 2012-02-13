package ningbq.db.dao;

import java.util.ArrayList;
import java.util.List;

import ningbq.bean.FirstCategory;
import ningbq.db.SoNingboDatabase;
import ningbq.db.SoNingboTables.FirstCategoryTable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class FirstCategoryDao {

	private Context context = null;
	private final static String FIRSTCATEGORY_TABLENAME = FirstCategoryTable.TABLE_NAME;

	public FirstCategoryDao(Context context) {
		this.context = context;
	}

	public SoNingboDatabase getSoNingboDB() {
		return SoNingboDatabase.getInstance(context);
	}

	public void insert(ContentValues values) {
		try {
			getSoNingboDB().getDb(true).insert(FIRSTCATEGORY_TABLENAME, null,
					values);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public List<FirstCategory> getAllFirstCategory() {
		Cursor cursor = getSoNingboDB().getDb(false).query(FIRSTCATEGORY_TABLENAME, null, null,
				null, null, null, null);
		List<FirstCategory> tempList = new ArrayList<FirstCategory>() ;
		while(cursor.moveToNext()){
			FirstCategory fc = new FirstCategory() ;
			fc.setId(cursor.getInt(cursor.getColumnIndex("_id"))) ;
			fc.setFirstId(cursor.getString(cursor.getColumnIndex("first_id"))) ;
			fc.setName_cn(cursor.getString(cursor.getColumnIndex("name_cn"))) ;
			fc.setName_en(cursor.getString(cursor.getColumnIndex("name_en"))) ;
			tempList.add(fc) ;
		}
		return tempList ;

	}
}
