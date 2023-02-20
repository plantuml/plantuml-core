package net.sourceforge.plantuml.sequencediagram;

import net.sourceforge.plantuml.StringUtils;

public enum NotePosition {
	LEFT, RIGHT, OVER, OVER_SEVERAL, BOTTOM, TOP;

	public static NotePosition defaultLeft(String s) {
		if (s == null) {
			return NotePosition.LEFT;
		}
		return NotePosition.valueOf(StringUtils.goUpperCase(s));
	}

}
