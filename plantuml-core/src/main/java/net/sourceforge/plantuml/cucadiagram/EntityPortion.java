package net.sourceforge.plantuml.cucadiagram;

import java.util.EnumSet;
import java.util.Set;

public enum EntityPortion {
	FIELD, METHOD, MEMBER, CIRCLED_CHARACTER, STEREOTYPE;

	public Set<EntityPortion> asSet() {
		if (this == MEMBER) {
			return EnumSet.<EntityPortion>of(FIELD, METHOD);
		}
		return EnumSet.<EntityPortion>of(this);
	}
}
