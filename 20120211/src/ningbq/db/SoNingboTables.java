package ningbq.db;

public abstract class SoNingboTables {

	/** 用户信息表 */
	public static class UserTable {
		public static final String TABLE_NAME = "tb_user";

		public static class Columns {
			public static final String ID = "_id";
			public static final String USER_ID = "user_id";
			public static final String USER_NAME = "user_name";
			public static final String USER_EMAIL = "user_email";
			public static final String LOCATION = "location";
			public static final String DESCRIPTION = "description";
			public static final String URL = "url";
			public static final String ISMANAGER = "is_manager";
			public static final String PROFILE_IMAGE_URL = "profile_image_url";
			public static final String FOLLOWERS_COUNT = "followers_count";
			public static final String FRIENDS_COUNT = "friends_count";
			public static final String FAVOURITES_COUNT = "favourites_count";
			public static final String STATUSES_COUNT = "statuses_count";
			public static final String CREATED_AT = "created_at";
			public static final String FOLLOWING = "following";
			public static final String NOTIFICATIONS = "notifications";
			public static final String UTC_OFFSET = "utc_offset";
			public static final String LOAD_TIME = "load_time";
		}

		public static String getCreateSQL() {
			String createString = TABLE_NAME + "( " + Columns.ID
					+ " INTEGER PRIMARY KEY, " + Columns.USER_ID
					+ " TEXT UNIQUE NOT NULL, " + Columns.USER_NAME
					+ " TEXT UNIQUE NOT NULL, " + Columns.USER_EMAIL
					+ " TEXT, " + Columns.LOCATION + " TEXT, "
					+ Columns.DESCRIPTION + " TEXT, " + Columns.URL + " TEXT, "
					+ Columns.ISMANAGER + " INT DEFAULT 0, "
					+ Columns.PROFILE_IMAGE_URL + " TEXT "
					+ Columns.FOLLOWERS_COUNT + " INT, "
					+ Columns.FRIENDS_COUNT + " INT, "
					+ Columns.FAVOURITES_COUNT + " INT, "
					+ Columns.STATUSES_COUNT + " INT, " + Columns.CREATED_AT
					+ " INT, " + Columns.FOLLOWING + " INT DEFAULT 0, "
					+ Columns.NOTIFICATIONS + " INT DEFAULT 0, "
					+ Columns.UTC_OFFSET + " TEXT, " + Columns.LOAD_TIME
					+ " TIMESTAMP default (DATETIME('now', 'localtime')) "
					+ ");";

			return "CREATE TABLE " + createString;
		}

		public static String getDropSQL() {
			return "DROP TABLE " + TABLE_NAME;
		}

		public static String[] getIndexColumns() {
			return new String[] { Columns.ID, Columns.USER_ID,
					Columns.USER_NAME, Columns.USER_EMAIL, Columns.LOCATION,
					Columns.DESCRIPTION, Columns.URL, Columns.ISMANAGER,
					Columns.PROFILE_IMAGE_URL, Columns.FOLLOWERS_COUNT,
					Columns.FRIENDS_COUNT, Columns.FAVOURITES_COUNT,
					Columns.STATUSES_COUNT, Columns.CREATED_AT,
					Columns.FOLLOWING, Columns.NOTIFICATIONS,
					Columns.UTC_OFFSET, Columns.LOAD_TIME };
		}

	}

	/** 一级栏目表 */
	public static class FirstCategoryTable {
		public static final String TABLE_NAME = "tb_first_category";

		public static class Columns {
			public static final String ID = "_id";
			public static final String FRIST_ID = "first_id";
			public static final String NAME_CN = "name_cn";
			public static final String NAME_EN = "name_en";
		}

		public static String getCreateSQL() {
			String createString = TABLE_NAME + "( " + Columns.ID
					+ " INTEGER PRIMARY KEY, " + Columns.FRIST_ID
					+ " TEXT UNIQUE NOT NULL, " + Columns.NAME_CN + " TEXT, "
					+ Columns.NAME_EN + " TEXT " + ");";

			return "CREATE TABLE " + createString;
		}

