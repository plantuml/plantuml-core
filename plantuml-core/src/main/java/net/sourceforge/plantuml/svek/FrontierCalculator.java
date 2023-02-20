package net.sourceforge.plantuml.svek;

import java.util.Collection;

import net.sourceforge.plantuml.cucadiagram.EntityPosition;
import net.sourceforge.plantuml.klimt.geom.ClusterPosition;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;

public class FrontierCalculator {

	private static final double DELTA = 3 * EntityPosition.RADIUS;
	private ClusterPosition core;
	private final ClusterPosition initial;

	public FrontierCalculator(final ClusterPosition initial, Collection<ClusterPosition> insides,
			Collection<XPoint2D> points) {
		this.initial = initial;
		for (ClusterPosition in : insides)
			if (core == null)
				core = in;
			else
				core = core.merge(in);

		if (core == null) {
			final XPoint2D center = initial.getPointCenter();
			core = new ClusterPosition(center.getX() - 1, center.getY() - 1, center.getX() + 1, center.getY() + 1);
		}
		for (XPoint2D p : points)
			core = core.merge(p);

		boolean touchMinX = false;
		boolean touchMaxX = false;
		boolean touchMinY = false;
		boolean touchMaxY = false;
		for (XPoint2D p : points) {
			if (p.getX() == core.getMinX())
				touchMinX = true;

			if (p.getX() == core.getMaxX())
				touchMaxX = true;

			if (p.getY() == core.getMinY())
				touchMinY = true;

			if (p.getY() == core.getMaxY())
				touchMaxY = true;

		}
		if (touchMinX == false)
			core = core.withMinX(initial.getMinX());

		if (touchMaxX == false)
			core = core.withMaxX(initial.getMaxX());

		if (touchMinY == false)
			core = core.withMinY(initial.getMinY());

		if (touchMaxY == false)
			core = core.withMaxY(initial.getMaxY());

		boolean pushMinX = false;
		boolean pushMaxX = false;
		boolean pushMinY = false;
		boolean pushMaxY = false;
		for (XPoint2D p : points) {
			if (p.getY() == core.getMinY() || p.getY() == core.getMaxY()) {
				if (Math.abs(p.getX() - core.getMaxX()) < DELTA)
					pushMaxX = true;

				if (Math.abs(p.getX() - core.getMinX()) < DELTA)
					pushMinX = true;

			}
			if (p.getX() == core.getMinX() || p.getX() == core.getMaxX()) {
				if (Math.abs(p.getY() - core.getMaxY()) < DELTA)
					pushMaxY = true;

				if (Math.abs(p.getY() - core.getMinY()) < DELTA)
					pushMinY = true;

			}
		}
		for (XPoint2D p : points) {
			if (p.getY() == core.getMinY() && (p.getX() == core.getMinX() || p.getX() == core.getMaxX()))
				pushMinY = false;

			if (p.getY() == core.getMaxY() && (p.getX() == core.getMinX() || p.getX() == core.getMaxX()))
				pushMaxY = false;

		}
		if (pushMaxX)
			core = core.addMaxX(DELTA);

		if (pushMinX)
			core = core.addMinX(-DELTA);

		if (pushMaxY)
			core = core.addMaxY(DELTA);

		if (pushMinY)
			core = core.addMinY(-DELTA);

	}

	public ClusterPosition getSuggestedPosition() {
		return core;
	}

	public void ensureMinWidth(double minWidth) {
		final double delta = core.getMaxX() - core.getMinX() - minWidth;
		if (delta < 0) {
			double newMinX = core.getMinX() + delta / 2;
			double newMaxX = core.getMaxX() - delta / 2;
			final double error = newMinX - initial.getMinX();
			if (error < 0) {
				newMinX -= error;
				newMaxX -= error;
			}
			core = core.withMinX(newMinX);
			core = core.withMaxX(newMaxX);
		}
	}

}
