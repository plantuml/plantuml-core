package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.UPolygon;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class ExtremityExtends extends Extremity {

	private UPolygon polygon = new UPolygon();
	private final HColor fill;
	private final XPoint2D contact;

	@Override
	public XPoint2D somePoint() {
		return contact;
	}

	public ExtremityExtends(XPoint2D p1, double angle, HColor backgroundColor) {
		this.fill = backgroundColor;
		this.contact = new XPoint2D(p1.getX(), p1.getY());
		angle = manageround(angle);
		polygon.addPoint(0, 0);
		final double xWing = 19;// 8 * 2.4;
		final double yAperture = 7;// 3 * 2.4;
		polygon.addPoint(-xWing, -yAperture);
		polygon.addPoint(-xWing, yAperture);
		polygon.addPoint(0, 0);
		polygon.rotate(angle + Math.PI / 2);
		polygon = polygon.translate(p1.getX(), p1.getY());
	}

	public void drawU(UGraphic ug) {
		ug = ug.apply(fill.bg());
		ug.draw(polygon);
	}

}
