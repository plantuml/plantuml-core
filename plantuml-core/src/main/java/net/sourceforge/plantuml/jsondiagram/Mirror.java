package net.sourceforge.plantuml.jsondiagram;

import net.sourceforge.plantuml.klimt.geom.XPoint2D;

public class Mirror {

	private final double max;

	public Mirror(double max) {
		this.max = max;
	}

	public double inv(double v) {
		if (v < 0 || v > max) {
			System.err.println("BAD VALUE IN Mirror");
		}
		return max - v;
	}

	public XPoint2D invAndXYSwitch(XPoint2D pt) {
		final double x = inv(pt.getY());
		final double y = pt.getX();
		return new XPoint2D(x, y);
	}

	public XPoint2D invGit(XPoint2D pt) {
		final double x = pt.getX();
		final double y = inv(pt.getY());
		return new XPoint2D(x, y);
	}

}
