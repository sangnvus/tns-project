package vn.co.taxinet.mobile.utils;

import java.net.URI;

import android.content.Context;
import android.content.Intent;

/**
 * @author Hieu-Gie
 * 
 */
public interface Constants {

	// Google project id
	public static final String SENDER_ID = "943411953393";

	/**
	 * Tag used on log messages.
	 */
	public static final String TAG = "Taxi_Net";

	/*
	 * define Broadcast action name
	 */
	public interface BroadcastAction {
		public static final String DISPLAY_MESSAGE = "vn.co.taxinet.mobile.DISPLAY_MESSAGE";
		public static final String DISPLAY_REQUEST = "vn.co.taxinet.mobile.DISPLAY_REQUEST";
	}

	/*
	 * define url
	 */
	public interface URL {
		public static final String GET_TERM = "http://192.168.0.114:8080/TN/restServices/TermController/GetTerm";
		public static final String LOGIN_AUTHEN = "http://192.168.0.114:8080/TN/restServices/riderController/Login";
		public static final String UPDATE_DRIVER = "http://192.168.0.114:8080/TN/restServices/DriverController/UpdateDriver";
		public static final String UPDATE_REG_ID = "http://192.168.0.114:8080/TN/restServices/CommonController/UpdateRegId";
		public static final String REGISTER_DRIVER = "http://192.168.0.114:8080/TN/restServices/CommonController/register";
		public static final String UPDATE_CURRENT_STATUS = "http://192.168.0.114:8080/TN/restServices/DriverController/UpdateCurrentStatus";
		public static final String UPDATE_TRIP = "http://192.168.0.114:8080/TN/restServices/TripController/UpdateTrip";
		public static final String CREATE_TRIP = "http://192.168.0.114:8080/TN/restServices/TripController/CreateTrip";	
		public static final String GET_DRIVER = "http://192.168.0.114:8080/TN/restServices/DriverController/getNearDriver";
	}

	/*
	 * define String const
	 */
	public static final String SUCCESS = "SUCCESS";
	public static final String ACCEPT = "OK";
	public static final String DENI = "DENI";
	public static final String EMPTY_ERROR = "EMPTY_ERROR";
	public static final String EMAIL_ERROR = "EMAIL_ERROR";
	public static final String PHONE_NUMBER_ERROR = "PHONE_NUMBER_ERROR";
	public static final String FIRST_NAME_ERROR = "FIRST_NAME_ERROR";
	public static final String LAST_NAME_ERROR = "LAST_NAME_ERROR";
	public static final String PASSWORD_ERROR = "PASSWORD_ERROR";
	public static final String ACCOUNT_ERROR = "ACCOUNT_ERROR";
	public static final String EXTRA_MESSAGE = "MESSGE";
	public static final String DRIVER_IMAGE = "DRIVER_IMAGE";
	public static final String DRIVER_NAME = "DRIVER_NAME";
	public static final String LONGITUDE = "longitude";
	public static final String LATITUDE = "latitude";
	public static final String PRICE = "PRICE";
	public static final String IMAGE = "image";
	public static final String NAME = "name";
	public static final String PHONE = "phone";
	public static final String ID = "id";
	public static final String RESPONSE_REQUEST = "RESPONSE_REQUEST";
	public static final String UPDATE_CURRENT_STATUS = "UPDATE_CURRENT_STATUS";

	public interface DriverStatus {
		public static final String NEW = "NE";
		public static final String APPROVED = "AP";
		public static final String AVAIABLE = "AC";
		public static final String BUSY = "BU";
		public static final String OUT_OF_SERVICE = "OS";
		public static final String NOT_AVAIABLE = "NA";
	}

	public interface TripStatus {
		public static final String NEW_TRIP = "NT";
		public static final String REJECTED = "RJ";
		public static final String PICKED = "PD";
		public static final String PICKING = "PI";
		public static final String CANCELLED = "CA";
		public static final String COMPLETED = "TC";
	}

	// These are used for Reference Data
	public interface UserGroup {
		public static final String RIDER = "RD";
		public static final String DRIVER = "DR";
		public static final String AGENT = "AG";
		public static final String AREA_STAFF = "AS";
		public static final String COUNTRY_STAFF = "CS";
		public static final String AREA_MANAGER = "AM";
		public static final String COUNTRY_MANAGER = "CM";
		public static final String COUNTRY_ADMINISTRATOR = "CA";
		public static final String GLOBAL_MANAGER = "GM";
		public static final String GLOBAL_ADMINISTRATOR = "GA";
	}
}
