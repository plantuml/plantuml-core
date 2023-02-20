package net.sourceforge.plantuml.skin;

import java.awt.Color;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSimple;

public class SplitParam {

	private final HColor borderColor;
	private final HColor externalColor;
	private final int externalMargin;

	private static SplitParam empty() {
		return new SplitParam(null, null, 0);
	}

	public SplitParam(HColor borderColor, HColor externalColor, int externalMargin) {
		if (borderColor != null && externalMargin == 0) {
			externalMargin = 1;
		}
		this.borderColor = borderColor;
		this.externalColor = externalColor;
		this.externalMargin = externalMargin;
	}

	public boolean isSet() {
		return externalMargin > 0;
	}

	public int getExternalMargin() {
		return externalMargin;
	}

	public Color getBorderColor() {
		if (borderColor == null)
			return null;

		return ((HColorSimple) borderColor).getAwtColor();
	}

	public Color getExternalColor() {
		if (externalColor == null)
			return null;

		return ((HColorSimple) externalColor).getAwtColor();
	}

}
