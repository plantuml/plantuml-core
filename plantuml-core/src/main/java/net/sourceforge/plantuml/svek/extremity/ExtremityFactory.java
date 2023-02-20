package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.klimt.geom.Side;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UDrawable;

public interface ExtremityFactory {

	public UDrawable createUDrawable(XPoint2D p0, XPoint2D p1, XPoint2D p2, Side side);

	public UDrawable createUDrawable(XPoint2D p0, double angle, Side side);

}
