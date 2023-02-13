package net.sourceforge.plantuml.ugraphic.hand;

import java.util.Random;

import net.sourceforge.plantuml.awt.geom.XCubicCurve2D;
import net.sourceforge.plantuml.klimt.DotPath;
import net.sourceforge.plantuml.klimt.UPath;

public class UDotPathHand {

	private final UPath path;

	public UDotPathHand(DotPath source, Random rnd) {

		final HandJiggle jiggle = HandJiggle.create(source.getStartPoint(), 2.0, rnd);
		for (XCubicCurve2D curve : source.getBeziers())
			jiggle.curveTo(curve);

		this.path = jiggle.toUPath();
	}

	public UPath getHanddrawn() {
		return this.path;
	}

}
