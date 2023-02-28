package net.sourceforge.plantuml.klimt.drawing.hand;

import java.util.Random;

import net.sourceforge.plantuml.klimt.Shadowable;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UEllipse;
import net.sourceforge.plantuml.klimt.shape.UPolygon;

public class UEllipseHand {

	private Shadowable poly;
	private final Random rnd;

	private double randomMe() {
		return rnd.nextDouble();
	}

	public UEllipseHand(UEllipse source, Random rnd) {
		this.rnd = rnd;
		if (source.getStart() != 0 || source.getExtend() != 0) {
			this.poly = source;
			return;
		}
		poly = new UPolygon();
		final double width = source.getWidth();
		final double height = source.getHeight();
		double angle = 0;
		if (width == height) {
			while (angle < Math.PI * 2) {
				angle += (10 + randomMe() * 10) * Math.PI / 180;
				final double variation = 1 + (randomMe() - 0.5) / 8;
				final double x = width / 2 + Math.cos(angle) * width * variation / 2;
				final double y = height / 2 + Math.sin(angle) * height * variation / 2;
				// final XPoint2D p = new XPoint2D(x, y);
				((UPolygon) poly).addPoint(x, y);
			}
		} else {
			while (angle < Math.PI * 2) {
				angle += Math.PI / 20;
				final XPoint2D pt = getPoint(width, height, angle);
				((UPolygon) poly).addPoint(pt.getX(), pt.getY());
			}

		}

		this.poly.setDeltaShadow(source.getDeltaShadow());
	}

	private XPoint2D getPoint(double width, double height, double angle) {
		final double x = width / 2 + Math.cos(angle) * width / 2;
		final double y = height / 2 + Math.sin(angle) * height / 2;
		final double variation = (randomMe() - 0.5) / 50;
		return new XPoint2D(x + variation * width, y + variation * height);

	}

	public Shadowable getHanddrawn() {
		return this.poly;
	}

}