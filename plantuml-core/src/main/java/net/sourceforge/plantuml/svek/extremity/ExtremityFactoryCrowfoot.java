package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.geom.Side;
import net.sourceforge.plantuml.svek.AbstractExtremityFactory;

public class ExtremityFactoryCrowfoot extends AbstractExtremityFactory implements ExtremityFactory {

	@Override
	public UDrawable createUDrawable(XPoint2D p0, XPoint2D p1, XPoint2D p2, Side side) {
		final double ortho = atan2(p0, p2);
		return new ExtremityCrowfoot(p1, ortho, side);
	}

	@Override
	public UDrawable createUDrawable(XPoint2D p0, double angle, Side side) {
		angle -= Math.PI / 2;
		return new ExtremityCrowfoot(p0, angle, side);
	}

}
