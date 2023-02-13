package net.sourceforge.plantuml.svek.extremity;

import java.awt.geom.AffineTransform;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.ULine;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class ExtremityHalfArrow extends Extremity {

	private final ULine line;
	private final ULine otherLine;
	private final XPoint2D contact;

	@Override
	public XPoint2D somePoint() {
		return contact;
	}

	public ExtremityHalfArrow(XPoint2D p1, double angle, XPoint2D center) {
		angle = manageround(angle);
		final AffineTransform rotate = AffineTransform.getRotateInstance(angle + Math.PI / 2);
		final int xWing = 9;
		final int yAperture = 4;
		XPoint2D other = new XPoint2D(-xWing, -yAperture);
		other = other.transform(rotate);

		this.contact = p1;
		this.line = new ULine(center.getX() - contact.getX(), center.getY() - contact.getY());
		this.otherLine = new ULine(other.getX(), other.getY());
	}

	public ExtremityHalfArrow(XPoint2D p0, double angle) {
		throw new UnsupportedOperationException();
	}

	public void drawU(UGraphic ug) {
		ug = ug.apply(HColors.changeBack(ug));
		if (line != null && line.getLength() > 2) {
			ug.apply(new UTranslate(contact.getX(), contact.getY())).draw(line);
			ug.apply(new UTranslate(contact.getX(), contact.getY())).draw(otherLine);
		}
	}

}
