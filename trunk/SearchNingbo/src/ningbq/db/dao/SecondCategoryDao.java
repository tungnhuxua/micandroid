package ningbq.db.dao;

import java.util.ArrayList;
import java.util.List;

import ningbq.bean.SecondCategory;
import ningbq.db.SoNingboDatabase;
import ningbq.db.SoNingboTables.SecondCategoryTable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SecondCategoryDao {

	private Context context = null;
	private final static String SECONDCATEGORY_TABLENAME = SecondCategoryTable.TABLE_NAME;
	private SQLiteDatabase db = null;

	public SecondCategoryDao(Context context) {
		this.context = context;
		this.db = getSoNingboDB().getDB();
	}

	public SoNingboDatabase getSoNingboDB() {
		return SoNingboDatabase.getInstance(context);
	}

	public void insert(ContentValues values) {
		try {
			getSoNingboDB().getDB().insert(SECONDCATEGORY_TABLENAME, null,
					values);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		db.close();

	}

	public List<SecondCategory> getAllSecondCategory() {
		Cursor cursor = db.query(SECONDCATEGORY_TABLENAME, null, null, null,
				null, null, null);
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
		cursor.close();
		db.close();
		return tempList;

	}

	public List<SecondCategory> getSecondCategoryByFirstId(String firstId) {
		Cursor cursor = db.query(SECONDCATEGORY_TABLENAME, null, "first_id=?",
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
		cursor.close();
		db.close();
		return tempList;

	}

	public SecondCategory queryByCategoryName(String categoryName) {
		SecondCategory sc = null;
		List<SecondCategory> list = getAllSecondCategory();
		int size = list.size();
		for (int i = 0; i < size;i++) {
			SecondCategory temp = list.get(i);
			if (categoryName.equals(temp.getName_cn())
					|| categoryName.equals(temp.getName_en())) {
				sc = temp;
				break;
			}
		}
		return sc;
		/*
		 * for (SecondCategory temp : list) { if
		 * (categoryName.equals(temp.getName_cn()) ||
		 * categoryName.equals(temp.getName_en())) { sc = temp; } break; }
		 * 
		 * return sc;
		 */
	}
}
