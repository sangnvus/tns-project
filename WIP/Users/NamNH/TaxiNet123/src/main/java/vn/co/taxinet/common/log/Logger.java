/**
 * Copyright(C) 2014, Trading and Service Information Network Company.
 * TaxiNet:
 *  Taxi Network System
 *
 * Record of change:
 * Date          Version   Modifier   Change    			Reason
 * 2014-12-01    1.0       Dev        Create structure		First creation
 * 
 */

package vn.co.taxinet.common.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * Simple wrapper around Log4J to provide logging features for other classes in
 * TaxiNet Backend.
 */

public class Logger {
	/** Full-qualified package name and its abbreviation. */
	private static final String FULL_NAME = "vn.co.taxinet";
	private static final String SHORT_NAME = "TaxiNet";

	/** Name of Logger to log statistics information. */
	private static final String STATS_NAME = "STATISTICS";

	/** Custom level for statistics logs. */
	private static final String STATS_LEVEL = "STATS";

	/** Constants for statistics logs. */
	private static final String STATS_BEGIN = "EjbBegin";
	private static final String STATS_ENDOK = "EjbEndOK";
	private static final String STATS_DELIM = "|";

	/** Flag to distinguish statistics Logger instance. */
	private boolean isStats = false;

	/** Reference to the Log4J instance being wrapped. */
	private org.apache.logging.log4j.Logger log = null;

	/**
	 * Initialize Log4J with specified property file. Called when Face Servlet
	 * is initialized.
	 * 
	 * @param propsUrl
	 *            the URL of LoginLog4J property file
	 */
	public static void initLogger(String propsUrl) {
		// org.apache.log4j.PropertyConfigurator.configure(propsUrl);
	}

	/**
	 * Create an instance for normal loggings with specified Log4J2 instance.
	 * 
	 * @param log4j
	 *            the instance of Log4J 2 Logger to wrap
	 */
	private Logger(org.apache.logging.log4j.Logger log4j) {
		this(log4j, true);
	}

	/**
	 * Create an instance for normal or statistics loggings with specified Log4J
	 * instance.
	 * 
	 * @param log4j
	 *            the instance of Log4J Logger to wrap
	 * @param isStats
	 *            true if for statistics logging, false otherwise
	 */
	private Logger(org.apache.logging.log4j.Logger log4j, boolean aIsStats) {
		log = log4j;
		isStats = aIsStats;
	}

	/**
	 * Get an instance of Logger for specified class.
	 * 
	 * @param clazz
	 *            the class that requires an instance of Logger
	 * @return an instance of Logger for specified class
	 */
	public static Logger getLogger(Class clazz) {
		String name = clazz.getName();
		if (name.startsWith(FULL_NAME)) {
			name = SHORT_NAME + name.substring(FULL_NAME.length());
		}
		return getLogger(name);
	}

	/**
	 * Get an instance of Logger for specified name.
	 * 
	 * @param name
	 *            the name to get Log4J Logger instance for
	 * @return an instance of Logger for specified name
	 */
	public static Logger getLogger(String name) {
		return new Logger(LogManager.getLogger(name));
	}

	/**
	 * Log a message at DEBUG level.
	 * 
	 * <p>
	 * <b>WARNING</b> Note that passing a {@link java.lang.Throwable} to this
	 * method will print the name of the Throwable but no stack trace. To print
	 * a stack trace use the
	 * {@link #debug(java.lang.Object, java.lang.Throwable)} form instead.
	 * 
	 * @param message
	 *            message object to be logged
	 */
	public void debug(Object message) {
		log.debug(message);
	}

	/**
	 * Log a message with stack trace of Throwable at DEBUG level.
	 * 
	 * @param message
	 *            message object to be logged
	 * @param t
	 *            a Throwable to log stact trace
	 */
	public void debug(Object message, Throwable t) {
		log.debug(message, t);
	}

	/**
	 * Log a message at ERROR level.
	 * 
	 * <p>
	 * <b>WARNING</b> Note that passing a {@link java.lang.Throwable} to this
	 * method will print the name of the Throwable but no stack trace. To print
	 * a stack trace use the
	 * {@link #error(java.lang.Object, java.lang.Throwable)} form instead.
	 * 
	 * @param message
	 *            message object to be logged
	 */
	public void error(Object message) {
		log.error(message);
	}

	/**
	 * Log a message with stack trace of Throwable at ERROR level.
	 * 
	 * @param message
	 *            message object to be logged
	 * @param t
	 *            a <code>Throwable</code> to log stack trace
	 */
	public void error(Object message, Throwable t) {
		log.error(message, t);
	}

