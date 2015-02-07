package vn.co.taxinet.mobile.utils;

import android.content.Context;
import android.content.Intent;

public class Const {

	public static final String ACCEPT = "OK";
	public static final String DENI = "DENI";

	public static final String URL_GET_TERM = "http://api.androidhive.info/volley/person_object.json";
	public static final String URL_JSON_ARRAY = "http://api.androidhive.info/volley/person_array.json";
	public static final String URL_STRING_REQ = "http://api.androidhive.info/volley/string_response.html";
	public static final String URL_IMAGE = "http://api.androidhive.info/volley/volley-image.jpg";
	public static final String URL_IMAGE2 = "http://api.androidhive.info/volley/volley-image.jpg";
	public static final String URL_LOGIN_AUTHEN = "";
	public static final String SUCCESS = "SUCCESS";
	public static final String EMPTY_ERROR = "EMPTY_ERROR";
	public static final String EMAIL_ERROR = "EMAIL_ERROR";
	public static final String PHONE_NUMBER_ERROR = "PHONE_NUMBER_ERROR";
	public static final String FIRST_NAME_ERROR = "FIRST_NAME_ERROR";
	public static final String LAST_NAME_ERROR = "LAST_NAME_ERROR";
	public static final String PASSWORD_ERROR = "PASSWORD_ERROR";
	public static final String ACCOUNT_ERROR = "ACCOUNT_ERROR";
	public static final String URL_GET_REQUEST = "http://echo.jsontest.com/insert-key-here/insert-value-here/key/value";
	public static final String URL_REGISTER_DRIVER = "http://api.androidhive.info/volley/volley-image.jpg";

	// Google project id
	public static final String SENDER_ID = "943411953393";
	/**
	 * Tag used on log messages.
	 */
	public static final String TAG = "Taxi Net";

	public static final String DISPLAY_MESSAGE_ACTION = "vn.co.taxinet.mobile.DISPLAY_MESSAGE";

	public static final String EXTRA_MESSAGE = "message";
	public static final String SERVER_URL = "http://10.0.2.2/gcm_server_php/register.php";

	/**
	 * Notifies UI to display a message.
	 * <p>
	 * This method is defined in the common helper because it's used both by the
	 * UI and the background service.
	 * 
	 * @param context
	 *            application's context.
	 * @param message
	 *            message to be displayed.
	 */
	public static void displayMessage(Context context, String message) {
		Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
		intent.putExtra(EXTRA_MESSAGE, message);
		context.sendBroadcast(intent);
	}

}
