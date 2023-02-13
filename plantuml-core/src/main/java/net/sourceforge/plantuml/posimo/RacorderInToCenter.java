package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.awt.geom.XCubicCurve2D;
import net.sourceforge.plantuml.awt.geom.XLine2D;
import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.awt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.DotPath;
import net.sourceforge.plantuml.klimt.geom.BezierUtils;

public class RacorderInToCenter extends RacorderAbstract implements Racorder {

	@Override
	public DotPath getRacordIn(XRectangle2D rect, XLine2D tangeante) {
		final DotPath result = new DotPath();

		final XPoint2D center = new XPoint2D(rect.getCenterX(), rect.getCenterY());
		final XLine2D line = new XLine2D(tangeante.getP1(), center);
		final XPoint2D inter = BezierUtils.intersect(line, rect);

		final XCubicCurve2D curv = new XCubicCurve2D(line.getX1(), line.getY1(), line.getX1(), line.getY1(),
				inter.getX(), inter.getY(), inter.getX(), inter.getY());
		return result.addAfter(curv);
	}

}
