package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.SymbolContext;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class Boundary extends AbstractTextBlock {

	private final double margin = 4;

	private final double radius = 12;
	private final double left = 17;
	
	private final SymbolContext symbolContext;

	public Boundary(SymbolContext symbolContext) {
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

		final UPath path1 = new UPath();
		path1.moveTo(0, 0);
		path1.lineTo(0, radius * 2);
		path1.setDeltaShadow(symbolContext.getDeltaShadow());

		final UPath path = new UPath();
		path.moveTo(0, 0);
		path.lineTo(0, radius * 2);
		path.moveTo(0, radius);
		path.lineTo(left, radius);
		path.setDeltaShadow(symbolContext.getDeltaShadow());
		ug.apply(new UTranslate(x, y)).apply(HColors.none().bg()).draw(path);

		// final ULine line1 = ULine.dy(radius * 2);
		// line1.setDeltaShadow(deltaShadow);
		// ug.apply(new UTranslate(x, y)).draw(line1);
		// final ULine line2 = new ULine(left, 0);
		// line2.setDeltaShadow(deltaShadow);
		// ug.apply(new UTranslate(x, y + radius)).draw(line2);

		ug.apply(new UTranslate(x + left, y)).draw(circle);

	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(radius * 2 + left + 2 * margin, radius * 2 + 2 * margin);
	}

}
