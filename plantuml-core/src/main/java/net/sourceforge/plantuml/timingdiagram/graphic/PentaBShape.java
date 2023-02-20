package net.sourceforge.plantuml.timingdiagram.graphic;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.klimt.shape.UPolygon;

public class PentaBShape implements UDrawable {

	private final double width;
	private final double height;
	private final Fashion context;

	private final double delta = 12;

	private PentaBShape(double width, double height, Fashion context) {
		this.width = width;
		this.height = height;
		this.context = context;
	}

	public static PentaBShape create(double width, double height, Fashion context) {
		return new PentaBShape(width, height, context);
	}

	public void drawU(UGraphic ug) {
		final UPolygon polygon = new UPolygon();
		polygon.addPoint(delta, 0);
		polygon.addPoint(width, 0);
		polygon.addPoint(width, height);
		polygon.addPoint(delta, height);
		polygon.addPoint(0, height / 2);

		context.withForeColor(context.getBackColor()).apply(ug).draw(polygon);

		final UPath path = new UPath();
		path.moveTo(width, 0);
		path.lineTo(delta, 0);
		path.lineTo(0, height / 2);
		path.lineTo(delta, height);
		path.lineTo(width, height);
		context.apply(ug).draw(path);

	}

}
