package net.sourceforge.plantuml.svek.image;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ConnectedCircle implements UDrawable {

	private final double radius;
	private final List<Double> angles = new ArrayList<>();
	private final List<XPoint2D> points = new ArrayList<>();

	public ConnectedCircle(double radius) {
		this.radius = radius;
	}

	public void drawU(UGraphic ug) {
		final UEllipse circle = new UEllipse(2 * radius, 2 * radius);
		// ug.draw(circle);
		for (Double angle : angles) {
			final double delta = 30;
			final UEllipse part = new UEllipse(2 * radius, 2 * radius, angle - delta, 2 * delta);
			ug.draw(part);
		}
		ug = ug.apply(HColors.GREEN).apply(HColors.GREEN.bg());
		for (XPoint2D pt : points) {
			final UTranslate tr = new UTranslate(pt);
			// ug.apply(tr).draw(new UEllipse(2, 2));
		}

	}

	public void addSecondaryConnection(XPoint2D pt) {
		points.add(pt);
		// double angle = Math.atan2(pt.getY() - radius, pt.getX() - radius);
		// double angle = Math.atan2(pt.getX() - radius, pt.getY() - radius);
		double angle = Math.atan2(radius - pt.getY(), pt.getX() - radius);
		angle = angle * 180.0 / Math.PI;
		System.err.println("pt1=" + pt + " " + angle);
		angles.add(angle);

	}

}
