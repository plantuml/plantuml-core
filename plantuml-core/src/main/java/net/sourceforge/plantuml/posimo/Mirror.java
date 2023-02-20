package net.sourceforge.plantuml.posimo;

public class Mirror {

	private final double max;

	public Mirror(double max) {
		this.max = max;
	}

	public double getMirrored(double v) {
		if (v < 0 || v > max) {
			throw new IllegalArgumentException();
		}
		// return v;
		return max - v;
	}

}
