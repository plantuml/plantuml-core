package net.sourceforge.plantuml.klimt.geom;

import net.sourceforge.plantuml.awt.geom.XPoint2D;

public class PointAndAngle {

	private final XPoint2D pt;
	private final double angle;

	public PointAndAngle(XPoint2D pt, double angle) {
		this.pt = pt;
		this.angle = angle;
	}

	public XPoint2D getPt() {
		return pt;
	}

	public double getAngle() {
		return angle;
	}

	public double getX() {
		return pt.getX();
	}

	public double getY() {
		return pt.getY();
	}

}
