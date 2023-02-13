package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.klimt.font.FontConfiguration;

class ExposantChange implements FontChange {

	private final FontPosition fontPosition;

	ExposantChange(FontPosition fontPosition) {
		this.fontPosition = fontPosition;
	}

	public FontConfiguration apply(FontConfiguration initial) {
		return initial.changeFontPosition(fontPosition);
	}

}
