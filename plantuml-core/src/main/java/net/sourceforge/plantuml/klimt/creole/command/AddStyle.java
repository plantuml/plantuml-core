// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.FontStyle;

public class AddStyle implements FontChange {

	private final FontStyle style;
	private final HColor extendedColor;

	public AddStyle(FontStyle style, HColor extendedColor) {
		this.style = style;
		this.extendedColor = extendedColor;
	}

	public static AddStyle fromString(String s) {
		return new AddStyle(FontStyle.getStyle(s), FontStyle.getStyle(s).getExtendedColor(s));
	}

	public FontConfiguration apply(FontConfiguration initial) {
		initial = initial.add(style);
		if (extendedColor != null) {
			initial = initial.changeExtendedColor(extendedColor);
		}
		return initial;
	}

}
