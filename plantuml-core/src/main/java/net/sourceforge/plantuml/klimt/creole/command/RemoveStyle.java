package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.FontStyle;

class RemoveStyle implements FontChange {

	private final FontStyle style;

	RemoveStyle(FontStyle style) {
		this.style = style;
	}

	public FontConfiguration apply(FontConfiguration initial) {
		return initial.remove(style);
	}

}
