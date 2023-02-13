package net.sourceforge.plantuml.project;

import net.sourceforge.plantuml.klimt.Arrows;
import net.sourceforge.plantuml.klimt.UPolygon;

public class GArrows extends Arrows {

	final static private double delta2 = 4;

	@Override
	public UPolygon asToUp() {
		final UPolygon polygon = new UPolygon("asToUp");
		polygon.addPoint(-delta2, 0);
		polygon.addPoint(0, 0);
		polygon.addPoint(delta2, 0);
		polygon.addPoint(0, -4);
		return polygon;
	}

	@Override
	public UPolygon asToDown() {
		final UPolygon polygon = new UPolygon("asToDown");
		polygon.addPoint(-delta2, 0);
		polygon.addPoint(0, 0);
		polygon.addPoint(delta2, 0);
		polygon.addPoint(0, 4);
		return polygon;
	}

	@Override
	public UPolygon asToRight() {
		final UPolygon polygon = new UPolygon("asToRight");
		polygon.addPoint(0, -delta2);
		polygon.addPoint(0, 0);
		polygon.addPoint(0, delta2);
		polygon.addPoint(4, 0);
		return polygon.translate(-4, 0);
	}

	@Override
	public UPolygon asToLeft() {
		final UPolygon polygon = new UPolygon("asToLeft");
		polygon.addPoint(0, -delta2);
		polygon.addPoint(0, 0);
		polygon.addPoint(0, delta2);
		polygon.addPoint(-4, 0);
		return polygon.translate(4, 0);
	}

}
