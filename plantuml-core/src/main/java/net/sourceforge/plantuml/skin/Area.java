package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class Area {

	private final XDimension2D dimensionToUse;
	private double deltaX1;

	@Override
	public String toString() {
		return dimensionToUse.toString() + " (" + deltaX1 + ")";
	}

	public Area(XDimension2D dimensionToUse) {
		this.dimensionToUse = dimensionToUse;
	}

	public static Area create(double with, double height) {
		return new Area(new XDimension2D(with, height));
	}

	public XDimension2D getDimensionToUse() {
		return dimensionToUse;
	}

	public void setDeltaX1(double deltaX1) {
		this.deltaX1 = deltaX1;
	}

	public final double getDeltaX1() {
		return deltaX1;
	}

}
