package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.klimt.font.FontConfiguration;

class RemoveStyle implements FontChange {

	private final FontStyle style;

	RemoveStyle(FontStyle style) {
		this.style = style;
	}

	public FontConfiguration apply(FontConfiguration initial) {
		return initial.remove(style);
	}

}
