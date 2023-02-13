package net.sourceforge.plantuml.svek;

import java.util.EnumSet;

public enum ConditionStyle {

	EMPTY_DIAMOND, INSIDE_HEXAGON, INSIDE_DIAMOND;

	public static ConditionStyle fromString(String value) {
		if ("InsideDiamond".equalsIgnoreCase(value)) {
			return INSIDE_DIAMOND;
		}
		if ("Foo1".equalsIgnoreCase(value)) {
			return INSIDE_DIAMOND;
		}
		if ("Diamond".equalsIgnoreCase(value)) {
			return EMPTY_DIAMOND;
		}
		if ("Inside".equalsIgnoreCase(value)) {
			return INSIDE_HEXAGON;
		}
		for (ConditionStyle p : EnumSet.allOf(ConditionStyle.class)) {
			if (p.toString().equalsIgnoreCase(value)) {
				return p;
			}
		}
		return null;
	}

}
