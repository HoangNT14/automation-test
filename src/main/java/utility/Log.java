package utility;

import org.apache.log4j.Logger;

public class Log {
	private static Logger Log = Logger.getLogger(Log.class.getName());

	public static void startTestCase(String testCaseName) {
		info("**********************************************************");
		info("**********************************************************");
		info("**" + testCaseName + "**");
		info("**********************************************************");
		info("**********************************************************");
	}

	public static void endTestCase(String testCaseName) {
		info("**********************************************************");
		info("**" + testCaseName + " END **");
		info("**********************************************************");
	}

	public static void info(String message) {
		System.out.println("INFO: " + message);
		Log.info("INFO: " + message);
	}

	public static void warn(String message) {
		System.out.println("WARN: " + message);
		Log.warn("WARN: " + message);
	}

	public static void error(String message) {
		System.out.println("ERROR: " + message);
		Log.error("ERROR: " + message);
	}

	public static void fatal(String message) {
		System.out.println("FATAL: " + message);
		Log.fatal("FATAL: " + message);
	}

	public static void debug(String message) {
		System.out.println("DEBUG: " + message);
		Log.debug("DEBUG: " + message);
	}
}
