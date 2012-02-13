package ningbq.db.dao;

import java.util.ArrayList;
import java.util.List;

import ningbq.bean.Location;
import ningbq.db.SoNingboDatabase;
import ningbq.db.SoNingboTables.LocationTable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class LocationAddressDao {

	private Context context = null;
	private final static String LOCATIONCATEGORY_TABLENAME = LocationTable.TABLE_NAME;
	private final static double DEFAULT_AREA = 5.0E-4;

	public LocationAddressDao(Context context) {
		this.context = context;
	}

	public SoNingboDatabase getSoNingboDB() {
		return SoNingboDatabase.getInstance(context);
	}

	public void insert(ContentValues values) {
		try {
			getSoNingboDB().getDb(true).insert(LOCATIONCATEGORY_TABLENAME,
					null, values);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public List<Location> getAllLocations() {
		Cursor cursor = getSoNingboDB().getDb(false).query(
				LOCATIONCATEGORY_TABLENAME, null, null, null, null, null, null);
		List<Location> tempList = new ArrayList<Location>();
		while (cursor.moveToNext()) {
			Location fc = new Location();

			fc.setId(cursor.getInt(cursor
					.getColumnIndex(LocationTable.Columns.ID)));
			fc.setSecondId(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.SECOND_ID)));
			fc.setLocationId(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.LOCATION_ID)));
			fc.setName_cn(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.NAME_CN)));
			fc.setName_en(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.NAME_EN)));
			fc.setAddress_cn(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.ADDRESS_CN)));
			fc.setAddress_en(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.ADDRESS_EN)));
			fc.setLatitude(cursor.getDouble(cursor
					.getColumnIndex(LocationTable.Columns.LATITUDE)));
			fc.setLongitude(cursor.getDouble(cursor
					.getColumnIndex(LocationTable.Columns.LONGITUDE)));

			tempList.add(fc);
		}
		return tempList;

	}

	public List<Location> getLocationsBySecondId(String secondId) {
		Cursor cursor = getSoNingboDB().getDb(false).query(
				LOCATIONCATEGORY_TABLENAME, null, "second_id=?",
				new String[] { secondId }, null, null, null);
		List<Location> tempList = new ArrayList<Location>();
		while (cursor.moveToNext()) {
			Location fc = new Location();

			fc.setId(cursor.getInt(cursor
					.getColumnIndex(LocationTable.Columns.ID)));
			fc.setSecondId(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.SECOND_ID)));
			fc.setLocationId(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.LOCATION_ID)));
			fc.setName_cn(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.NAME_CN)));
			fc.setName_en(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.NAME_EN)));
			fc.setAddress_cn(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.ADDRESS_CN)));
			fc.setAddress_en(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.ADDRESS_EN)));
			fc.setLatitude(cursor.getDouble(cursor
					.getColumnIndex(LocationTable.Columns.LATITUDE)));
			fc.setLongitude(cursor.getDouble(cursor
					.getColumnIndex(LocationTable.Columns.LONGITUDE)));

			tempList.add(fc);
		}
		return tempList;
	}

	public List<Location> getNearByLocations(double latitude, double longitude,
			double area) {
		if (area < 0.0) {
			area = DEFAULT_AREA;
		}
		if (latitude < 0.0 || longitude < 0.0) {
			return new ArrayList<Location>();
		}
		double frontLat = latitude + area;
		double backLat = latitude - area;
		double frontlon = longitude + area;
		double backlon = longitude - area;
		String fLat = String.valueOf(frontLat);
		String bLat = String.valueOf(backLat);
		String fLon = String.valueOf(frontlon);
		String bLon = String.valueOf(backlon);

		String sql = "select * from "
				+ LocationTable.TABLE_NAME
				+ " where longitude > ? and longitude < ? and latitude > ? and latitude < ? ";
		Cursor cursor = getSoNingboDB().getDb(false).rawQuery(sql,
				new String[] { bLon, fLon, bLat, fLat });
		
		List<Location> list = new ArrayList<Location>() ;
		while(cursor.moveToNext()){
			Location fc = new Location();

			fc.setId(cursor.getInt(cursor
					.getColumnIndex(LocationTable.Columns.ID)));
			fc.setSecondId(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.SECOND_ID)));
			fc.setLocationId(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.LOCATION_ID)));
			fc.setName_cn(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.NAME_CN)));
			fc.setName_en(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.NAME_EN)));
			fc.setAddress_cn(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.ADDRESS_CN)));
			fc.setAddress_en(cursor.getString(cursor
					.getColumnIndex(LocationTable.Columns.ADDRESS_EN)));
			fc.setLatitude(cursor.getDouble(cursor
					.getColumnIndex(LocationTable.Columns.LATITUDE)));
			fc.setLongitude(cursor.getDouble(cursor
					.getColumnIndex(LocationTable.Columns.LONGITUDE)));
			
			list.add(fc) ;
		}
		return list ;
	}
}
