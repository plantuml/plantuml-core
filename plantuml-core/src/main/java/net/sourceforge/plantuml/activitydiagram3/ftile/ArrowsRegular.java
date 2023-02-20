package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.klimt.Arrows;
import net.sourceforge.plantuml.klimt.shape.UPolygon;

public class ArrowsRegular extends Arrows {

	final static private double delta1 = 10;
	final static private double delta2 = 4;

	@Override
	public UPolygon asToUp() {
		final UPolygon polygon = new UPolygon("asToUp");
		polygon.addPoint(-delta2, delta1);
		polygon.addPoint(0, 0);
		polygon.addPoint(delta2, delta1);
		polygon.addPoint(0, delta1 - 4);
		return polygon;
	}

	@Override
	public UPolygon asToDown() {
		final UPolygon polygon = new UPolygon("asToDown");
		polygon.addPoint(-delta2, -delta1);
		polygon.addPoint(0, 0);
		polygon.addPoint(delta2, -delta1);
		polygon.addPoint(0, -delta1 + 4);
		return polygon;
	}

	@Override
	public UPolygon asToRight() {
		final UPolygon polygon = new UPolygon("asToRight");
		polygon.addPoint(-delta1, -delta2);
		polygon.addPoint(0, 0);
		polygon.addPoint(-delta1, delta2);
		polygon.addPoint(-delta1 + 4, 0);
		return polygon;
	}

	@Override
	public UPolygon asToLeft() {
		final UPolygon polygon = new UPolygon("asToLeft");
		polygon.addPoint(delta1, -delta2);
		polygon.addPoint(0, 0);
		polygon.addPoint(delta1, delta2);
		polygon.addPoint(delta1 - 4, 0);
		return polygon;
	}

}
