package net.sourceforge.plantuml.posimo;

import java.awt.geom.Line2D;

import net.sourceforge.plantuml.klimt.geom.XPoint2D;

public class TwoLinesIntersection {

	private final XPoint2D inter;

	public TwoLinesIntersection(Line2D lineA, Line2D lineB) {
		final double x1 = lineA.getX1();
		final double y1 = lineA.getY1();
		final double x2 = lineA.getX2();
		final double y2 = lineA.getY2();
		final double x3 = lineB.getX1();
		final double y3 = lineB.getY1();
		final double x4 = lineB.getX2();
		final double y4 = lineB.getY2();

		final double den = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);

		final double uA1 = (x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3);
		final double uA = uA1 / den;

		// final double uB1 = (x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3);
		// uB = uB1 / den;

		final double x = x1 + uA * (x2 - x1);
		final double y = y1 + uA * (y2 - y1);

		inter = new XPoint2D(x, y);
	}

	public final XPoint2D getIntersection() {
		return inter;
	}

}
