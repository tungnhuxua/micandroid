package ningbq.db.dao;

import java.util.ArrayList;
import java.util.List;

import ningbq.bean.SecondCategory;
import ningbq.db.SoNingboDatabase;
import ningbq.db.SoNingboTables.SecondCategoryTable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class SecondCategoryDao {

	private Context context = null;
	private final static String SECONDCATEGORY_TABLENAME = SecondCategoryTable.TABLE_NAME;

	public SecondCategoryDao(Context context) {
		this.context = context;
	}

	public SoNingboDatabase getSoNingboDB() {
		return SoNingboDatabase.getInstance(context);
	}

	public void insert(ContentValues values) {
		try {
			getSoNingboDB().getDb(true).insert(SECONDCATEGORY_TABLENAME, null,
					values);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public List<SecondCategory> getAllSecondCategory() {
		Cursor cursor = getSoNingboDB().getDb(false).query(
				SECONDCATEGORY_TABLENAME, null, null, null, null, null, null);
		List<SecondCategory> tempList = new ArrayList<SecondCategory>();
		while (cursor.moveToNext()) {
			SecondCategory fc = new SecondCategory();
			fc.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			fc.setFirstId(cursor.getString(cursor.getColumnIndex("first_id")));
			fc.setSecondId(cursor.getString(cursor.getColumnIndex("second_id")));
			fc.setName_cn(cursor.getString(cursor.getColumnIndex("name_cn")));
			fc.setName_en(cursor.getString(cursor.getColumnIndex("name_en")));
			tempList.add(fc);
		}
		return tempList;

	}

	public List<SecondCategory> getSecondCategoryByFirstId(String firstId) {
		Cursor cursor = getSoNingboDB().getDb(false).query(
				SECONDCATEGORY_TABLENAME, null, "first_id=?",
				new String[] { firstId }, null, null, null);
		List<SecondCategory> tempList = new ArrayList<SecondCategory>();
		while (cursor.moveToNext()) {
			SecondCategory fc = new SecondCategory();
			fc.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			fc.setFirstId(cursor.getString(cursor.getColumnIndex("first_id")));
			fc.setSecondId(cursor.getString(cursor.getColumnIndex("second_id")));
			fc.setName_cn(cursor.getString(cursor.getColumnIndex("name_cn")));
			fc.setName_en(cursor.getString(cursor.getColumnIndex("name_en")));
			tempList.add(fc);
		}
		return tempList;

	}

}
