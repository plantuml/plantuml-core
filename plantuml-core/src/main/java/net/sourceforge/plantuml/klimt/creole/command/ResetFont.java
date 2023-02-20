package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.font.FontConfiguration;

class ResetFont implements FontChange {

	public FontConfiguration apply(FontConfiguration initial) {
		return initial.resetFont();
	}

}
