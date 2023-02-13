package net.sourceforge.plantuml.sequencediagram;

import java.text.DecimalFormat;

public class AutoNumber {

	private boolean running = false;
	private DottedNumber current;
	private int increment;

	private DecimalFormat format;
	private String last = "";

	public final void go(DottedNumber startingNumber, int increment, DecimalFormat format) {
		this.running = true;
		this.current = startingNumber;
		this.increment = increment;
		this.format = format;
	}

	public final void stop() {
		this.running = false;
	}

	public final void resume(DecimalFormat format) {
		this.running = true;
		if (format != null) {
			this.format = format;
		}
	}

	public final void resume(int increment, DecimalFormat format) {
		this.running = true;
		this.increment = increment;
		if (format != null) {
			this.format = format;
		}
	}

	public void incrementIntermediate() {
		current.incrementIntermediate();
	}

	public void incrementIntermediate(int position) {
		current.incrementIntermediate(position);
	}

	public String getNextMessageNumber() {
		if (running == false) {
			return null;
		}
		last = current.format(format);
		current.incrementMinor(increment);
		return last;
	}

	public String getCurrentMessageNumber(boolean formatted) {
		if (formatted) {
			return last;
		}
		return last.replace("<b>", "").replace("</b>", "");
	}
}
