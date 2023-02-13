package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.klimt.font.FontConfiguration;

class ResetFont implements FontChange {

	public FontConfiguration apply(FontConfiguration initial) {
		return initial.resetFont();
	}

}
