package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.shape.UDrawable;

public class MiddleFactoryCircle implements MiddleFactory {

	private final HColor backColor;

	public MiddleFactoryCircle(HColor backColor) {
		this.backColor = backColor;
	}

	public UDrawable createUDrawable(double angle) {
		return new MiddleCircle(backColor);
	}

}
