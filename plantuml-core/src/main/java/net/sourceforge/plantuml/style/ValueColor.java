package net.sourceforge.plantuml.style;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;

public class ValueColor extends ValueAbstract {

	private final HColor color;
	private final int priority;

	@Override
	public String toString() {
		return color.toString();
	}

	public ValueColor(HColor color, int priority) {
		this.color = color;
		this.priority = priority;
	}

	@Override
	public HColor asColor(HColorSet set) {
		return color;
	}

	@Override
	public int getPriority() {
		return priority;
	}

}
