package net.sourceforge.plantuml.klimt.drawing.hand;

import java.util.Random;

import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.shape.ULine;

public class ULineHand {

	private UPath path;

	public ULineHand(ULine line, Random rnd) {
		final double endX = line.getDX();
		final double endY = line.getDY();
		final HandJiggle jiggle = new HandJiggle(0, 0, 2.0, rnd);
		jiggle.lineTo(endX, endY);

		this.path = jiggle.toUPath();

	}

	public UPath getHanddrawn() {
		return this.path;
	}

}
