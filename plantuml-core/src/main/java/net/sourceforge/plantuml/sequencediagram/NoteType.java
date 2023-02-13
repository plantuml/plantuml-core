package net.sourceforge.plantuml.sequencediagram;

import net.sourceforge.plantuml.StringUtils;

public enum NoteType {
	NOTE, FLOATING_NOTE;

	public static NoteType defaultType(String s) {
		if (s == null) {
			return NoteType.NOTE;
		}
		return NoteType.valueOf(StringUtils.goUpperCase(s).replace(' ', '_'));
	}

}
