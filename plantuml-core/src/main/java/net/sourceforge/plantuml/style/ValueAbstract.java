package net.sourceforge.plantuml.style;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;

public abstract class ValueAbstract implements Value {

	public String asString() {
		throw new UnsupportedOperationException("Class=" + getClass());
	}

	public HColor asColor(HColorSet set) {
		throw new UnsupportedOperationException("Class=" + getClass());
	}

	public int asInt(boolean minusOneIfError) {
		throw new UnsupportedOperationException("Class=" + getClass());
	}

	public double asDouble() {
		throw new UnsupportedOperationException("Class=" + getClass());
	}

	public boolean asBoolean() {
		throw new UnsupportedOperationException("Class=" + getClass());
	}

	public int asFontStyle() {
		throw new UnsupportedOperationException("Class=" + getClass());
	}

	public HorizontalAlignment asHorizontalAlignment() {
		throw new UnsupportedOperationException("Class=" + getClass());
	}

	public int getPriority() {
		throw new UnsupportedOperationException("Class=" + getClass());
	}

}
