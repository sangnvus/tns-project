
package vn.co.taxinet.common;

import java.util.Locale;

public interface Constants {
	public static final String STR_DATE_FORMAT = "";
	public static final Locale DEFAULT_LOCALE =null;
	public static final String STR_DATE_TIME_FORMAT = "";
	public static final String COMMA_SYMBOL =",";
	public static final double MAX_VAL_MONEY = 0.0;
	public static final String STR_MONEY_FORMAT= "";
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