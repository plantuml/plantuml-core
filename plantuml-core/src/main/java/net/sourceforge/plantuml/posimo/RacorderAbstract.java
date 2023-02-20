package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.klimt.geom.XLine2D;
import net.sourceforge.plantuml.klimt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.shape.DotPath;

public abstract class RacorderAbstract implements Racorder {

	public final DotPath getRacordOut(XRectangle2D rect, XLine2D tangeante) {
		tangeante = symetric(tangeante);
		return getRacordIn(rect, tangeante).reverse();
	}

	private static XLine2D symetric(XLine2D line) {
		final double x1 = line.getX1();
		final double y1 = line.getY1();
		final double x2 = line.getX2();
		final double y2 = line.getY2();
		final double dx = x2 - x1;
		final double dy = y2 - y1;
		return new XLine2D(x1, y1, x1 - dx, y1 - dy);
	}

}
