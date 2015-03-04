package vn.co.taxinet.utils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import vn.co.taxinet.bean.UserProfile;
//import vn.co.taxinet.bean.UserProfileImpl;
//import vn.co.taxinet.bo.impl.AuthenticationBOImpl;
import vn.co.taxinet.common.Constants;
import vn.co.taxinet.common.exception.FunctionalException;

public final class Utility {

	private static final String THIS = "Utility";
	private static final Logger logger = LogManager.getLogger(Utility.class);
	private static ResourceBundle onlineResource;

	/**
	 * Keep reference to the only ServletContext instance of TaxiNet application
	 * (per JVM).
	 */
	private static ServletContext servletContext;

	/**
	 * Called from CAPActionServlet to cache CAP's ServletContext instance.
	 *
	 * @param sc
	 *            the ServletContext instance of CAP in this JVM
	 */
	public static void setServletContext(ServletContext sc) {
		servletContext = sc;
	}

	/**
	 * @return current date
	 */
	public static Date getCurrentDate() {
		Calendar rightNow = Calendar.getInstance();
		// Trim milisecond value:
		long timeInMilisecond = rightNow.getTimeInMillis();
		String timeStr = String.valueOf(timeInMilisecond);
		timeStr = timeStr.substring(0, timeStr.length() - 3).concat("000");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return format.parse(format.format(rightNow.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rightNow.getTime();
		}
	}

	/**
	 * @return
	 * @throws FunctionalException
	 */
	public static Date getCurrentDateWithDayOnly() throws FunctionalException {
		Calendar rightNow = Calendar.getInstance();
		String now = "" + rightNow.get(Calendar.DAY_OF_MONTH);
		now += "/" + (rightNow.get(Calendar.MONTH) + 1);
		now += "/" + rightNow.get(Calendar.YEAR);
		return Utility.stringToDate(now, Constants.STR_DATE_FORMAT);
	}

	/**
	 * @param day
	 * @return
	 */
	public static int getDayInWeek(Date day) {
		if (day == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Method getCurrentDate.
	 *
	 * @return Date
	 */
	public static java.sql.Date getCurrentSQLDate() {
		return new java.sql.Date(getCurrentDate().getTime());
	}

	/**
	 * Method getCurrentDateTime.
	 *
	 * @return Date
	 */
	public static Date getCurrentDateTime() {
		return new Date();
	}

	/**
	 * Get default locale.
	 *
	 * @return Locale
	 */
	public static Locale getDefaultLocale() {
		return Constants.DEFAULT_LOCALE;
	}

	/**
	 * Get User's Locale in session.
	 *
	 * @param request
	 * @return Locale
	 */
	// public static Locale getUserLocale(HttpServletRequest request) {
	// UserProfile profile = (UserProfile)
	// request.getSession().getAttribute(UserProfile.SESSION_PROFILE);
	// return (Locale) profile.getObject(UserProfile.LOCALE);
	// }

	/**
	 * @param stringDate
	 * @param format
	 * @return
	 * @throws FunctionalException
	 */
	public static Date stringToDate(String stringDate, String format)
			throws FunctionalException {
		final String LOCATION = THIS + "stringToDate(" + stringDate + ", "
				+ format + ")";
		if (stringDate == null || stringDate.trim().length() == 0) {
			return null;
		}
		try {
			SimpleDateFormat fm = new SimpleDateFormat(format);
			fm.setLenient(false);
			return fm.parse(stringDate.trim());
		} catch (ParseException e) {
			throw new FunctionalException(LOCATION, "Cannot parse string "
					+ stringDate + " to Date!",
					Constants.Errors.PARSE_DATE_ERROR,
					new Object[] { stringDate });
		}
	}

	/**
	 * @param date
	 * @param format
	 * @return
	 * @throws FunctionalException
	 */
	public static Date formatDate(Date date, String format)
			throws FunctionalException {
		final String LOCATION = THIS + "formatDate(" + date + ", " + format
				+ ")";
		if (date == null) {
			return null;
		}
		try {
			Format formatter = new SimpleDateFormat(format);
			String s = formatter.format(date);
			SimpleDateFormat fm = new SimpleDateFormat(format);
			fm.setLenient(false);
			return fm.parse(s.trim());
		} catch (ParseException e) {
			throw new FunctionalException(LOCATION, "Cannot parse string "
					+ date + "to Date!", Constants.Errors.PARSE_DATE_ERROR,
					new Object[] { date });
		}
	}

	/**
	 * Convert String date in a pattern to java.sql.Date
	 *
	 * @param stringDate
	 *            date value in String format
	 * @param pattern
	 *            Date pattern used to convert
	 * @return java.sql.Date
	 */
	public static java.sql.Date stringToSQLDate(String stringDate,
			String pattern) throws FunctionalException {
		final String LOCATION = THIS + "stringToSQLDate(" + stringDate + ", "
				+ pattern + ")";
		if (stringDate == null || stringDate.length() == 0) {
			return null;
		}
		try {
			SimpleDateFormat fm = new SimpleDateFormat(pattern);
			fm.setLenient(false);
			return new java.sql.Date(fm.parse(stringDate).getTime());
		} catch (ParseException e) {
			throw new FunctionalException(LOCATION, "Cannot parse string "
					+ stringDate + " to SQL Date!",
					Constants.Errors.PARSE_DATE_ERROR,
					new Object[] { stringDate });
		}
	}

	/**
	 * Convert a date/time string to SQL Timestamp object.
	 *
	 * @param dateString
	 * @param formatDate
	 * @return Timestamp
	 * @throws FunctionalException
	 */
	public static Timestamp stringToTimestamp(String dateString,
			String formatDate) throws FunctionalException {
		final String LOCATION = THIS + "stringToTimestamp(" + dateString + ", "
				+ formatDate + ")";
		if (dateString == null || dateString.length() == 0) {
			return null;
		}
		try {
			DateFormat fmt = new SimpleDateFormat(formatDate);
			return new Timestamp(fmt.parse(dateString).getTime());
		} catch (ParseException e) {
			throw new FunctionalException(LOCATION, "Cannot parse string "
					+ dateString + " to Timestamp!",
					Constants.Errors.PARSE_DATE_ERROR,
					new Object[] { dateString });
		}
	}

	/**
	 * Convert Date to String according to date pattern
	 *
	 * @param date
	 *            Date need to convert to String
	 * @return String of date converted based on the pattern
	 */
	public static String dateToString(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat fm = new SimpleDateFormat(
				Constants.STR_DATE_TIME_FORMAT);
		return fm.format(date);
	}

	/**
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat fm = new SimpleDateFormat(format);
		return fm.format(date);
	}

	/**
	 * Get online resource of TaxiNet application.
	 *
	 * @param basename
	 *            the name of application resource
	 */
	public static void setupOnlineResource(String basename,
			ServletContext servletContext) {
		if (onlineResource == null) {
			// onlineResource = XMLResourceBundle.getBundle(basename, new
			// Locale("", ""), servletContext);
		}
	}

	/**
	 * Get online resource of TaxiNet application.
	 *
	 * @return ResourceBundle
	 */
	public static ResourceBundle getOnlineResource() {
		return onlineResource;
	}

	/**
	 * Return a value by its key
	 *
	 * @param key
	 * @return String value of the key
	 */
	public static String getOnlineValue(String key) {
		return onlineResource.getString(key);
	}

	/**
	 * Check if a string is a valid date
	 *
	 * @param strDateValue
	 * @return true/false
	 */
	public static boolean isDate(String strDateValue) {
		if (strDateValue == null || strDateValue.trim().length() == 0
				|| strDateValue.trim().length() != 10) {
			return false;
		}
		try {
			SimpleDateFormat fm = new SimpleDateFormat(
					Constants.STR_DATE_FORMAT);
			fm.setLenient(false);
			Date date = fm.parse(strDateValue.trim());
			return true;
		} catch (Exception e) {
			logger.warn(e);
			return false;
		}
	}

	/**
	 * @param strDateValue
	 * @param strFormat
	 * @return true/false
	 */
	public static boolean isDate(String strDateValue, String strFormat) {
		if (strDateValue == null || strDateValue.trim().length() == 0
				|| strDateValue.trim().length() != 10) {
			return false;
		}
		try {
			SimpleDateFormat fm = new SimpleDateFormat(strFormat);
			fm.setLenient(false);
			Date date = fm.parse(strDateValue.toString());
			return true;
		} catch (Exception fe) {
			logger.warn(fe.toString());
			return false;
		}
	}

	/**
	 * @param money
	 * @return true/false
	 */
	public static boolean isMoney(String money) {
		final String LOCATION = THIS + "isMoney(" + money + ")";
		try {
			if (money == null || money.length() == 0) {
				return true;
			}
			if (money.indexOf(",,") > 0) {
				return false;
			}
			if ((money.indexOf(",") > 0 && money.indexOf(".") > 0)
					&& (money.indexOf(",") > money.indexOf("."))) {
				return false;
			}

			if (money.indexOf(",") > 0) {
				money = money.replaceAll(",", "");
			}
			double doubleMoney = Double.parseDouble(money);
			return doubleMoney >= 0 && doubleMoney <= Constants.MAX_VAL_MONEY;
		} catch (NumberFormatException nfe) {
			logger.error(LOCATION + "::ERROR::" + nfe);
			return false;
		}
	}

	/**
	 * @param strNumber
	 * @return
	 * @throws FunctionalException
	 */
	public static double stringToDouble(String strNumber)
			throws FunctionalException {
		final String LOCATION = THIS + "stringToDouble(" + strNumber + ")";
		if (strNumber == null || strNumber.length() == 0) {
			return 0.00;
		}
		try {
			DecimalFormat fomatter = new DecimalFormat();
			fomatter.applyPattern(Constants.STR_MONEY_FORMAT);
			return fomatter.parse(strNumber).doubleValue();
		} catch (ParseException pe) {
			throw new FunctionalException(LOCATION, "Cannot parse string "
					+ strNumber + "to double!",
					Constants.Errors.PARSE_NUMBER_ERROR,
					new Object[] { strNumber });
		}
	}

	/**
	 * @param strNumber
	 * @return
	 * @throws FunctionalException
	 */
	public static float stringToFloat(String strNumber, String applyPattern)
			throws FunctionalException {
		final String LOCATION = THIS + "stringToDouble(" + strNumber + ")";
		if (strNumber == null || strNumber.length() == 0) {
			return 0.00000f;
		}
		try {
			DecimalFormat fomatter = new DecimalFormat();
			fomatter.applyPattern(applyPattern);
			return fomatter.parse(strNumber).floatValue();
		} catch (ParseException pe) {
			throw new FunctionalException(LOCATION, "Cannot parse string "
					+ strNumber + "to float!",
					Constants.Errors.PARSE_NUMBER_ERROR,
					new Object[] { strNumber });
		}
	}

	/**
	 * @param money
	 * @return true/false
	 */
	public static boolean isMoney(double money) {
		try {
			if (money >= 0 && money <= Constants.MAX_VAL_MONEY /*
																 * || money ==
																 * Constants
																 * .BLANK_MONEY
																 */) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Get User's Profile in session.
	 *
	 * @param request
	 * @return UserProfile
	 */
	// public static UserProfile getUserProfile(HttpServletRequest request) {
	// UserProfile profile = (UserProfile)
	// request.getSession().getAttribute(UserProfile.SESSION_PROFILE);
	// return profile;
	// }

	/**
	 * Get User's Id in session.
	 *
	 * @param request
	 * @return String
	 */
	// public static long getUserId(HttpServletRequest request) {
	// UserProfile profile = (UserProfile)
	// request.getSession().getAttribute(UserProfile.SESSION_PROFILE);
	// return profile.getAttribute(UserProfile.USER_ID) == null ? 0 :
	// Long.parseLong(profile.getAttribute(UserProfile.USER_ID));
	// }

	/**
	 * Get User's name in session.
	 *
	 * @param request
	 * @return String
	 */
	// public static String getUserName(HttpServletRequest request) {
	// UserProfile profile = (UserProfile)
	// request.getSession().getAttribute(UserProfile.SESSION_PROFILE);
	// return profile.getAttribute(UserProfile.USER_NAME);
	// }

	/**
	 * Get User's full name in session.
	 *
	 * @param request
	 * @return String
	 */
	// public static String getFullUserName(HttpServletRequest request) {
	// UserProfile profile = (UserProfile)
	// request.getSession().getAttribute(UserProfile.SESSION_PROFILE);
	// return profile.getAttribute(UserProfile.FULL_NAME);
	// }

	/**
	 * Check whether input string is parsable to Double type
	 *
	 * @param strNumber
	 * @return boolean
	 */
	public static boolean isDouble(String strNumber) {
		boolean rs = false;
		try {
			Double.parseDouble(strNumber);
			rs = true;
		} catch (Exception e) {
			rs = false;
		}
		return rs;
	}

	/**
	 * Check whether input string is parsable to Long type
	 *
	 * @param strNumber
	 * @return boolean
	 */
	public static boolean isLong(String strNumber) {
		boolean rs = false;
		try {
			Long.parseLong(strNumber);
			rs = true;
		} catch (Exception e) {
			rs = false;
		}
		return rs;
	}

	/**
	 * @param strNumber
	 * @return
	 */
	public static boolean isPositiveInteger(String strNumber) {
		if (strNumber == null || strNumber.trim().length() == 0) {
			return true;
		}
		try {
			int intNumber = Integer.parseInt(strNumber);
			return intNumber >= 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Gets the user's ResourceBundle based on the locale passed in.
	 * <P>
	 * NOTE:
	 * <P>
	 * This method is for places where HTTP request object is not available,
	 * e.g. in BO classes.<BR>
	 * Ideally this direct use of XMLResourceBundle here is not a good
	 * encapsulation. But this method is required for getDate/TimeFormat()
	 * methods of DateUtils minimize the impact to many other classes.
	 * <p/>
	 *
	 * @param userLocale
	 * @return ResourceBundle
	 */
	public static ResourceBundle getResourceBundle(Locale userLocale) {
		return null;// XMLResourceBundle.getBundle(Constants.INTER_SUPPORT_CAP,
					// userLocale, servletContext);
	}

	/**
	 * Gets the user's ResourceBundle based on the request passed in.
	 *
	 * @param request
	 * @return ResourceBundle
	 */
	// public static ResourceBundle getResourceBundle(HttpServletRequest
	// request) {
	// LocalizationContext localeContext = getLocalizationContext(request);
	// if (localeContext != null) {
	// return localeContext.getResourceBundle();
	// }
	// return getResourceBundle(getUserLocale(request));
	// }

	/**
	 * Gets the user's LocalizationContext based on the request passed in.
	 *
	 * @param request
	 * @return LocalizationContext
	 */
	// public static LocalizationContext
	// getLocalizationContext(HttpServletRequest request) {
	// return (LocalizationContext)
	// request.getSession().getAttribute(Config.FMT_LOCALIZATION_CONTEXT);
	// }

	/**
	 * Get i18n message from user's ResourceBundle based on request passed in
	 *
	 * @param key
	 * @param request
	 * @return
	 */
	// public static String getI18nMessage(String key, HttpServletRequest
	// request) {
	// return getResourceBundle(request).getString(key);
	// }

	/**
	 * Get i18n message from user's ResourceBundle based on request passed in
	 *
	 * @param key
	 * @param request
	 * @param values
	 * @return
	 */
	// public static String getI18nMessage(String key, HttpServletRequest
	// request, Object[] values) {
	// String message = null;
	// try {
	// message = getResourceBundle(request).getString(key);
	// message = MessageFormat.format(message, values == null ? new Object[0] :
	// values);
	// } catch (Exception e) {
	// message = "???";
	// logger.warn(e);
	// }
	// return message;
	// }
	//
	/**
	 * Get i18n message from user's ResourceBundle based on user's Locale
	 *
	 * @param key
	 * @param userLocale
	 * @return String a i18n string from xml file
	 */
	public static String getI18nMessage(String key, Locale userLocale) {
		return getResourceBundle(userLocale).getString(key);
	}

	/**
	 * Get i18n message from user's ResourceBundle based on user's Locale
	 *
	 * @param key
	 * @param userLocale
	 * @param values
	 * @return
	 */
	public static String getI18nMessage(String key, Locale userLocale,
			Object[] values) {
		String message = null;
		try {
			message = getResourceBundle(userLocale).getString(key);
			message = MessageFormat.format(message,
					values == null ? new Object[0] : values);
		} catch (Exception e) {
			message = "???";
			logger.warn(e);
		}
		return message;
	}

	/**
	 * format a double with n digit after point
	 *
	 * @param number
	 *            - number to format
	 * @param digit
	 *            - number of digit after point
	 * @return number was formated
	 */
	public static double formatDouble(double number, int digit) {
		long pow = (long) Math.pow(10, digit);
		return (((double) Math.round(pow * number)) / pow);
	}

	/**
	 * format a float with n digit after point
	 *
	 * @param number
	 *            - number to format
	 * @param digit
	 *            - number of digit after point
	 * @return number was formated
	 */
	public static float formatFloat(float number, int digit) {
		long pow = (long) Math.pow(10, digit);
		return (((float) Math.round(pow * number)) / pow);
	}

	// public static String formatFloat(float number) throws SystemException {
	// final String LOCATION = THIS + "formatFloat(" + number + ")";
	// try {
	// NumberFormat formatter = new
	// DecimalFormat(Constants.STR_VT_DIVIDER_FORMAT);
	// String s = number == 0 ? "0.00000" : formatter.format(formatFloat(number,
	// 5));
	// return s;
	// } catch (Exception e) {
	// throw new SystemException(LOCATION, e.getMessage());
	// }
	// }

	/**
	 * @param number
	 * @return
	 * @throws SystemException
	 */
	// public static String formatCurrency(double number) throws SystemException
	// {
	// final String LOCATION = THIS + "formatCurrency(" + number + ")";
	// try {
	// NumberFormat formatter = new DecimalFormat(Constants.STR_MONEY_FORMAT);
	// String s = number == 0 ? "0.00" : formatter.format(formatDouble(number,
	// 2));
	// return s;
	// } catch (Exception e) {
	// throw new SystemException(LOCATION, e.getMessage());
	// }
	// }

	/**
	 * @param number
	 * @return
	 * @throws SystemException
	 */
	// public static String formatCurrency(double number, String format) throws
	// SystemException {
	// final String LOCATION = THIS + "formatCurrency(" + number + ")";
	// try {
	// NumberFormat formatter = new DecimalFormat(format);
	// String s = number == 0 ? "0.00" : formatter.format(formatDouble(number,
	// 2));
	// return s;
	// } catch (Exception e) {
	// throw new SystemException(LOCATION, e.getMessage());
	// }
	// }
	/**
	 * @param number
	 * @param digit
	 * @return
	 * @throws SystemException
	 */
	// public static String formatCurrency(double number, int digit) throws
	// SystemException {
	// final String LOCATION = THIS + "formatCurrency(" + number + ", " + digit
	// + ")";
	// try {
	// switch (digit) {
	// case 0: {
	// NumberFormat formatter = new
	// DecimalFormat(Constants.STR_MONEY_FORMAT_ZERO);
	// String s = number == 0 ? "0" : formatter.format(formatDouble(number, 0));
	// return s;
	// }
	// case 1: {
	// NumberFormat formatter = new
	// DecimalFormat(Constants.STR_MONEY_FORMAT_ONE);
	// String s = number == 0 ? "0.0" : formatter.format(formatDouble(number,
	// 1));
	// return s;
	// }
	// case 3: {
	// NumberFormat formatter = new
	// DecimalFormat(Constants.STR_MONEY_FORMAT_THRID);
	// String s = number == 0 ? "0.000" : formatter.format(formatDouble(number,
	// 3));
	// return s;
	// }
	// default : {
	// NumberFormat formatter = new DecimalFormat(Constants.STR_MONEY_FORMAT);
	// String s = number == 0 ? "0.00" : formatter.format(formatDouble(number,
	// 2));
	// return s;
	// }
	// }
	// } catch (Exception e) {
	// throw new SystemException(LOCATION, e.getMessage());
	// }
	// }

	/**
	 * Format number
	 *
	 * @param number
	 * @param digit
	 * @return
	 */
	// public static String formatNumber(double number, int digit) {
	// try {
	// String strResult = "";
	// if (digit <= 0) {
	// NumberFormat formatter = new DecimalFormat(Constants.STR_DECIMAL_FORMAT);
	// strResult = number == 0 ? "0" : formatter.format(formatDouble(number,
	// 0));
	// } else {
	// StringBuffer suffix = new StringBuffer(".");
	// for (int i = 0; i < digit; i++) {
	// suffix.append("0");
	// }
	// NumberFormat formatter = new DecimalFormat(Constants.STR_DECIMAL_FORMAT +
	// suffix.toString());
	// strResult = number == 0 ? ("0" + suffix.toString()) :
	// formatter.format(formatDouble(number, digit));
	// }
	// return strResult;
	// } catch (Exception e) {
	// return "";
	// }
	// }

	/**
	 * check email address.
	 *
	 * @param email
	 * @return true/false
	 */
	public static boolean isEmail(String email) {
		// Check that the email address does not contain spaces
		int space = email.indexOf(' ');
		if (space != -1) {
			return false;
		}
		// check that there is an @, and that there's at least one character
		// before the @
		int atpos = email.indexOf('@');
		if (atpos < 1) {
			return false;
		}
		atpos++;

		// check there's not second at
		int anotheratpos = email.indexOf('@', atpos);
		if (anotheratpos != -1) {
			return false;
		}
		// check there's at least one dot after the at
		int dotAfterAt = email.indexOf('.', atpos);
		if (dotAfterAt == -1) {
			return false;
		}
		if (dotAfterAt == email.length() - 2) {
			return false;
		}
		return true;
	}

	/**
	 * @param object
	 */
	public static void printObjectAttributes(Object object) {
		logger.debug("Object Name: " + object.getClass().getName());
		logger.debug("Fields:");
		Field fields[] = object.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				Field field = fields[(i)];
				field.setAccessible(true);
				logger.debug("    " + field.getName() + " = "
						+ field.get(object));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void printHashMapKeys(Map map) {
		logger.debug("Object Map: " + map.getClass().getName());
		logger.debug("Keys:");
		if (map == null) {
			logger.debug("Map is null");
			return;
		}
		Set sMap = map.entrySet();
		for (Iterator i = sMap.iterator(); i.hasNext();) {
			Map.Entry entryMap = (Map.Entry) i.next();
			Utility.printObjectAttributes((Object) entryMap.getValue());
		}
	}

	/**
	 * @param object
	 */
	public static void printArraysObjectAttributes(Collection col) {
		logger.debug("Collection Name: " + col.getClass().getName());
		if (col == null || col.size() == 0) {
			logger.debug("Collection is null or emty collection");
			return;
		}
		for (Iterator i = col.iterator(); i.hasNext();) {
			Utility.printObjectAttributes((Object) i.next());
		}
	}

	/**
	 * @param object
	 */
	public static void printObjectAttributesToScreen(Object object) {
		if (object == null) {
			System.out.println("Object Null");
			return;
		}
		System.out.println("Object Name: " + object.getClass().getName());
		System.out.println("Fields:");
		Field fields[] = object.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				Field field = fields[(i)];
				field.setAccessible(true);
				System.err.println("    " + field.getName() + " = "
						+ field.get(object));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("test");
	}

	/**
	 * Write a buffer of String to a file.
	 *
	 * @param filePath
	 * @param buffer
	 * @throws FunctionalException
	 */
	public static void writeToFile(String filePath, StringBuffer buffer)
			throws FunctionalException {
		final String LOCATION = "writeToFile (" + filePath + ", " + buffer
				+ ")";
		logger.debug(LOCATION + "::BEGIN");
		try {
			FileWriter fileWriter = new FileWriter(filePath);

			fileWriter.write(buffer.toString());
			fileWriter.close();
		} catch (FileNotFoundException fEx) {
			logger.error(fEx.getMessage());
			throw new FunctionalException(LOCATION,
					Constants.Errors.INVALID_PATH);
		} catch (IOException ioEx) {
			logger.error(ioEx.getMessage());
			throw new FunctionalException(LOCATION,
					Constants.Errors.WRITTING_FILE_ERROR);
		}
		logger.debug(LOCATION + "::END");
	}

	/**
	 * Checking max length of a string
	 *
	 * @param strData
	 * @param lengthMax
	 * @return
	 */
	public static boolean checkLength(String strData, int lengthMax) {
		if (strData.trim().length() > lengthMax) {
			return false;
		}
		return true;
	}

	/**
	 * Get the DateTime of the input year,week, and day of the week.
	 *
	 * @param year
	 * @param week
	 *            the week number of the year.
	 * @param day
	 *            the number of days (offset day) calculated from Monday
	 * @return
	 */
	public static Date getDateOfWeek(int year, int week, int day) {
		GregorianCalendar gc = new GregorianCalendar(year, 0, 1);
		int firstDayOfYear = gc.get(Calendar.DAY_OF_WEEK);

		// The first day is not Monday
		if (firstDayOfYear > Calendar.MONDAY) {
			gc.add(Calendar.DATE, 7 - firstDayOfYear + 2);
		} else if (firstDayOfYear < Calendar.MONDAY) {
			gc.add(Calendar.DATE, 1);
		}
		gc.add(Calendar.DATE, (week - 1) * 7);
		if (day > 2) {
			gc.add(Calendar.DATE, day - 2);
		} else if (day == 1) {
			gc.add(Calendar.DATE, -1);
		}
		return gc.getTime();
	}

	/**
	 * add a number of day to a Date
	 *
	 * @param dateToAdd
	 * @param numberOfDayToAdd
	 * @param format
	 * @return Date
	 */
	public static Date addDayToDate(Date dateToAdd, int numberOfDayToAdd) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(dateToAdd);
		c1.add(Calendar.DATE, numberOfDayToAdd);
		long i = c1.getTime().getTime();
		return new Date(i);
	}

	/**
	 * @param money
	 * @param degit
	 *            after "." symbol
	 * @return true if money's format is correct, false if money's format is
	 *         wrong
	 * @author kientnt1 check format of money
	 */
	public static boolean checkMoney(String money, int degit) {
		try {
			if (money == null || money.length() == 0) {
				return true;
			}

			if (money.indexOf(",,") > 0) {
				return false;
			}

			if ((money.indexOf(",") > 0 && money.indexOf(".") > 0)
					&& (money.indexOf(",") > money.indexOf("."))) {
				return false;
			}

			if (money.indexOf(",") > 0) {
				StringTokenizer strToken = new StringTokenizer(money, ",");
				int count = strToken.countTokens();
				int k = 1;
				String str = null;
				String subStr1 = null;
				String subStr2 = null;
				int dotIndex = 0;
				for (int i = 0; i < count; i++) {
					str = strToken.nextToken();
					if (k < count) {
						if (str.length() > 3 || !Utility.isDouble(str)) {
							return false;
						}
					} else if (k == count) {
						dotIndex = str.indexOf(".");
						if (dotIndex > 0) {
							subStr1 = str.substring(0, dotIndex);
							if (subStr1.length() > 3
									|| !Utility.isDouble(subStr1)) {
								return false;
							}
							subStr2 = str.substring(dotIndex + 1);
							if (subStr2.length() > degit
									|| !Utility.isDouble(subStr2)) {
								return false;
							}
						} else {
							if (str != null
									&& (str.length() > 3 || str.length() < 3)) {
								return false;
							}
						}
					}
					k++;
				}
				money = money.replaceAll(",", "");
			} else {
				if (money.indexOf(".") > 0) {
					String subStr = money.substring(money.indexOf(".") + 1);
					if (subStr != null && subStr.length() > degit) {
						return false;
					}
				}
			}
			double doubleMoney = Double.parseDouble(money);
			return doubleMoney >= 0;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * @param strMoney
	 * @return
	 */
	public static String truncate(String strMoney) {
		int degit = 2;
		if (strMoney != null && strMoney.length() > 0) {
			int dotIndex = strMoney.indexOf(".");
			if (dotIndex > 0) {
				String subStr = strMoney.substring(dotIndex + 1);
				if (subStr.length() > degit) {
					return strMoney.substring(0, dotIndex + degit + 1);
				} else if (subStr.length() > 0 && subStr.length() < degit) {
					return strMoney + "0";
				} else {
					return strMoney;
				}
			}
			return strMoney;
		}
		return null;
	}

	/**
	 * @param beforeDate
	 * @param afterDate
	 * @return
	 * @throws FunctionalException
	 */
	public static long daysBetween(Date beforeDate, Date afterDate)
			throws FunctionalException {
		beforeDate = removeTime(beforeDate);
		afterDate = removeTime(afterDate);

		long diffMillis = (beforeDate.getTime() - afterDate.getTime())
				/ (24 * 60 * 60 * 1000);
		return diffMillis;
	}

	public static long daysBetween1(Date fromDate, Date toDate)
			throws FunctionalException {
		fromDate = removeTime(fromDate);
		toDate = removeTime(toDate);

		long diffMillis = (toDate.getTime() - fromDate.getTime())
				/ (24 * 60 * 60 * 1000);
		return diffMillis;
	}

	public static double daysBetweenDouble(Date beforeDate, Date afterDate)
			throws FunctionalException {
		beforeDate = removeTime(beforeDate);
		afterDate = removeTime(afterDate);

		double diffMillis = (beforeDate.getTime() - afterDate.getTime())
				/ (24 * 60 * 60 * 1000);
		return diffMillis;
	}

	/**
	 * @param dateParam
	 * @return
	 * @throws FunctionalException
	 */
	public static Date removeTime(Date dateParam) throws FunctionalException {
		String dateStr = dateToString(dateParam);
		Date temp = stringToDate(dateStr, Constants.STR_DATE_FORMAT);
		return temp;
	}

	/**
	 * @param date
	 * @return
	 */
	public static Date newStartDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE), 00, 00, 01);
		return cal.getTime();
	}

	/**
	 * @param date
	 * @return
	 */
	public static Date newEndDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE), 23, 59, 59);
		return cal.getTime();
	}

	/**
	 * Use for CodaExport only
	 * 
	 * @param value
	 * @return
	 */
	public static int stringToInt(String value) {
		int result = 0;
		try {
			result = Integer.parseInt(value);
			return result;
		} catch (Exception e) {
			return -1;
		}

	}

	public static int stringToIntAccCode(String value) {
		int result = 0;
		try {
			result = Integer.parseInt(value);
			return result;
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * @param object
	 * @return
	 * @author minhlh
	 */
	public static StringBuffer buildCsvRowFromObject(Object object) {
		Field fields[] = object.getClass().getDeclaredFields();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < fields.length; i++) {
			try {
				Field field = fields[(i)];
				field.setAccessible(true);
				if (field.get(object) != null) {
					result.append(field.get(object));
				}
				result.append(Constants.COMMA_SYMBOL);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String getContextPathOfWeb(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
	}

	// public static UserProfileImpl getUserProfileImpl(HttpServletRequest
	// request) {
	// UserProfileImpl profile = (UserProfileImpl)
	// request.getSession().getAttribute(UserProfile.SESSION_PROFILE);
	// return profile;
	// }

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	public static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	public static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

}