package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.geom.Side;
import net.sourceforge.plantuml.svek.extremity.ExtremityFactory;

public abstract class AbstractExtremityFactory implements ExtremityFactory {

	public UDrawable createUDrawable(XPoint2D p0, double angle, Side side) {
		throw new UnsupportedOperationException(getClass().toString());
	}

	protected double atan2(XPoint2D p1, XPoint2D p0) {
		double a = Math.atan2(p1.getY() - p0.getY(), p1.getX() - p0.getX());
		if (a < 0)
			a += Math.PI * 2;

		return a;
	}

}
