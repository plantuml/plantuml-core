// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UEllipse;
import net.sourceforge.plantuml.klimt.shape.UPolygon;

class ExtremityArrowAndCircle extends Extremity {

	private UPolygon polygon = new UPolygon();
	private final XPoint2D contact;
	private final XPoint2D dest;
	private final double radius = 5;
	private final HColor backgroundColor;

	@Override
	public XPoint2D somePoint() {
		return contact;
	}

	public ExtremityArrowAndCircle(XPoint2D p1, double angle, XPoint2D center, HColor backgroundColor) {
		angle = manageround(angle);
		polygon.addPoint(0, 0);
		this.backgroundColor = backgroundColor;
		this.dest = new XPoint2D(p1.getX(), p1.getY());
		final int xWing = 9;
		final int yAperture = 4;
		polygon.addPoint(-xWing, -yAperture);
		final int xContact = 5;
		polygon.addPoint(-xContact, 0);
		polygon.addPoint(-xWing, yAperture);
		polygon.addPoint(0, 0);
		polygon.rotate(angle + Math.PI / 2);
		polygon = polygon.translate(p1.getX() + radius * Math.sin(angle), p1.getY() - radius * Math.cos(angle));
		contact = new XPoint2D(p1.getX() - xContact * Math.cos(angle + Math.PI / 2),
				p1.getY() - xContact * Math.sin(angle + Math.PI / 2));
	}

	public void drawU(UGraphic ug) {
		ug.apply(HColors.changeBack(ug)).draw(polygon);
		ug.apply(UStroke.withThickness(1.5)).apply(backgroundColor.bg())
				.apply(new UTranslate(dest.getX() - radius, dest.getY() - radius))
				.draw(UEllipse.build(radius * 2, radius * 2));
	}

}
