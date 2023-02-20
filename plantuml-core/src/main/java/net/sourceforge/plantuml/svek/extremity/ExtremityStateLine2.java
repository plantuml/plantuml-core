package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UEllipse;
import net.sourceforge.plantuml.klimt.shape.UPolygon;

class ExtremityStateLine2 extends Extremity {

	private UPolygon polygon = new UPolygon();
	private final XPoint2D dest;
	private final double radius = 5;
	private final double angle;

	@Override
	public XPoint2D somePoint() {
		return dest;
	}

	public ExtremityStateLine2(double angle, XPoint2D center) {
		this.angle = manageround(angle);
		polygon.addPoint(0, 0);
		this.dest = new XPoint2D(center.getX(), center.getY());
		final int xWing = 9;
		final int yAperture = 4;
		polygon.addPoint(-xWing, -yAperture);
		final int xContact = 5;
		polygon.addPoint(-xContact, 0);
		polygon.addPoint(-xWing, yAperture);
		polygon.addPoint(0, 0);
		polygon.rotate(this.angle);
		polygon = polygon.translate(center.getX(), center.getY());
	}

	public void drawU(UGraphic ug) {
		ug.apply(ug.getParam().getColor().bg())
				.apply(new UTranslate(-radius * Math.cos(angle), -radius * Math.sin(angle))).draw(polygon);
		ug.apply(new UStroke(1.5)).apply(HColors.WHITE.bg())
				.apply(new UTranslate(dest.getX() - radius, dest.getY() - radius))
				.draw(new UEllipse(radius * 2, radius * 2));
	}

}
