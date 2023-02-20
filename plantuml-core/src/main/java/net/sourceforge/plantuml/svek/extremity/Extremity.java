package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UDrawable;

public abstract class Extremity implements UDrawable {

	protected double manageround(double angle) {
		final double deg = angle * 180.0 / Math.PI;
		if (isCloseTo(0, deg))
			return 0;

		if (isCloseTo(90, deg))
			return 90.0 * Math.PI / 180.0;

		if (isCloseTo(180, deg))
			return 180.0 * Math.PI / 180.0;

		if (isCloseTo(270, deg))
			return 270.0 * Math.PI / 180.0;

		if (isCloseTo(360, deg))
			return 0;

		return angle;
	}

	private boolean isCloseTo(double value, double variable) {
		if (Math.abs(value - variable) < 0.05)
			return true;

		return false;
	}

	public abstract XPoint2D somePoint();

	public XPoint2D isTooSmallSoGiveThePointCloserToThisOne(XPoint2D pt) {
		return null;
	}

	public UTranslate getDeltaForKal() {
		return new UTranslate();
	}

}
