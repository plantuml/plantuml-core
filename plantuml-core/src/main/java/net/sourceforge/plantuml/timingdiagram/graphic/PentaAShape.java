package net.sourceforge.plantuml.timingdiagram.graphic;

import net.sourceforge.plantuml.graphic.SymbolContext;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UPolygon;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class PentaAShape implements UDrawable {

	private final double width;
	private final double height;
	private final SymbolContext context;

	private final double delta = 12;

	private PentaAShape(double width, double height, SymbolContext context) {
		this.width = width;
		this.height = height;
		this.context = context;
	}

	public static PentaAShape create(double width, double height, SymbolContext context) {
		return new PentaAShape(width, height, context);
	}

	public void drawU(UGraphic ug) {
		final UPolygon polygon = new UPolygon();
		polygon.addPoint(0, 0);
		polygon.addPoint(width - delta, 0);
		polygon.addPoint(width, height / 2);
		polygon.addPoint(width - delta, height);
		polygon.addPoint(0, height);

		context.withForeColor(context.getBackColor()).apply(ug).draw(polygon);

		final UPath path = new UPath();
		path.moveTo(0, 0);
		path.lineTo(width - delta, 0);
		path.lineTo(width, height / 2);
		path.lineTo(width - delta, height);
		path.lineTo(0, height);

		context.apply(ug).draw(path);

	}

}
