package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.klimt.geom.XPoint2D;

public class YDelta implements Point2DFunction {

	private final double delta;
	private final double factor;

	public YDelta(double delta) {
		this(1, delta);
	}

	public YDelta(double factor, double delta) {
		this.delta = delta;
		this.factor = factor;
	}

	public XPoint2D apply(XPoint2D pt) {
		return new XPoint2D(pt.getX(), pt.getY() * factor + delta);

	}

}
