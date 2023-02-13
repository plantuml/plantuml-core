package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.geom.Side;
import net.sourceforge.plantuml.svek.AbstractExtremityFactory;

public class ExtremityFactoryExtends extends AbstractExtremityFactory implements ExtremityFactory {

	private final HColor backgroundColor;

	public ExtremityFactoryExtends(HColor backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	@Override
	public UDrawable createUDrawable(XPoint2D p0, double angle, Side side) {
		return new ExtremityExtends(p0, angle - Math.PI / 2, backgroundColor);
	}

	@Override
	public UDrawable createUDrawable(XPoint2D p0, XPoint2D p1, XPoint2D p2, Side side) {
		throw new UnsupportedOperationException();
		// final double ortho = atan2(p0, p2);
		// return new ExtremityExtends(p1, ortho, true);
	}

}
