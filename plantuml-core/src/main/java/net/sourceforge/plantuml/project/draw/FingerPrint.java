package net.sourceforge.plantuml.project.draw;

public class FingerPrint {

	private final double x;
	private final double y;
	private final double width;
	private final double height;

	public FingerPrint(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return "X=(" + x + "->" + (x + width) + ") Y=(" + y + " ->" + (y + height) + ")";
	}

	public double overlap(FingerPrint other) {
		if (x >= other.x + other.width || other.x >= x + width) {
			return 0;
		}

		if (y >= other.y + other.height || other.y >= y + height) {
			return 0;
		}

		return y + height - other.y;
	}

}
