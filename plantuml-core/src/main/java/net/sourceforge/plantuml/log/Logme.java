package net.sourceforge.plantuml.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logme {

	private static final Logger logger;

	static {
		logger = Logger.getLogger("com.plantuml");
		logger.setUseParentHandlers(false);
		final ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		logger.addHandler(handler);
	}

	public static void error(Throwable thrown) {
		logger.log(Level.SEVERE, "", thrown);
	}

	// Unused right now
	//
	// public static void error(String msg, Throwable thrown) {
	// logger.log(Level.SEVERE, msg, thrown);
	// }
	//
	// public static void error(String msg) {
	// logger.log(Level.SEVERE, msg);
	// }
}
