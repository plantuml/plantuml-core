package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class ExtremityNotNavigable extends Extremity {

	private UPath path = new UPath();
	private final XPoint2D contact;

	@Override
	public XPoint2D somePoint() {
		return contact;
	}

	public ExtremityNotNavigable(XPoint2D p1, double angle) {
		this.contact = new XPoint2D(p1.getX(), p1.getY());
		angle = manageround(angle);

		final double size = 4;
		final double move = 5;
		path.moveTo(-size, 0);
		path.lineTo(size, 2 * size);
		path.moveTo(size, 0);
		path.lineTo(-size, 2 * size);
		path = path.translate(0, move);
		path = path.rotate(angle + Math.PI);
		path = path.translate(p1.getX(), p1.getY());
	}

	public void drawU(UGraphic ug) {
		ug.draw(path);
	}

}
