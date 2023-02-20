package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.geom.Side;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.svek.AbstractExtremityFactory;

public class ExtremityFactoryPlus extends AbstractExtremityFactory implements ExtremityFactory {

	private final HColor backgroundColor;

	public ExtremityFactoryPlus(HColor backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	@Override
	public UDrawable createUDrawable(XPoint2D center, double angle, Side side) {
		return ExtremityPlus.create(center, angle - Math.PI / 2, backgroundColor);
	}

	@Override
	public UDrawable createUDrawable(XPoint2D p0, XPoint2D p1, XPoint2D p2, Side side) {
		final double ortho = atan2(p0, p2);
		return ExtremityPlus.create(p1, ortho, backgroundColor);
	}

}
