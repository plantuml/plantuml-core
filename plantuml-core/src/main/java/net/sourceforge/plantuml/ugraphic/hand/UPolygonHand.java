package net.sourceforge.plantuml.ugraphic.hand;

import java.util.List;
import java.util.Random;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.Shadowable;
import net.sourceforge.plantuml.klimt.UPolygon;

public class UPolygonHand {

	private final UPolygon poly;

	public UPolygonHand(UPolygon source, Random rnd) {
		final List<XPoint2D> pt = source.getPoints();
		if (pt.size() == 0) {
			poly = new UPolygon();
			return;
		}
		final HandJiggle jiggle = HandJiggle.create(pt.get(0), 1.5, rnd);
		for (int i = 1; i < pt.size(); i++) {
			jiggle.lineTo(pt.get(i));
		}
		jiggle.lineTo(pt.get(0));

		this.poly = jiggle.toUPolygon();
		this.poly.setDeltaShadow(source.getDeltaShadow());
	}

	public Shadowable getHanddrawn() {
		return this.poly;
	}

}
