package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.klimt.geom.Side;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.svek.AbstractExtremityFactory;

public class ExtremityFactoryArrow extends AbstractExtremityFactory implements ExtremityFactory {

	@Override
	public UDrawable createUDrawable(XPoint2D p0, double angle, Side side) {
		return new ExtremityArrow(p0, angle);
	}

	@Override
	public UDrawable createUDrawable(XPoint2D p0, XPoint2D p1, XPoint2D p2, Side side) {
		final double ortho = atan2(p0, p2);
		final XPoint2D center = new XPoint2D((p0.getX() + p2.getX()) / 2, (p0.getY() + p2.getY()) / 2);
		return new ExtremityArrow(p1, ortho, center);
	}

}
