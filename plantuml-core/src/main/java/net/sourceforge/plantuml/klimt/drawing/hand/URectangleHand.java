package net.sourceforge.plantuml.klimt.drawing.hand;

import java.util.Random;

import net.sourceforge.plantuml.klimt.Shadowable;
import net.sourceforge.plantuml.klimt.shape.UPolygon;
import net.sourceforge.plantuml.klimt.shape.URectangle;

public class URectangleHand {

	final private UPolygon poly;

	public URectangleHand(URectangle rectangle, Random rnd) {
		final double width = rectangle.getWidth();
		final double height = rectangle.getHeight();
		final HandJiggle jiggle;
		final double rx = Math.min(rectangle.getRx() / 2, width / 2);
		final double ry = Math.min(rectangle.getRy() / 2, height / 2);
		// System.err.println("rx=" + rx + " ry=" + ry);
		if (rx == 0 && ry == 0) {
			jiggle = new HandJiggle(0, 0, 1.5, rnd);
			jiggle.lineTo(width, 0);
			jiggle.lineTo(width, height);
			jiggle.lineTo(0, height);
			jiggle.lineTo(0, 0);
		} else {
			jiggle = new HandJiggle(rx, 0, 1.5, rnd);
			jiggle.lineTo(width - rx, 0);
			jiggle.arcTo(-Math.PI / 2, 0, width - rx, ry, rx, ry);
			jiggle.lineTo(width, height - ry);
			jiggle.arcTo(0, Math.PI / 2, width - rx, height - ry, rx, ry);
			jiggle.lineTo(rx, height);
			jiggle.arcTo(Math.PI / 2, Math.PI, rx, height - ry, rx, ry);
			jiggle.lineTo(0, ry);
			jiggle.arcTo(Math.PI, 3 * Math.PI / 2, rx, ry, rx, ry);
		}

		this.poly = jiggle.toUPolygon();
		this.poly.setDeltaShadow(rectangle.getDeltaShadow());
	}

	public Shadowable getHanddrawn() {
		return this.poly;
	}

}