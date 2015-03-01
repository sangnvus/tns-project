/**
 * Copyright(C) 2014, Trading and Service Information Network Company.
 * TaxiNet:
 *  Taxi Network System
 *
 * Record of change:
 * Date          Version   Modifier   Change    			Reason
 * 2014-12-01    1.0       Dev        Create structure		First creation
 */

package vn.co.taxinet.common.log;

import vn.co.taxinet.common.exception.TNSException;

/**
 * Simple wrapper around Logger to provide logging TNSException feature for
 * other classes.
 */

public class LoggerHelper {

	/**
	 * The Logger instance
	 */
	private Logger logger = null;

	private LoggerHelper() {
	}

	/**
	 * Constructor for LogHelper Get an instance of Logger for specified class.
	 * 
	 * @param clazz
	 *            the class that requires an instance of Logger
	 */
	public LoggerHelper(Class clazz) {
		this.logger = Logger.getLogger(clazz);
	}

	/**
	 * Log a message with stack trace of Exception at ERROR level.
	 * 
	 * @param exception
	 *            a Exception to log stack trace
	 */
	public void logError(Throwable exception) {
		if (exception instanceof TNSException) {
			TNSException ex = (TNSException) exception;
			if (ex.getThrowable() != null) {
				logger.error(ex.getLocation(), ex.getThrowable());
			} else {
				logger.error(ex.getLocationMessage());
			}
		} else {
			logger.error(exception);
		}
	}

	/**
	 * Log a message at ERROR level.
	 * 
	 * @param description
	 *            message object to be logged
	 */
	public void logError(String description) {
		logger.error(description);
	}

	/**
	 * Log a message with stack trace of Exception at DEBUG level.
	 * 
	 * @param exception
	 *            a Exception to log stack trace
	 */
	public void logDebug(Throwable exception) {
		if (exception instanceof TNSException) {
			TNSException ex = (TNSException) exception;
			if (ex.getThrowable() != null) {
				logger.debug(ex.getLocation(), ex.getThrowable());
			} else {
				logger.debug(ex.getLocationMessage());
			}
		} else {
			logger.debug(exception);
		}
	}

	/**
	 * Log a message at DEBUG level.
	 * 
	 * @param description
	 *            message object to be logged
	 */
	public void logDebug(String description) {
		logger.debug(description);
	}

	/**
	 * Log a message with stack trace of Exception at INFO level.
	 * 
	 * @param exception
	 *            a Exception to log stack trace
	 */
	public void logInfo(Throwable exception) {
		if (exception instanceof TNSException) {
			TNSException ex = (TNSException) exception;
			if (ex.getThrowable() != null) {
				logger.info(ex.getLocation(), ex.getThrowable());
			} else {
				logger.info(ex.getLocationMessage());
			}
		} else {
			logger.info(exception);
		}
	}

	/**
	 * Log a message at INFO level.
	 * 
	 * @param description
	 *            message object to be logged
	 */
	public void logInfo(String description) {
		logger.info(description);
	}

	/**
	 * Log a message with stack trace of Exception at WARNING level.
	 * 
	 * @param exception
	 *            a Exception to log stack trace
	 */
	public void logWarn(Throwable exception) {
		if (exception instanceof TNSException) {
			TNSException ex = (TNSException) exception;
			if (ex.getThrowable() != null) {
				logger.warn(ex.getLocation(), ex.getThrowable());
			} else {
				logger.warn(ex.getLocationMessage());
			}
		} else {
			logger.warn(exception);
		}
	}

	/**
	 * Log a message at WARNING level.
	 * 
	 * @param description
	 *            message object to be logged
	 */
	public void logWarn(String description) {
		logger.warn(description);
	}

	/**
	 * Log a message with stack trace of Exception at FATAL level.
	 * 
	 * @param exception
	 *            a Exception to log stack trace
	 */
	public void logFatal(Throwable exception) {
		if (exception instanceof TNSException) {
			TNSException ex = (TNSException) exception;
			if (ex.getThrowable() != null) {
				logger.fatal(ex.getLocation(), ex.getThrowable());
			} else {
				logger.fatal(ex.getLocationMessage());
			}
		} else {
			logger.fatal(exception);
		}
	}

	/**
	 * Log a message at FATAL level.
	 * 
	 * @param description
	 *            message object to be logged
	 */
	public void logFatal(String description) {
		logger.fatal(description);
	}

}