package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.UEllipse;
import net.sourceforge.plantuml.klimt.shape.ULine;

public class EntityDomain extends AbstractTextBlock implements TextBlock {

	private final double margin = 4;

	private final double radius = 12;
	private final double suppY = 2;
	private final Fashion symbolContext;

	public EntityDomain(Fashion symbolContext) {
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
		ug.apply(new UTranslate(x, y + 2 * radius + suppY)).draw(ULine.hline(2 * radius));
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(radius * 2 + 2 * margin, radius * 2 + 2 * margin);
	}

}
