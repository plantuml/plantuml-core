package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;

public class EaterException extends Exception {

	private final String message;

	private EaterException(String message) {
		this.message = message;
	}

	public static EaterException unlocated(String message) {
		return new EaterException(message);
	}

	public static EaterException located(String message) {
		return unlocated(message);
	}

	public final String getMessage() {
		return message;
	}

	public EaterExceptionLocated withLocation(StringLocated sl) {
		return EaterExceptionLocated.located(message, sl);
	}

}
