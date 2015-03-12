package vn.co.taxinet.common;

import java.util.Locale;

public interface Constants {
	// regext pattern
	public static final String EMAIL_PATTERN_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String ZIPCODE_PATTERN_REGEX = "^\\d{5}(-\\d{4})?$";
	public static final String VISACARD_PATTERN_REGEX = "^4[0-9]{12}(?:[0-9]{3})?$";
	public static final String CVV_PATTERN_REGEX = "^[0-9]{3,4}$";
	public static final String PHONENO_PATTERN_REGEX = "^[0-9]{10,11}$";
	public static final String NAME_PATTERN_REGEX = "^[a-zA-Z]{6,10}$";
	public static final String PASSWORD_PATTERN_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).{6,20})";
	public static final String USERNAME_PATTERN_REGEX = "^[a-z0-9_-]{6,10}$";
	// end regex

	public static final int PAGE_SIZE = 10;
	public static final String STR_DATE_FORMAT = "";
	public static final Locale DEFAULT_LOCALE = null;
	public static final String STR_DATE_TIME_FORMAT = "";
	public static final String COMMA_SYMBOL = ",";
	public static final double MAX_VAL_MONEY = 0.0;
	public static final String STR_MONEY_FORMAT = "";

	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";

	public static final int MAX_PASSWORD_LENGTH = 10;
	public static final int MIN_PASSWORD_LENGTH = 6;

	public static final String apiKey = "AIzaSyAUJa4mx6s4YzO00RYr2PUXbQgx_o7PPXc";

	public interface Language {
		public static final String LANG_EN_CODE = "en";
		public static final String LANG_VN_CODE = "vi";
	}

	public interface FacesMess {
		public static final String WARNING = "WARNING";
		public static final String INFO = "SUCCESSFUL";
		public static final String FATAL = "FATAL ERROR";
		public static final String ERROR = "ERROR";
	}

	// These are used for Reference Data
	public interface GroupCode {
		public static final String USER_ROLE = "GROUPUSER";
		public static final String USER_STATUS = "USERSTATUS";
		public static final String SERVICE_CHARGE = "SERVICECHARGE";
		public static final String DRIVER_STATUS = "DRIVERSTATUS";
		public static final String BUSINESS_TYPE = "BUSINESSTYPE";
	}

	// These are used for Reference Data
	public interface GroupUser {
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

	public interface UserStatus {
		public static final String ACTIVE = "AC";
		public static final String DEACTIVED = "DA";
	}

	public interface ServiceCharge {
		public static final String MONTHLY = "MO";
		public static final String TRIP_BASED = "TB";
	}

	public interface DriverStatus {
		public static final String NEW = "NE";
		public static final String APPROVED = "AP";
		public static final String AVAIABLE = "AC";
		public static final String BUSY = "BU";
		public static final String OUT_OF_SERVICE = "OS";
		public static final String NOT_AVAIABLE = "NA";
	}

	public interface BusinessType {
		public static final String TAXI_SERVICE = "TS";
		public static final String PRIVATE_TAXI = "PT";
		public static final String TRUCK_TAXI = "TT";
	}

	public interface TripStatus {
		public static final String NEW_TRIP = "NT";
		public static final String REJECTED = "RJ";
		public static final String PICKED = "PD";
		public static final String PICKING = "PI";
		public static final String CANCELLED = "CA";
		public static final String COMPLETED = "TC";
	}

	public interface PayType {
		public static final String DIRECT = "DI";
		public static final String BANK_ACCOUNT = "BA";
		public static final String CREDIT_CARD = "CR";
		public static final String PAYPAL = "PP";
	}

	/* error keys definition */
	public interface Errors {
		public static final String DUPLICATED_ERROR = "001";
		public static final String PARSE_DATE_ERROR = "";
		public static final String PARSE_NUMBER_ERROR = "";
		public static final String INVALID_PATH = "";
		public static final String WRITTING_FILE_ERROR = "";
		public static final String CANT_GET_REQUEST = "System can't receive user request";
		public static final String CANT_FIND_USER = "Can't find user";
		public static final String DUPLICATE_VEHICLE_PLATE = "Plate is existed";
	}

	public interface MailKeys {
		public static final String EMAIL_SMTP_DEBUG = "taxinet/background/mail/SMTPDebug";
		public static final String EMAIL_SMTP_AUTH = "taxinet/background/mail/SMTPAuthentication";
		public static final String EMAIL_SMTP_HOST = "taxinet/background/mail/SMTPHost";
		public static final String EMAIL_SMTP_USER = "taxinet/background/mail/SMTPUsername";
		public static final String EMAIL_SMTP_PASS = "taxinet/background/mail/SMTPPassword";
		public static final String EMAIL_SMTP_PORT = "taxinet/background/mail/SMTPPort";
		public static final String EMAIL_SEND_TIMEOUT = "taxinet/background/mail/SendTimeout";
		public static final String EMAIL_CONNECT_TIMEOUT = "taxinet/background/mail/ConnectionTimeout";
		public static final String EMAIL_RETRY_WAIT = "taxinet/background/mail/RetryWait";
	}

	public interface Message {
		public static final String SUCCESS = "SUCCESS";
		public static final String FAIL = "FAIL";
		public static final String ERROR = "ERROR";
		public static final String REQUEST_NOT_FOUND = "REQUEST_NOT_FOUND";
		public static final String NUMBER_FORMAT_EXCEPTION = "NUMBER_FORMAT_EXCEPTION";
		public static final String EMTPY_STATUS = "EMTPY_STATUS";
		public static final String NULL_PARAMS = "NULL_PARAMS";
		public static final String DATA_NOT_FOUND = "DATA_NOT_FOUND";
		public static final String PASSWORD_LENGTH = "PASSWORD_LENGTH";
		public static final String PASSWORD_NOT_SAME = "PASSWORD_NOT_SAME";
		public static final String PASSWORD_ERROR = "PASSWORD_ERROR";
		public static final String EMAIL_ERROR = "EMAIL ERROR";
		public static final String PHONE_ERROR = "PHONE NUMBER ERROR";
	}
}