package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.klimt.geom.XPoint2D;

public enum SnakeDirection {
	VERTICAL_THEN_HORIZONTAL, HORIZONTAL_THEN_VERTICAL;

	public static SnakeDirection getDirection(XPoint2D pt1, XPoint2D pt2) {
		if (pt1.getX() == pt2.getX()) {
			return VERTICAL_THEN_HORIZONTAL;
		}
		if (pt1.getY() == pt2.getY()) {
			return HORIZONTAL_THEN_VERTICAL;
		}
		throw new IllegalArgumentException();
	}

}
