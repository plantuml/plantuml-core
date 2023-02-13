package net.sourceforge.plantuml.klimt;

public class UStroke implements UChange {

	private final double dashVisible;
	private final double dashSpace;
	private final double thickness;

	@Override
	public int hashCode() {
		return Double.hashCode(dashVisible) + Double.hashCode(dashSpace) + Double.hashCode(thickness);
	}

	@Override
	public boolean equals(Object obj) {
		final UStroke other = (UStroke) obj;
		return this.dashVisible == other.dashVisible && this.dashSpace == other.dashSpace
				&& this.thickness == other.thickness;
	}

	@Override
	public String toString() {
		return "" + dashVisible + "-" + dashSpace + "-" + thickness;
	}

	public UStroke(double dashVisible, double dashSpace, double thickness) {
		this.dashVisible = dashVisible;
		this.dashSpace = dashSpace;
		this.thickness = thickness;
	}

	public UStroke(double thickness) {
		this(0, 0, thickness);
	}

	public UStroke() {
		this(1.0);
	}

	public UStroke onlyThickness() {
		return new UStroke(thickness);
	}

	private UStroke applyThickness(UStroke thickness) {
		if (thickness == null)
			return this;

		return new UStroke(dashVisible, dashSpace, thickness.thickness);
	}

	public double getDashVisible() {
		return dashVisible;
	}

	public double getDashSpace() {
		return dashSpace;
	}

	public double getThickness() {
		return thickness;
	}

	public String getDasharraySvg() {
		if (dashVisible == 0)
			return null;

		return "" + dashVisible + "," + dashSpace;
	}

	public String getDashTikz() {
		if (dashVisible == 0) {
			return null;
		}
		return "on " + dashVisible + "pt off " + dashSpace + "pt";
	}

	// public String getDasharrayEps() {
	// if (dashVisible == 0) {
	// return null;
	// }
	// return "" + dashVisible + " " + dashSpace;
	// }

}
