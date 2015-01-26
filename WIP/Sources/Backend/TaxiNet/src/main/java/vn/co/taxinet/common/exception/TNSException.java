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

/**
 * Base exception of all TNS-specific exception classes to provide some common
 * attributes such as the location and system exception (if exists).
 */
public abstract class TNSException extends Exception {
	private static final long serialVersionUID = 4907332684307180702L;
	private String location = null;
    private Throwable throwable = null;

    public TNSException() {
        super();
    }

    /**
     * @param location
     * @param throwable
     */
    public TNSException(String location, Throwable throwable) {
        super(throwable.getMessage());
        this.location = location;
        this.throwable = throwable;
    }

    /**
     * @param location
     * @param description
     */
    public TNSException(String location, String description) {
        super(description);
        this.location = location;
    }

    /**
     * @param description
     */
    public TNSException(String description) {
        super(description);
    }

    /**
     * @return String
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return java.lang.Throwable
     */
    public Throwable getThrowable() {
        return throwable;
    }

    /**
     * @return String
     */
    public String getLocationMessage() {
        return location + ": " + getMessage();
    }
}