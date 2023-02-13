package net.sourceforge.plantuml.svek;

import java.util.EnumSet;

public enum ConditionEndStyle {

	DIAMOND, HLINE;

	public static ConditionEndStyle fromString(String value) {
		for (ConditionEndStyle p : EnumSet.allOf(ConditionEndStyle.class)) {
			if (p.toString().equalsIgnoreCase(value)) {
				return p;
			}
		}
		return null;
	}
}
