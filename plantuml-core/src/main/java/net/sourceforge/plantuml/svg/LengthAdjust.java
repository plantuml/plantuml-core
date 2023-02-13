package net.sourceforge.plantuml.svg;

public enum LengthAdjust {
	NONE, SPACING, SPACING_AND_GLYPHS;

	public static LengthAdjust defaultValue() {
		return SPACING;
	}
}
