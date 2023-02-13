package net.sourceforge.plantuml.svek.extremity;

import java.awt.geom.AffineTransform;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.ULine;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class ExtremityCircleCrowfoot extends Extremity {

	private final XPoint2D contact;
	private final double angle;
	private final double radius = 4;

	@Override
	public XPoint2D somePoint() {
		return contact;
	}

	public ExtremityCircleCrowfoot(XPoint2D p1, double angle) {
		this.contact = new XPoint2D(p1.getX(), p1.getY());
		this.angle = manageround(angle + Math.PI / 2);
	}

	public void drawU(UGraphic ug) {
		final int xWing = 8;
		final int yAperture = 6;
		final AffineTransform rotate = AffineTransform.getRotateInstance(this.angle);
		XPoint2D middle = new XPoint2D(0, 0);
		XPoint2D left = new XPoint2D(0, -yAperture);
		XPoint2D base = new XPoint2D(-xWing, 0);
		XPoint2D right = new XPoint2D(0, yAperture);
		XPoint2D circleBase = new XPoint2D(-xWing - radius - 2, 0);

		left = left.transform(rotate);
		base = base.transform(rotate);
		right = right.transform(rotate);
		circleBase = circleBase.transform(rotate);

		drawLine(ug, contact.getX(), contact.getY(), base, left);
		drawLine(ug, contact.getX(), contact.getY(), base, right);
		drawLine(ug, contact.getX(), contact.getY(), base, middle);
		ug.apply(new UTranslate(contact.getX() + circleBase.getX() - radius,
				contact.getY() + circleBase.getY() - radius)).draw(new UEllipse(2 * radius, 2 * radius));
	}

	static private void drawLine(UGraphic ug, double x, double y, XPoint2D p1, XPoint2D p2) {
		final double dx = p2.getX() - p1.getX();
		final double dy = p2.getY() - p1.getY();
		ug.apply(new UTranslate(x + p1.getX(), y + p1.getY())).draw(new ULine(dx, dy));
	}

}
