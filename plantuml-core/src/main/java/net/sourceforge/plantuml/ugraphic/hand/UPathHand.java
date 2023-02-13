package net.sourceforge.plantuml.ugraphic.hand;

import java.util.Random;

import net.sourceforge.plantuml.awt.geom.XCubicCurve2D;
import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.geom.USegment;
import net.sourceforge.plantuml.klimt.geom.USegmentType;

public class UPathHand {

	private final UPath path;
	private final double defaultVariation = 4.0;

	public UPathHand(UPath source, Random rnd) {

		final UPath result = new UPath();

		XPoint2D last = new XPoint2D();

		for (USegment segment : source) {
			final USegmentType type = segment.getSegmentType();
			if (type == USegmentType.SEG_MOVETO) {
				final double x = segment.getCoord()[0];
				final double y = segment.getCoord()[1];
				result.moveTo(x, y);
				last = new XPoint2D(x, y);
			} else if (type == USegmentType.SEG_CUBICTO) {
				final double x2 = segment.getCoord()[4];
				final double y2 = segment.getCoord()[5];
				final HandJiggle jiggle = HandJiggle.create(last, 2.0, rnd);

				final XCubicCurve2D tmp = new XCubicCurve2D(last.getX(), last.getY(), segment.getCoord()[0],
						segment.getCoord()[1], segment.getCoord()[2], segment.getCoord()[3], x2, y2);
				jiggle.curveTo(tmp);
				jiggle.appendTo(result);
				last = new XPoint2D(x2, y2);
			} else if (type == USegmentType.SEG_LINETO) {
				final double x = segment.getCoord()[0];
				final double y = segment.getCoord()[1];
				final HandJiggle jiggle = new HandJiggle(last.getX(), last.getY(), defaultVariation, rnd);
				jiggle.lineTo(x, y);
				for (USegment seg2 : jiggle.toUPath())
					if (seg2.getSegmentType() == USegmentType.SEG_LINETO)
						result.lineTo(seg2.getCoord()[0], seg2.getCoord()[1]);

				last = new XPoint2D(x, y);
			} else if (type == USegmentType.SEG_ARCTO) {
				final double x = segment.getCoord()[5];
				final double y = segment.getCoord()[6];
				result.lineTo(x, y);
				last = new XPoint2D(x, y);
			} else {
				this.path = source;
				return;
			}
		}
		this.path = result;
		this.path.setDeltaShadow(source.getDeltaShadow());
	}

	public UPath getHanddrawn() {
		return this.path;
	}

}
