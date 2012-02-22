package ningbq.db.dao;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import ningbq.bean.Location;
import ningbq.db.SoNingboDatabase;
import ningbq.db.SoNingboTables.LocationTable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

public class LocationAddressDao {

	private Context context = null;
	private final static String LOCATIONCATEGORY_TABLENAME = LocationTable.TABLE_NAME;
	private final static double DEFAULT_AREA = 5.0E-4;
	private SQLiteDatabase db = null;

	public LocationAddressDao(Context context) {
		this.context = context;
		this.db = getSoNingboDB().getDB();
	}

	public SoNingboDatabase getSoNingboDB() {
		return SoNingboDatabase.getInstance(context);
	}

	public long insert(Location loc) {
		try {
			ContentValues values = new ContentValues() ;
			values.put(LocationTable.Columns.NAME_CN, loc.getName_cn()) ;
			values.put(LocationTable.Columns.NAME_EN, loc.getName_en()) ;
			values.put(LocationTable.Columns.ADDRESS_CN, loc.getAddress_cn()) ;
			values.put(LocationTable.Columns.ADDRESS_EN, loc.getAddress_en()) ;
			values.put(LocationTable.Columns.SECOND_ID, loc.getSecondId()) ;
			values.put(LocationTable.Columns.LOCATION_ID, loc.getLocationId()) ;
			values.put(LocationTable.Columns.LATITUDE, loc.getLatitude()) ;
			values.put(LocationTable.Columns.LONGITUDE, loc.getLongitude()) ;
			values.put(LocationTable.Columns.TELEPHONE, loc.getTelephone()) ;
			
			return db.insert(LOCATIONCATEGORY_TABLENAME, null, values);
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}

	}
	
	public List<Location> getAllLocations() {
		Cursor cursor = db.query(LOCATIONCATEGORY_TABLENAME, null, null, null,
				null, null, null);
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
		cursor.close();
		db.close();
		return tempList;

	}

	public List<Location> getLocationsBySecondId(String secondId) {
		Cursor cursor = db.query(LOCATIONCATEGORY_TABLENAME, null,
				"second_id=?", new String[] { secondId }, null, null, null);
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
		cursor.close();
		db.close();
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
		Cursor cursor = db.rawQuery(sql,
				new String[] { bLon, fLon, bLat, fLat });

		List<Location> list = new ArrayList<Location>();
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

			// ByteArrayInputStream stream = new ByteArrayInputStream(
			// cursor.getBlob(cursor
			// .getColumnIndex(LocationTable.Columns.LOCATIONICON)));
			// Drawable icon = Drawable.createFromStream(stream, "image") ;
			// fc.setLocationIcon(icon) ;

			list.add(fc);
		}

		cursor.close();
		db.close();
		return list;
	}

	/** update LocationInformation */
	public int updateLocationAddress(Bitmap locationIcon, String locationId) {
		ContentValues values = new ContentValues();
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		locationIcon.compress(Bitmap.CompressFormat.PNG, 100, os);
		values.put("location_icon", os.toByteArray());
		int id = db.update(LOCATIONCATEGORY_TABLENAME, values,
				" location_id = ? ", new String[] { locationId });
		Log.e("updateLocationAddress-SoNingbo", id + "");
		db.close();
		return id;

	}

}
