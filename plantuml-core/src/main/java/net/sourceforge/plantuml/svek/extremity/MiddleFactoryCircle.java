package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.color.HColor;

public class MiddleFactoryCircle implements MiddleFactory {
	
	private final HColor backColor;

	public MiddleFactoryCircle(HColor backColor) {
		this.backColor = backColor;
	}

	public UDrawable createUDrawable(double angle) {
		return new MiddleCircle(backColor);
	}

}
