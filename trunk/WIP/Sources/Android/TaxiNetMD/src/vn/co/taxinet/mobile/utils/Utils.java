package vn.co.taxinet.mobile.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

	private static Pattern pattern;
	private static Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PHONE_PATTERN = "^[0-9]{10,11}$";

	/**
	 * @author Hieu-Gie
	 * 
	 *         Notifies UI to display a message.
	 *         <p>
	 *         This method is defined in the common helper because it's used
	 *         both by the UI and the background service.
	 * 
	 * @param context
	 *            application's context.
	 * @param message
	 *            message to be displayed.
	 */
	public static void displayMessage(Context context, String message) {
		Intent intent = new Intent(Constants.BroadcastAction.DISPLAY_MESSAGE);
		intent.putExtra(Constants.EXTRA_MESSAGE, message);
		context.sendBroadcast(intent);
	}

	/**
	 * @author Hieu-Gie
	 * 
	 *         Notifies UI to display a REQUEST .
	 *         <p>
	 *         This method is defined in the common helper because it's used
	 *         both by the UI and the background service.
	 * 
	 * @param context
	 * @param driverImage
	 * @param longitude
	 * @param latitude
	 * @param price
	 */
	public static void displayRequest(Context context, String driverImage,
			String driverName, String longitude, String latitude, String price) {
		Intent intent = new Intent(Constants.BroadcastAction.DISPLAY_REQUEST);
		intent.putExtra(Constants.DRIVER_IMAGE, driverImage);
		intent.putExtra(Constants.RIDER_NAME, driverName);
		intent.putExtra(Constants.LONGITUDE, longitude);
		intent.putExtra(Constants.LATITUDE, latitude);
		intent.putExtra(Constants.PRICE, price);
		context.sendBroadcast(intent);
	}

	/**
	 * @author Hieu-Gie
	 * 
	 *         Checking for all possible internet providers
	 * 
	 * @param _context
	 * @return
	 */
	public static boolean isConnectingToInternet(Context _context) {
		ConnectivityManager connectivity = (ConnectivityManager) _context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}

		}
		return false;
	}

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public static boolean validateEmail(final String hex) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}

	/**
	 * @author Hieu-Gie
	 * 
	 *         Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @returntrue valid hex, false invalid hex
	 */
	public static boolean validatePhoneNumber(final String hex) {
		pattern = Pattern.compile(PHONE_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
}
