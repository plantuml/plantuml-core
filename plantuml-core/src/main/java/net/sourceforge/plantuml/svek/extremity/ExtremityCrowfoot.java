package net.sourceforge.plantuml.svek.extremity;

import java.awt.geom.AffineTransform;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.Side;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.ULine;

class ExtremityCrowfoot extends Extremity {

	private final XPoint2D contact;
	private double angle;
	private final Side side;

	@Override
	public XPoint2D somePoint() {
		return contact;
	}

	public ExtremityCrowfoot(XPoint2D p1, double angle, Side side) {
		this.contact = new XPoint2D(p1.getX(), p1.getY());
		this.angle = manageround(angle + Math.PI / 2);
		this.side = side;
	}

	public void drawU(UGraphic ug) {
		final int xWing = 8;
		final int yAperture = 8;
		final AffineTransform rotate = AffineTransform.getRotateInstance(this.angle);
		XPoint2D middle = new XPoint2D(0, 0);
		XPoint2D left = new XPoint2D(0, -yAperture);
		XPoint2D base = new XPoint2D(-xWing, 0);
		XPoint2D right = new XPoint2D(0, yAperture);
		left = left.transform(rotate);
		base = base.transform(rotate);
		right = right.transform(rotate);

		if (side == Side.WEST || side == Side.EAST) {
			left = new XPoint2D(middle.getX(), left.getY());
			right = new XPoint2D(middle.getX(), right.getY());
		}
		if (side == Side.SOUTH || side == Side.NORTH) {
			left = new XPoint2D(left.getX(), middle.getY());
			right = new XPoint2D(right.getX(), middle.getY());
		}

		drawLine(ug, contact.getX(), contact.getY(), base, left);
		drawLine(ug, contact.getX(), contact.getY(), base, right);
		drawLine(ug, contact.getX(), contact.getY(), base, middle);
	}

	static private void drawLine(UGraphic ug, double x, double y, XPoint2D p1, XPoint2D p2) {
		final double dx = p2.getX() - p1.getX();
		final double dy = p2.getY() - p1.getY();
		ug.apply(new UTranslate(x + p1.getX(), y + p1.getY())).draw(new ULine(dx, dy));

	}

}
