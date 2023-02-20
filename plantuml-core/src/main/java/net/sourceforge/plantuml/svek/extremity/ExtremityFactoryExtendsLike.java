package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.geom.Side;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.svek.AbstractExtremityFactory;

public class ExtremityFactoryExtendsLike extends AbstractExtremityFactory implements ExtremityFactory {

	private final HColor backgroundColor;
	private final boolean definedBy;

	public ExtremityFactoryExtendsLike(HColor backgroundColor, boolean definedBy) {
		this.backgroundColor = backgroundColor;
		this.definedBy = definedBy;
	}

	@Override
	public UDrawable createUDrawable(XPoint2D p0, double angle, Side side) {
		if (definedBy) {
			return new ExtremityExtendsLike.DefinedBy(p0, angle, backgroundColor);
		} else {
			return new ExtremityExtendsLike.Redefines(p0, angle, backgroundColor);
		}
	}

	@Override
	public UDrawable createUDrawable(XPoint2D p0, XPoint2D p1, XPoint2D p2, Side side) {
		final double ortho = atan2(p0, p2) + (Math.PI / 2.0);
		if (definedBy) {
			return new ExtremityExtendsLike.DefinedBy(p1, ortho, backgroundColor);
		} else {
			return new ExtremityExtendsLike.Redefines(p1, ortho, backgroundColor);
		}
	}
}
