package net.sourceforge.plantuml;

import java.util.Objects;

import net.sourceforge.plantuml.utils.LineLocation;

public class ErrorUml {

	private final String error;
	private final ErrorUmlType type;
	private final LineLocation lineLocation;
	private final int score;

	public ErrorUml(ErrorUmlType type, String error, int score, LineLocation lineLocation) {
		this.score = score;
		this.error = Objects.requireNonNull(error);
		this.type = Objects.requireNonNull(type);
		this.lineLocation = lineLocation;
	}

	public int score() {
		return score;
	}

	@Override
	public boolean equals(Object obj) {
		final ErrorUml this2 = (ErrorUml) obj;
		return this.type == this2.type && this.getPosition() == this2.getPosition() && this.error.equals(this2.error);
	}

	@Override
	public int hashCode() {
		return error.hashCode() + type.hashCode() + getPosition();
	}

	@Override
	public String toString() {
		return type.toString() + " " + getPosition() + " " + error;
	}

	public final String getError() {
		return error;
	}

	public final ErrorUmlType getType() {
		return type;
	}

	public final int getPosition() {
		return lineLocation.getPosition();
	}

	public final LineLocation getLineLocation() {
		return lineLocation;
	}

}
