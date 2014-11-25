/**
 * Copyright(C) 2014, Transport Information Network Company.
 * TaxiNet:
 *  Taxi Network System
 *
 * Record of change:
 * Date          Version   Modifier   Change    			Reason
 * 2014-12-01    1.0       Dev        Create structure		First creation
 */

package vn.co.taxinet.common.exception;

import vn.co.taxinet.common.log.LoggerHelper;



/**
 * Represent a functional exception in the application.
 * Automatically log an ERROR message to log file when created.
 */
public class FunctionalException extends TNSException {
    private static final LoggerHelper logger = new LoggerHelper(FunctionalException.class);

    private String msgKey = null;
    private Object[] values = null;

    public FunctionalException() {
        super();
        this.logException();
    }

    /**
     * @param description
     */
    public FunctionalException(String description) {
        super(description);
        this.logException();
    }

    /**
     * @param location
     * @param description
     */
    public FunctionalException(String location, String description) {
        super(location, description);
        this.logException();
    }

    /**
     * @param location
     * @param description
     * @param msgKey
     */
    public FunctionalException(String location, String description,
            String msgKey) {
        super(location, description);
        this.msgKey = msgKey;
        this.logException();
    }

    /**
     * @param location
     * @param description
     * @param msgKey
     * @param values
     */
    public FunctionalException(String location, String description,
            String msgKey, Object[] values) {
        this(location, description, msgKey);
        this.values = values;
        this.logException();
    }

    /**
     * @return String
     */
    public String getMsgKey() {
        return msgKey;
    }

    /**
     * @return Object[]
     */
    public Object[] getValues() {
        return values;
    }

    /**
     * Log an ERROR message whenever a new exception is created.
     */
    private void logException() {
        logger.logError(this.getLocationMessage());
    }
}