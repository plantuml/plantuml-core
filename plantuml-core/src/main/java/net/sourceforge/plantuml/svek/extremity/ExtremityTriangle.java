package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UPolygon;

class ExtremityTriangle extends Extremity {

	private UPolygon polygon = new UPolygon();
	private final boolean fill;
	private final HColor backgroundColor;
	private final XPoint2D contact;

	@Override
	public XPoint2D somePoint() {
		return contact;
	}

	public ExtremityTriangle(XPoint2D p1, double angle, boolean fill, HColor backgroundColor, int xWing,
			int yAperture) {
		this.backgroundColor = backgroundColor;
		this.fill = fill;
		this.contact = new XPoint2D(p1.getX(), p1.getY());
		angle = manageround(angle);
		polygon.addPoint(0, 0);

		polygon.addPoint(-xWing, -yAperture);
		polygon.addPoint(-xWing, yAperture);
		polygon.addPoint(0, 0);
		polygon.rotate(angle + Math.PI / 2);
		polygon = polygon.translate(p1.getX(), p1.getY());
	}

	public void drawU(UGraphic ug) {
		if (backgroundColor != null) {
			ug = ug.apply(backgroundColor.bg());
		} else if (fill) {
			ug = ug.apply(HColors.changeBack(ug));
		}
		ug.draw(polygon);
	}

}
