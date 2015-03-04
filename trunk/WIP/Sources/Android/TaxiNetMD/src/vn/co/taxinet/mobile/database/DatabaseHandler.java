package vn.co.taxinet.mobile.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import vn.co.taxinet.mobile.model.Driver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "TaxiNet";

	// Table Names
	private static final String TABLE_DRIVER = "Driver";
	private static final String TABLE_TERM = "term";

	// DRIVER Table - column names
	private static final String COLUMN_DRIVER_ID = "COLUMN_DRIVER_ID";
	private static final String COLUMN_DRIVER_IMAGES = "COLUMN_DRIVER_IMAGES";
	private static final String COLUMN_DRIVER_FIRST_NAME = "COLUMN_DRIVER_FIRST_NAME";
	private static final String COLUMN_DRIVER_LAST_NAME = "COLUMN_DRIVER_LAST_NAME";
	private static final String COLUMN_DRIVER_EMAIL = "COLUMN_DRIVER_EMAIL";
	private static final String COLUMN_DRIVER_PASSWORD = "COLUMN_DRIVER_PASSWORD";
	private static final String COLUMN_DRIVER_PHONE_NUMBER = "COLUMN_DRIVER_PHONE_NUMBER";

	// TERM Table - column names
	private static final String COLUMN_TERM_ID = "COLUMN_TERM_ID";
	private static final String COLUMN_TERM_CONTENT = "COLUMN_TERM_CONTENT";

	// DRIVER table create statement
	private static final String CREATE_TABLE_DRIVER = "CREATE TABLE "
			+ TABLE_DRIVER + "(" + COLUMN_DRIVER_ID
			+ " TEXT," + COLUMN_DRIVER_IMAGES
			+ " TEXT," + COLUMN_DRIVER_FIRST_NAME + " TEXT,"
			+ COLUMN_DRIVER_LAST_NAME + " TEXT," + COLUMN_DRIVER_EMAIL
			+ " TEXT," + COLUMN_DRIVER_PASSWORD + " TEXT,"
			+ COLUMN_DRIVER_PHONE_NUMBER + " TEXT" + ")";

	// TERM table create statement
	private static final String CREATE_TABLE_TERM = "CREATE TABLE "
			+ TABLE_TERM + "(" + COLUMN_TERM_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TERM_CONTENT
			+ " TEXT" + ")";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		if (!db.isReadOnly()) {
			// Enable foreign key constraints
			db.execSQL("PRAGMA foreign_keys=ON;");
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// creating required tables
		db.execSQL(CREATE_TABLE_DRIVER);
		db.execSQL(CREATE_TABLE_TERM);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRIVER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TERM);
		// create new tables
		onCreate(db);
	}

	// -------------------common methods---------------------------------//
	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
				Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}

	// ------------------------ "driver" table methods
	// -----------------------------------//
	/*
	 * Add a term
	 */
	public long createTerm(String content) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_TERM_CONTENT, content);

		// Inserting Row
		long _id = db.insert(TABLE_TERM, null, values);
		if (db != null && db.isOpen())
			db.close();
		return _id;
	}

	/*
	 * Deleting a term
	 */
	public void deleteTerm() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from " + TABLE_TERM);

		if (db != null && db.isOpen())
			db.close();

	}

	public long createDriver(Driver driver) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_DRIVER_ID, driver.getId());
		values.put(COLUMN_DRIVER_FIRST_NAME, driver.getFirstName());
		values.put(COLUMN_DRIVER_LAST_NAME, driver.getLastName());
		values.put(COLUMN_DRIVER_IMAGES, driver.getImage());
		values.put(COLUMN_DRIVER_EMAIL, driver.getEmail());
		values.put(COLUMN_DRIVER_PHONE_NUMBER, driver.getPhoneNumber());
		values.put(COLUMN_DRIVER_PASSWORD, driver.getPassword());

		// Inserting Row
		long _id = db.insert(TABLE_DRIVER, null, values);
		if (db != null && db.isOpen())
			db.close();
		return _id;
	}

	/*
	 * find a driver
	 */
	public Driver findDriver() {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_DRIVER;

		Cursor c = db.rawQuery(selectQuery, null);
		Driver driver = null;
		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			driver = new Driver();
			System.out.println(c.getString(c.getColumnIndex(COLUMN_DRIVER_ID)));
			driver.setId(c.getString(c.getColumnIndex(COLUMN_DRIVER_ID)));
			driver.setFirstName((c.getString(c
					.getColumnIndex(COLUMN_DRIVER_FIRST_NAME))));
			driver.setLastName(c.getString(c
					.getColumnIndex(COLUMN_DRIVER_LAST_NAME)));
			driver.setImage(c.getString(c.getColumnIndex(COLUMN_DRIVER_IMAGES)));
			driver.setEmail(c.getString(c.getColumnIndex(COLUMN_DRIVER_EMAIL)));
			driver.setPhoneNumber(c.getString(c
					.getColumnIndex(COLUMN_DRIVER_PHONE_NUMBER)));
			driver.setPassword(c.getString(c
					.getColumnIndex(COLUMN_DRIVER_PASSWORD)));

		}

		if (db != null && db.isOpen())
			db.close();

		return driver;
	}

	/*
	 * Updating a driver
	 */
	public int updateDriver(Driver driver) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_DRIVER_ID, driver.getId());
		values.put(COLUMN_DRIVER_FIRST_NAME, driver.getFirstName());
		values.put(COLUMN_DRIVER_LAST_NAME, driver.getLastName());
		values.put(COLUMN_DRIVER_IMAGES, driver.getImage());
		values.put(COLUMN_DRIVER_EMAIL, driver.getEmail());
		values.put(COLUMN_DRIVER_PHONE_NUMBER, driver.getPhoneNumber());
		values.put(COLUMN_DRIVER_PASSWORD, driver.getPassword());
		db.execSQL("delete from " + TABLE_DRIVER);

		// updating row
		int temp = db.update(TABLE_DRIVER, values, COLUMN_DRIVER_ID + " = ?",
				new String[] { String.valueOf(driver.getId()) });
		if (db != null && db.isOpen())
			db.close();
		return temp;
	}

	/*
	 * Deleting a driver
	 */
	public int deleteDriverById(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		int temp = db.delete(TABLE_DRIVER, COLUMN_DRIVER_ID + " = ?",
				new String[] { id });

		if (db != null && db.isOpen())
			db.close();

		return temp;
	}

	public ArrayList<Driver> getListDriver() {
		ArrayList<Driver> listDrivers = new ArrayList<Driver>();
		String selectQuery = "SELECT  * FROM " + TABLE_DRIVER;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Driver driver = new Driver();
				driver.setId(c.getString(c.getColumnIndex(COLUMN_DRIVER_ID)));
				driver.setFirstName((c.getString(c
						.getColumnIndex(COLUMN_DRIVER_FIRST_NAME))));
				driver.setLastName(c.getString(c
						.getColumnIndex(COLUMN_DRIVER_LAST_NAME)));
				driver.setImage(c.getString(c
						.getColumnIndex(COLUMN_DRIVER_IMAGES)));
				driver.setEmail(c.getString(c
						.getColumnIndex(COLUMN_DRIVER_EMAIL)));
				driver.setPhoneNumber(c.getString(c
						.getColumnIndex(COLUMN_DRIVER_PHONE_NUMBER)));
				driver.setPassword(c.getString(c
						.getColumnIndex(COLUMN_DRIVER_PASSWORD)));
				listDrivers.add(driver);
			} while (c.moveToNext());
		}

		if (db != null && db.isOpen())
			db.close();

		return listDrivers;
	}

}
