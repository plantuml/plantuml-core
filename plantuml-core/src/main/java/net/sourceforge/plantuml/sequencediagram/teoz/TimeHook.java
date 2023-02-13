package net.sourceforge.plantuml.sequencediagram.teoz;

public final class TimeHook {

	private final double value;

	public TimeHook(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "" + value;
	}

}
