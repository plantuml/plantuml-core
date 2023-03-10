// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.klimt.geom.XLine2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.geom.XRectangle2D;

public class LineRectIntersection {

	private final XPoint2D inter;

	public LineRectIntersection(XLine2D line, XRectangle2D rect) {
		final XPoint2D p1 = new XPoint2D(rect.getMinX(), rect.getMinY());
		final XPoint2D p2 = new XPoint2D(rect.getMaxX(), rect.getMinY());
		final XPoint2D p3 = new XPoint2D(rect.getMaxX(), rect.getMaxY());
		final XPoint2D p4 = new XPoint2D(rect.getMinX(), rect.getMaxY());

		final XPoint2D inter1 = new LineSegmentIntersection(XLine2D.line(p1, p2), line).getIntersection();
		final XPoint2D inter2 = new LineSegmentIntersection(XLine2D.line(p2, p3), line).getIntersection();
		final XPoint2D inter3 = new LineSegmentIntersection(XLine2D.line(p3, p4), line).getIntersection();
		final XPoint2D inter4 = new LineSegmentIntersection(XLine2D.line(p4, p1), line).getIntersection();

		final XPoint2D o = line.getP1();
		inter = getCloser(o, inter1, inter2, inter3, inter4);

	}

	public static XPoint2D getCloser(final XPoint2D o, final XPoint2D... other) {
		double minDist = Double.MAX_VALUE;
		XPoint2D result = null;

		for (XPoint2D pt : other)
			if (pt != null) {
				final double dist = pt.distanceSq(o);
				if (dist < minDist) {
					minDist = dist;
					result = pt;
				}
			}

		return result;
	}

	public final XPoint2D getIntersection() {
		return inter;
	}

}
