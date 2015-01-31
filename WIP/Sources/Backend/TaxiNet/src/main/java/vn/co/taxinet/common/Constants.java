
package vn.co.taxinet.common;

import java.util.Locale;

public interface Constants {	
	public static final String STR_DATE_FORMAT = "";
	public static final Locale DEFAULT_LOCALE =null;
	public static final String STR_DATE_TIME_FORMAT = "";
	public static final String COMMA_SYMBOL =",";
	public static final double MAX_VAL_MONEY = 0.0;
	public static final String STR_MONEY_FORMAT= "";
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
		public static final String RIDER = "RD";
		public static final String DRIVER = "DR";
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