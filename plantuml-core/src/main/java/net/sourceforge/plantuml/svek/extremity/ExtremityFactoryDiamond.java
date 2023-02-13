package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.geom.Side;
import net.sourceforge.plantuml.svek.AbstractExtremityFactory;

public class ExtremityFactoryDiamond extends AbstractExtremityFactory implements ExtremityFactory {

	private final boolean fill;

	@Override
	public UDrawable createUDrawable(XPoint2D p0, double angle, Side side) {
		return new ExtremityDiamond(p0, angle - Math.PI / 2, fill);
	}

	public ExtremityFactoryDiamond(boolean fill) {
		this.fill = fill;
	}

	@Override
	public UDrawable createUDrawable(XPoint2D p0, XPoint2D p1, XPoint2D p2, Side side) {
		final double ortho = atan2(p0, p2);
		return new ExtremityDiamond(p1, ortho, fill);
	}

}
