package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.klimt.geom.BezierUtils;
import net.sourceforge.plantuml.klimt.geom.XCubicCurve2D;
import net.sourceforge.plantuml.klimt.geom.XLine2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.shape.DotPath;

public class RacorderFollowTangeanteOld extends RacorderAbstract implements Racorder {

	public DotPath getRacordIn(XRectangle2D rect, XLine2D tangeante) {

		final DotPath result = new DotPath();

		final XPoint2D center = new XPoint2D(rect.getCenterX(), rect.getCenterY());
		final XLine2D line = new XLine2D(tangeante.getP1(), center);
		final XPoint2D inter = BezierUtils.intersect(line, rect);

		final XCubicCurve2D curv = new XCubicCurve2D(tangeante.getX1(), tangeante.getY1(), tangeante.getX2(),
				tangeante.getY2(), tangeante.getX2(), tangeante.getY2(), inter.getX(), inter.getY());
		return result.addAfter(curv);
	}

}
