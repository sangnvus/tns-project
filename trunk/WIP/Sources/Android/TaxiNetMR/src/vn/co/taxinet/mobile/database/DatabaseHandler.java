package vn.co.taxinet.mobile.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import vn.co.taxinet.mobile.model.Rider;
import vn.co.taxinet.mobile.model.Trip;
import vn.co.taxinet.mobile.utils.Constants.TripStatus;

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
	private static final String TABLE_RIDER = "Driver";
	private static final String TABLE_TERM = "term";
	private static final String TABLE_TRIP_STATUS = "trip_status";

	// DRIVER Table - column names
	private static final String COLUMN_RIDER_ID = "COLUMN_DRIVER_ID";
	private static final String COLUMN_RIDER_IMAGES = "COLUMN_DRIVER_IMAGES";
	private static final String COLUMN_RIDER_FIRST_NAME = "COLUMN_DRIVER_FIRST_NAME";
	private static final String COLUMN_DRIVER_LAST_NAME = "COLUMN_DRIVER_LAST_NAME";
	private static final String COLUMN_RIDER_EMAIL = "COLUMN_DRIVER_EMAIL";
	private static final String COLUMN_DRIVER_PASSWORD = "COLUMN_DRIVER_PASSWORD";
	private static final String COLUMN_RIDER_PHONE_NUMBER = "COLUMN_DRIVER_PHONE_NUMBER";

	// TERM Table - column names
	private static final String COLUMN_TERM_ID = "COLUMN_TERM_ID";
	private static final String COLUMN_TERM_CONTENT = "COLUMN_TERM_CONTENT";

	// TRIP STATUS Table - column names
	private static final String COLUMN_TRIP_STATUS_ID = "COLUMN_TRIP_STATUS_ID";
	private static final String COLUMN_TRIP_STATUS_STATUS = "COLUMN_TRIP_STATUS_STATUS";

	// DRIVER table create statement
	private static final String CREATE_TABLE_DRIVER = "CREATE TABLE "
			+ TABLE_RIDER + "(" + COLUMN_RIDER_ID + " TEXT,"
			+ COLUMN_RIDER_IMAGES + " TEXT," + COLUMN_RIDER_FIRST_NAME
			+ " TEXT," + COLUMN_DRIVER_LAST_NAME + " TEXT,"
			+ COLUMN_RIDER_EMAIL + " TEXT," + COLUMN_DRIVER_PASSWORD + " TEXT,"
			+ COLUMN_RIDER_PHONE_NUMBER + " TEXT" + ")";

	// TERM table create statement
	private static final String CREATE_TABLE_TERM = "CREATE TABLE "
			+ TABLE_TERM + "(" + COLUMN_TERM_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TERM_CONTENT
			+ " TEXT" + ")";

	private static final String CREATE_TABLE_TRIP_STATUS = "CREATE TABLE "
			+ TABLE_TRIP_STATUS + "(" + COLUMN_TRIP_STATUS_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TRIP_STATUS_STATUS
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
		db.execSQL(CREATE_TABLE_TRIP_STATUS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RIDER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TERM);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP_STATUS);
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

	public long createTrip(String id, String content) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_TRIP_STATUS_ID, id);
		values.put(COLUMN_TRIP_STATUS_STATUS, content);

		// Inserting Row
		long _id = db.insert(TABLE_TRIP_STATUS, null, values);
		if (db != null && db.isOpen())
			db.close();
		return _id;
	}

	public void deleteTrip(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from " + TABLE_TRIP_STATUS
				+ "where TRIP_STATUS_ID = " + id);

		if (db != null && db.isOpen())
			db.close();
	}

	public int updateTrip(String id, String content) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_TRIP_STATUS_ID, id);
		values.put(COLUMN_TRIP_STATUS_STATUS, content);
		db.execSQL("delete from " + TABLE_TRIP_STATUS);

		// updating row
		int temp = db.update(TABLE_TRIP_STATUS, values, COLUMN_TRIP_STATUS_ID
				+ " = ?", new String[] { String.valueOf(id) });
		if (db != null && db.isOpen())
			db.close();
		return temp;
	}

	public Trip getTripStatus() {
		SQLiteDatabase db = this.getWritableDatabase();
		String selectQuery = "SELECT  * FROM " + TABLE_TRIP_STATUS;
		Trip trip = new Trip();
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			trip.setId(c.getString(c.getColumnIndex(COLUMN_TRIP_STATUS_ID)));
			trip.setStatus(c.getString(c.getColumnIndex(COLUMN_TRIP_STATUS_STATUS)));
		}
		return trip;
	}

	public long createRider(Rider rider) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_RIDER_ID, rider.getId());
		values.put(COLUMN_RIDER_FIRST_NAME, rider.getFullname());
		values.put(COLUMN_RIDER_IMAGES, rider.getImage());
		values.put(COLUMN_RIDER_EMAIL, rider.getEmail());
		values.put(COLUMN_RIDER_PHONE_NUMBER, rider.getPhone());

		// Inserting Row
		long _id = db.insert(TABLE_RIDER, null, values);
		if (db != null && db.isOpen())
			db.close();
		return _id;
	}

	/*
	 * find a driver
	 */
	public Rider findRider() {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_RIDER;

		Cursor c = db.rawQuery(selectQuery, null);
		Rider rider = null;
		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			rider = new Rider();
			System.out.println(c.getString(c.getColumnIndex(COLUMN_RIDER_ID)));
			rider.setId(c.getString(c.getColumnIndex(COLUMN_RIDER_ID)));
			rider.setFullname((c.getString(c
					.getColumnIndex(COLUMN_RIDER_FIRST_NAME))));
			rider.setImage(c.getString(c.getColumnIndex(COLUMN_RIDER_IMAGES)));
			rider.setEmail(c.getString(c.getColumnIndex(COLUMN_RIDER_EMAIL)));
			rider.setPhone(c.getString(c
					.getColumnIndex(COLUMN_RIDER_PHONE_NUMBER)));

		}

		if (db != null && db.isOpen())
			db.close();

		return rider;
	}

	/*
	 * Updating a driver
	 */
	public int updateRider(Rider rider) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_RIDER_ID, rider.getId());
		values.put(COLUMN_RIDER_FIRST_NAME, rider.getFullname());
		values.put(COLUMN_RIDER_IMAGES, rider.getImage());
		values.put(COLUMN_RIDER_EMAIL, rider.getEmail());
		values.put(COLUMN_RIDER_PHONE_NUMBER, rider.getPhone());
		db.execSQL("delete from " + TABLE_RIDER);

		// updating row
		int temp = db.update(TABLE_RIDER, values, COLUMN_RIDER_ID + " = ?",
				new String[] { String.valueOf(rider.getId()) });
		if (db != null && db.isOpen())
			db.close();
		return temp;
	}

	/*
	 * Deleting a driver
	 */
	public int deleteRiderById(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		int temp = db.delete(TABLE_RIDER, COLUMN_RIDER_ID + " = ?",
				new String[] { id });

		if (db != null && db.isOpen())
			db.close();

		return temp;
	}

	public ArrayList<Rider> getListRider() {
		ArrayList<Rider> listRiders = new ArrayList<Rider>();
		String selectQuery = "SELECT  * FROM " + TABLE_RIDER;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Rider rider = new Rider();
				rider.setId(c.getString(c.getColumnIndex(COLUMN_RIDER_ID)));
				rider.setFullname((c.getString(c
						.getColumnIndex(COLUMN_RIDER_FIRST_NAME))));
				rider.setImage(c.getString(c
						.getColumnIndex(COLUMN_RIDER_IMAGES)));
				rider.setEmail(c.getString(c.getColumnIndex(COLUMN_RIDER_EMAIL)));
				rider.setPhone(c.getString(c
						.getColumnIndex(COLUMN_RIDER_PHONE_NUMBER)));
				listRiders.add(rider);
			} while (c.moveToNext());
		}

		if (db != null && db.isOpen())
			db.close();

		return listRiders;
	}

}
