package net.sourceforge.plantuml.klimt;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;

public class UParamNull implements UParam {

	public HColor getColor() {
		return HColors.BLACK;
	}

	public HColor getBackcolor() {
		return HColors.BLACK;
	}

	public UStroke getStroke() {
		return new UStroke();
	}

	public boolean isHidden() {
		return false;
	}

	public UPattern getPattern() {
		return UPattern.FULL;
	}
}
