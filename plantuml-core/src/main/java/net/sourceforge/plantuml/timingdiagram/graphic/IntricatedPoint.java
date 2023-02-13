package net.sourceforge.plantuml.timingdiagram.graphic;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.UTranslate;

public class IntricatedPoint {

	private final XPoint2D pta;
	private final XPoint2D ptb;

	public IntricatedPoint(XPoint2D pta, XPoint2D ptb) {
		this.pta = pta;
		this.ptb = ptb;
	}

	public final XPoint2D getPointA() {
		return pta;
	}

	public final XPoint2D getPointB() {
		return ptb;
	}

	public IntricatedPoint translated(UTranslate translate) {
		return new IntricatedPoint(translate.getTranslated(pta), translate.getTranslated(ptb));
	}

}
