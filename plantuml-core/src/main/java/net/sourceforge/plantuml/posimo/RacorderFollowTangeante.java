package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.klimt.geom.XCubicCurve2D;
import net.sourceforge.plantuml.klimt.geom.XLine2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.shape.DotPath;

public class RacorderFollowTangeante extends RacorderAbstract implements Racorder {

	@Override
	public DotPath getRacordIn(XRectangle2D rect, XLine2D tangeante) {

		final DotPath result = new DotPath();

		XPoint2D inter = new LineRectIntersection(tangeante, rect).getIntersection();

		if (inter == null) {
			final XPoint2D p1 = new XPoint2D(rect.getMinX(), rect.getMinY());
			final XPoint2D p2 = new XPoint2D(rect.getMaxX(), rect.getMinY());
			final XPoint2D p3 = new XPoint2D(rect.getMaxX(), rect.getMaxY());
			final XPoint2D p4 = new XPoint2D(rect.getMinX(), rect.getMaxY());

			inter = LineRectIntersection.getCloser(tangeante.getP1(), p1, p2, p3, p4);
		}

		final XCubicCurve2D curv = new XCubicCurve2D(tangeante.getX1(), tangeante.getY1(), tangeante.getX1(),
				tangeante.getY1(), inter.getX(), inter.getY(), inter.getX(), inter.getY());
		return result.addAfter(curv);
	}

}
