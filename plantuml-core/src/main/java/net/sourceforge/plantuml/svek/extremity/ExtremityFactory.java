package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.geom.Side;

public interface ExtremityFactory {

	public UDrawable createUDrawable(XPoint2D p0, XPoint2D p1, XPoint2D p2, Side side);
	
	public UDrawable createUDrawable(XPoint2D p0, double angle, Side side);

}