	/**
	 * Log a message at FATAL level.
	 * 
	 * <p>
	 * <b>WARNING</b> Note that passing a {@link java.lang.Throwable} to this
	 * method will print the name of the Throwable but no stack trace. To print
	 * a stack trace use the
	 * {@link #fatal(java.lang.Object, java.lang.Throwable)} form instead.
	 * 
	 * @param message
	 *            message object to be logged
	 */
	public void fatal(Object message) {
		log.fatal(message);
	}

	/**
	 * Log a message with stack trace of Throwable at FATAL level.
	 * 
	 * @param message
	 *            message object to be logged
	 * @param t
	 *            a Throwable to log stact trace
	 */
	public void fatal(Object message, Throwable t) {
		log.fatal(message, t);
	}

	/**
	 * Log a message at INFO level.
	 * 
	 * <p>
	 * <b>WARNING</b> Note that passing a {@link java.lang.Throwable} to this
	 * method will print the name of the Throwable but no stack trace. To print
	 * a stack trace use the
	 * {@link #info(java.lang.Object, java.lang.Throwable)} form instead.
	 * 
	 * @param message
	 *            message object to be logged
	 */
	public void info(Object message) {
		log.info(message);
	}

	/**
	 * Log a message with stack trace of Throwable at INFO level.
	 * 
	 * @param message
	 *            message object to be logged
	 * @param t
	 *            a Throwable to log stact trace
	 */
	public void info(Object message, Throwable t) {
		log.info(message, t);
	}

	/**
	 * Log a message at WARN level.
	 * 
	 * <p>
	 * <b>WARNING</b> Note that passing a {@link java.lang.Throwable} to this
	 * method will print the name of the Throwable but no stack trace. To print
	 * a stack trace use the
	 * {@link #warn(java.lang.Object, java.lang.Throwable)} form instead.
	 * 
	 * @param message
	 *            message object to be logged
	 */
	public void warn(Object message) {
		log.warn(message);
	}

	/**
	 * Log a message with stack trace of Throwable at WARN level.
	 * 
	 * @param message
	 *            message object to be logged
	 * @param t
	 *            a Throwable to log stact trace
	 */
	public void warn(Object message, Throwable t) {
		log.warn(message, t);
	}

	/**
	 * Get an instance of Logger for statistics features.
	 * <P>
	 * Used in EJB classes to log statistics information.
	 * 
	 * @return an instance of Logger for statistics features
	 */
	public static Logger getStatisticsLogger() {
		return new Logger(
				org.apache.logging.log4j.LogManager.getLogger(STATS_NAME), true);
	}

	/**
	 * Log a statistic information at beginning of execution of an EJB service.
	 * 
	 * @param serviceName
	 *            name of the EJB service in execution
	 * @param freeMsg
	 *            any free message relates to the EJB service
	 */
	public void statsBegin(String serviceName, String freeMsg) {
		if (!isStats)
			return;
		this.stats(STATS_BEGIN + STATS_DELIM + serviceName + STATS_DELIM
				+ freeMsg);
	}

	/**
	 * Log a statistic information after an EJB service has been processed
	 * successfully.
	 * 
	 * @param serviceName
	 *            name of the EJB service in execution
	 * @param time
	 *            the milliseconds spent for this service execution
	 * @param freeMsg
	 *            any free message relates to the EJB service
	 */
	public void statsEndOK(String serviceName, long time, String freeMsg) {
		if (!isStats)
			return;
		this.stats(STATS_ENDOK + STATS_DELIM + serviceName + STATS_DELIM + time
				+ STATS_DELIM + freeMsg);
	}

	/**
	 * Log a statistic information to log file.
	 * 
	 * @param msg
	 *            the statistic message to log
	 */
	private void stats(String msg) {
		log.log(Level.ALL, msg);
	}

	/**
	 * Define a new log level (STATS) for statistic messages.
	 * <P>
	 * This level has higher priority than FATAL so that it can't be disabled in
	 * logger property file.
	 */
	public static class StatsLogLevel {

		public static final String STATS_STR = STATS_LEVEL;

		/**
		 * Convert the string passed as argument to a level. If the conversion
		 * fails, then this method returns {@link #STATS}.
		 */
		public static org.apache.logging.log4j.Level toLevel(String sArg) {
			return Level.toLevel(sArg);
		}

		/**
		 * 
		 * @param sArg
		 * @param defaultValue
		 * @return
		 */
		public static org.apache.logging.log4j.Level toLevel(String sArg,
				Level defaultValue) {
			if (sArg == null) {
				return defaultValue;
			} else if (STATS_STR.equals(sArg.toUpperCase())) {
				return Level.toLevel(STATS_STR);
			} else {
				return Level.toLevel(sArg, defaultValue);
			}
		}
	}
}