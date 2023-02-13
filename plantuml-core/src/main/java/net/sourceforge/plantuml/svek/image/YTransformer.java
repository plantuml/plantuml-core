package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.awt.geom.XPoint2D;

public class YTransformer {

	private final double alpha;

	public YTransformer(double alpha) {
		this.alpha = alpha;
	}

	public XPoint2D getPoint2D(XPoint2D pt) {
		return new XPoint2D(pt.getX(), pt.getY() * alpha);
	}

	public XPoint2D getReversePoint2D(XPoint2D pt) {
		return new XPoint2D(pt.getX(), pt.getY() / alpha);
	}

	public double getAlpha() {
		return alpha;
	}

}
