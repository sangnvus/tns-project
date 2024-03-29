package vn.co.taxinet.mobile.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PHONE_PATTERN = "*0(\\d{9})\\d{1}?";

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */

	public Validator() {

	}

	public boolean validateEmail(final String hex) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}

	public boolean validatePhoneNumber(final String hex) {
		pattern = Pattern.compile(PHONE_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
}
