package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.URectangle;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class ExtremitySquare extends Extremity {

	private final HColor backgroundColor;
	private final XPoint2D dest;
	private final double radius = 5;

	@Override
	public XPoint2D somePoint() {
		return dest;
	}

	public ExtremitySquare(XPoint2D p1, HColor backgroundColor) {
		this.dest = new XPoint2D(p1.getX(), p1.getY());
		this.backgroundColor = backgroundColor;
	}

	public void drawU(UGraphic ug) {
		ug.apply(new UStroke(1.5)).apply(backgroundColor.bg()).apply(new UTranslate(dest.getX() - radius, dest.getY() - radius)).draw(new URectangle(radius * 2, radius * 2));
	}

}
