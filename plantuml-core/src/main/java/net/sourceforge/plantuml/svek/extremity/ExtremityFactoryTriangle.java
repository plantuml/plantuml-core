package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.geom.Side;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.svek.AbstractExtremityFactory;

public class ExtremityFactoryTriangle extends AbstractExtremityFactory implements ExtremityFactory {

	private final HColor backgroundColor;
	private final int xWing;
	private final int yAperture;

	public ExtremityFactoryTriangle(HColor backgroundColor, int xWing, int yAperture) {
		this.backgroundColor = backgroundColor;
		this.xWing = xWing;
		this.yAperture = yAperture;
	}

	@Override
	public UDrawable createUDrawable(XPoint2D p0, double angle, Side side) {
		return new ExtremityTriangle(p0, angle - Math.PI / 2, false, backgroundColor, xWing, yAperture);
	}

	@Override
	public UDrawable createUDrawable(XPoint2D p0, XPoint2D p1, XPoint2D p2, Side side) {
		final double ortho = atan2(p0, p2);
		return new ExtremityTriangle(p1, ortho, true, backgroundColor, xWing, yAperture);
	}

}
