package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class MiddleCircle extends Extremity {

	private final double radius = 6;
	private final UEllipse circle = new UEllipse(2 * radius, 2 * radius);
	private final HColor backColor;
	
	public MiddleCircle(HColor backColor) {
		this.backColor = backColor;
	}


	@Override
	public XPoint2D somePoint() {
		return null;
	}


	public void drawU(UGraphic ug) {
		ug.apply(backColor.bg()).apply(new UStroke(1.5)).apply(new UTranslate(-radius, -radius)).draw(circle);
	}

}
