package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.FontPosition;

class ExposantChange implements FontChange {

	private final FontPosition fontPosition;

	ExposantChange(FontPosition fontPosition) {
		this.fontPosition = fontPosition;
	}

	public FontConfiguration apply(FontConfiguration initial) {
		return initial.changeFontPosition(fontPosition);
	}

}