package net.sourceforge.plantuml.timingdiagram.graphic;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.klimt.shape.UPolygon;

public class HexaShape implements UDrawable {

	private final double width;
	private final double height;
	private final Fashion context;

	private final double delta = 12;

	private HexaShape(double width, double height, Fashion context) {
		this.width = width;
		this.height = height;
		this.context = context;
	}

	public static HexaShape create(double width, double height, Fashion context) {
		return new HexaShape(width, height, context);
	}

	public void drawU(UGraphic ug) {
		ug = context.apply(ug);
		final UPolygon polygon = new UPolygon();
		polygon.addPoint(delta, 0);
		polygon.addPoint(width - delta, 0);
		polygon.addPoint(width, height / 2);
		polygon.addPoint(width - delta, height);
		polygon.addPoint(delta, height);
		polygon.addPoint(0, height / 2);
		ug.draw(polygon);

	}

}
