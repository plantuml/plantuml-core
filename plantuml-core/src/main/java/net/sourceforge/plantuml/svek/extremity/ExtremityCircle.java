package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.klimt.shape.UEllipse;

class ExtremityCircle extends Extremity {

	private static final double radius = 6;
	private final XPoint2D dest;
	private final boolean fill;
	private final HColor backgroundColor;

	@Override
	public XPoint2D somePoint() {
		return dest;
	}

	public static UDrawable create(XPoint2D center, boolean fill, double angle, HColor backgroundColor) {
		return new ExtremityCircle(center.getX(), center.getY(), fill, angle, backgroundColor);
	}

	private ExtremityCircle(double x, double y, boolean fill, double angle, HColor backgroundColor) {
		this.dest = new XPoint2D(x - radius * Math.cos(angle + Math.PI / 2),
				y - radius * Math.sin(angle + Math.PI / 2));
		this.backgroundColor = backgroundColor;
		this.fill = fill;
		// contact = new XPoint2D(p1.getX() - xContact * Math.cos(angle + Math.PI / 2),
		// p1.getY() - xContact
		// * Math.sin(angle + Math.PI / 2));
	}

	public void drawU(UGraphic ug) {

		ug = ug.apply(new UStroke(1.5));
		if (fill) {
			ug = ug.apply(HColors.changeBack(ug));
		} else {
			ug = ug.apply(backgroundColor.bg());
		}

		ug = ug.apply(new UTranslate(dest.getX() - radius, dest.getY() - radius));
		ug.draw(new UEllipse(radius * 2, radius * 2));
	}

}