		public static String getDropSQL() {
			return "DROP TABLE " + TABLE_NAME;
		}

		public static String[] getIndexColumns() {
			return new String[] { Columns.ID, Columns.FRIST_ID,
					Columns.NAME_CN, Columns.NAME_EN };
		}
	}

	/** 二级栏目表 */
	public static class SecondCategoryTable {
		public static final String TABLE_NAME = "tb_second_category";

		public static class Columns {
			public static final String ID = "_id";
			public static final String SECOND_ID = "second_id";
			public static final String FIRST_ID = "first_id";
			public static final String NAME_CN = "name_cn";
			public static final String NAME_EN = "name_en";
		}

		public static String getCreateSQL() {
			String createString = TABLE_NAME + "( " + Columns.ID
					+ " INTEGER PRIMARY KEY, " + Columns.SECOND_ID
					+ " TEXT UNIQUE NOT NULL, " + Columns.FIRST_ID + " TEXT, "
					+ Columns.NAME_CN + " TEXT, " + Columns.NAME_EN + " TEXT "
					+ ");";

			return "CREATE TABLE " + createString;
		}

		public static String getDropSQL() {
			return "DROP TABLE " + TABLE_NAME;
		}

		public static String[] getIndexColumns() {
			return new String[] { Columns.ID, Columns.SECOND_ID,
					Columns.FIRST_ID, Columns.NAME_CN, Columns.NAME_EN };
		}

	}

	/** 二级栏目与位置的中间表. */
	public static class SecondCategoryAndLocationTable {
		public static final String TABLE_NAME = "tb_second_category_location";

		public static class Columns {
			public static final String ID = "_id";
			public static final String CATEGORY2_ID = "category2_id";
			public static final String LOCATION_ID = "location_id";
		}

		public static String getCreateSQL() {
			String createString = TABLE_NAME + "( " + Columns.ID
					+ " INTEGER PRIMARY KEY, " + Columns.CATEGORY2_ID
					+ " TEXT NOT NULL, " + Columns.LOCATION_ID
					+ " TEXT NOT NULL" + ");";

			return "CREATE TABLE " + createString;

		}

		public static String getDropSQL() {
			return "DROP TABLe " + TABLE_NAME;
		}
	}

	/** 本地的位置信息表 */
	public static class LocationTable {
		public static final String TABLE_NAME = "tb_location";

		public static class Columns {
			public static final String ID = "_id";
			public static final String LOCATION_ID = "location_id";
			public static final String SECOND_ID = "second_id";
			public static final String NAME_CN = "name_cn";
			public static final String NAME_EN = "name_en";
			public static final String ADDRESS_CN = "address_cn";
			public static final String ADDRESS_EN = "address_en";
			public static final String TELEPHONE = "telephone";
			public static final String LONGITUDE = "longitude";
			public static final String LATITUDE = "latitude";
		}

		public static String getCreateSQL() {
			String createString = TABLE_NAME + "( " + Columns.ID
					+ " INTEGER PRIMARY KEY, " + Columns.LOCATION_ID
					+ " TEXT UNIQUE NOT NULL, " + Columns.SECOND_ID + " TEXT, "
					+ Columns.NAME_CN + " TEXT, " + Columns.NAME_EN + " TEXT, "
					+ Columns.ADDRESS_CN + " TEXT, " + Columns.ADDRESS_EN
					+ " TEXT, " + Columns.TELEPHONE + " TEXT, "
					+ Columns.LONGITUDE + " DOUBLE, " + Columns.LATITUDE
					+ " DOUBLE " + ");";

			return "CREATE TABLE " + createString;
		}

		public static String getDropSQL() {
			return "DROP TABLE " + TABLE_NAME;
		}

		public static String[] getIndexColumns() {
			return new String[] { Columns.ID, Columns.LOCATION_ID,
					Columns.SECOND_ID, Columns.NAME_CN, Columns.NAME_EN,
					Columns.ADDRESS_CN, Columns.ADDRESS_EN, Columns.TELEPHONE,
					Columns.LONGITUDE, Columns.LATITUDE };
		}
	}

}
