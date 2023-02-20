package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.UEllipse;
import net.sourceforge.plantuml.klimt.shape.UPolygon;

public class Control extends AbstractTextBlock implements TextBlock {

	private final double margin = 4;

	private final double radius = 12;
	private final Fashion symbolContext;

	public Control(Fashion symbolContext) {
		this.symbolContext = symbolContext;
	}

	public void drawU(UGraphic ug) {
		double x = 0;
		double y = 0;
		x += margin;
		y += margin;
		ug = symbolContext.apply(ug);
		final UEllipse circle = new UEllipse(radius * 2, radius * 2);
		circle.setDeltaShadow(symbolContext.getDeltaShadow());
		ug.apply(new UTranslate(x, y)).draw(circle);
		ug = ug.apply(new UStroke());

		ug = ug.apply(symbolContext.getForeColor().bg());
		final UPolygon polygon = new UPolygon();
		polygon.addPoint(0, 0);
		final int xWing = 6;
		final int yAperture = 5;
		polygon.addPoint(xWing, -yAperture);
		final int xContact = 4;
		polygon.addPoint(xContact, 0);
		polygon.addPoint(xWing, yAperture);
		polygon.addPoint(0, 0);

		ug.apply(new UTranslate(x + radius - xContact, y)).draw(polygon);
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(radius * 2 + 2 * margin, radius * 2 + 2 * margin);
	}

}
