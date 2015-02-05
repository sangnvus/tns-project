
package vn.co.taxinet.common;

import java.util.Locale;

public interface Constants {	
	//regext pattern
	public static final String EMAIL_PATTERN_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String ZIPCODE_PATTERN_REGEX = "^\\d{5}(-\\d{4})?$";
	public static final String VISACARD_PATTERN_REGEX = "^4[0-9]{12}(?:[0-9]{3})?$";
	public static final String CVV_PATTERN_REGEX ="^[0-9]{3,4}$";
	public static final String PHONENO_PATTERN_REGEX ="^[0-9]{6,7}$";
	//end regex
	
	
	
	public static final String STR_DATE_FORMAT = "";
	public static final Locale DEFAULT_LOCALE =null;
	public static final String STR_DATE_TIME_FORMAT = "";
	public static final String COMMA_SYMBOL =",";
	public static final double MAX_VAL_MONEY = 0.0;
	public static final String STR_MONEY_FORMAT= "";
	
	
	public interface Language {
		public static final String LANG_EN_CODE = "en";
		public static final String LANG_VN_CODE = "vn";
	}
	
	public interface UserStatus {
		public static final String ONLINE = "online";
		public static final String OFFLINE = "offline";
	}
	
	
	//These are used for Reference Data
	public interface GroupCode {
		public static final String USER_ROLE = "GROUPUSER";
		public static final String USER_STATUS = "USERSTATUS";
		public static final String SERVICE_CHARGE = "SERVICECHARGE";
		public static final String DRIVER_STATUS = "DRIVERSTATUS";
		public static final String BUSINESS_TYPE ="BUSINESSTYPE";
	}
	//These are used for Reference Data
	public interface GroupUser{
		public static final String RIDER = "1";
		public static final String DRIVER = "2";
		public static final String TAXIGROUP ="3";
		public static final String FEEAGENT = "4";
		public static final String ADMIN = "5";
	}
	public interface BusinessType {
		public static final String TAXI_COM = "TS";
		public static final String PRIVATE_TAXI = "PT";
	}
	public interface ServiceCharge {
		public static final String MONTHLY = "MO";
		public static final String TRIP_BASED = "TB";
	}
    /* error keys definition */
    public interface Errors {
    	public static final String DUPLICATED_ERROR = "001";
    	public static final String PARSE_DATE_ERROR = ""; 
    	public static final String PARSE_NUMBER_ERROR = "";
    	public static final String INVALID_PATH = "";
    	public static final String WRITTING_FILE_ERROR = "";    	
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
}