package net.sourceforge.plantuml.cucadiagram;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.svek.extremity.MiddleCircleCircledMode;
import net.sourceforge.plantuml.svek.extremity.MiddleFactory;
import net.sourceforge.plantuml.svek.extremity.MiddleFactoryCircle;
import net.sourceforge.plantuml.svek.extremity.MiddleFactoryCircleCircled;

public enum LinkMiddleDecor {

	NONE, CIRCLE, CIRCLE_CIRCLED, CIRCLE_CIRCLED1, CIRCLE_CIRCLED2;

	public MiddleFactory getMiddleFactory(HColor backColor, HColor diagramBackColor) {
		if (this == CIRCLE) {
			return new MiddleFactoryCircle(backColor);
		}
		if (this == CIRCLE_CIRCLED) {
			return new MiddleFactoryCircleCircled(MiddleCircleCircledMode.BOTH, backColor, diagramBackColor);
		}
		if (this == CIRCLE_CIRCLED1) {
			return new MiddleFactoryCircleCircled(MiddleCircleCircledMode.MODE1, backColor, diagramBackColor);
		}
		if (this == CIRCLE_CIRCLED2) {
			return new MiddleFactoryCircleCircled(MiddleCircleCircledMode.MODE2, backColor, diagramBackColor);
		}
		throw new UnsupportedOperationException();
	}

	public LinkMiddleDecor getInversed() {
		if (this == CIRCLE_CIRCLED1) {
			return CIRCLE_CIRCLED2;
		} else if (this == CIRCLE_CIRCLED2) {
			return CIRCLE_CIRCLED1;
		}
		return this;
	}

}
