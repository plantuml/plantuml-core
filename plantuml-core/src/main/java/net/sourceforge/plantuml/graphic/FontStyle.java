package net.sourceforge.plantuml.graphic;

import java.awt.Font;
import java.util.EnumSet;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;

public enum FontStyle {
	PLAIN, ITALIC, BOLD, UNDERLINE, STRIKE, WAVE, BACKCOLOR;

	public UFont mutateFont(UFont font) {
		if (this == PLAIN) {
			return font.withStyle(Font.PLAIN);
		}
		if (this == ITALIC) {
			return font.withStyle(font.getStyle() | Font.ITALIC);
		}
		if (this == BOLD) {
			return font.withStyle(font.getStyle() | Font.BOLD);
		}
		return font;
	}

	public String getActivationPattern() {
		if (this == PLAIN) {
			return "\\<[pP][lL][aA][iI][nN]\\>";
		}
		if (this == ITALIC) {
			return "\\<[iI]\\>";
		}
		if (this == BOLD) {
			return "\\<[bB]\\>";
		}
		if (this == UNDERLINE) {
			return "\\<[uU](?::(#[0-9a-fA-F]{6}|\\w+))?\\>";
		}
		if (this == WAVE) {
			return "\\<[wW](?::(#[0-9a-fA-F]{6}|\\w+))?\\>";
		}
		if (this == BACKCOLOR) {
			// return "\\<[bB][aA][cC][kK](?::(#[0-9a-fA-F]{6}|\\w+))?\\>";
			return "\\<[bB][aA][cC][kK](?::(#?\\w+(?:[-\\\\|/]#?\\w+)?))?\\>";
		}
		if (this == STRIKE) {
			return "\\<(?:s|S|strike|STRIKE|del|DEL)(?::(#[0-9a-fA-F]{6}|\\w+))?\\>";
		}
		return null;
	}

	public boolean canHaveExtendedColor() {
		if (this == UNDERLINE) {
			return true;
		}
		if (this == WAVE) {
			return true;
		}
		if (this == BACKCOLOR) {
			return true;
		}
		if (this == STRIKE) {
			return true;
		}
		return false;
	}

	public String getCreoleSyntax() {
		if (this == ITALIC) {
			return "//";
		}
		if (this == BOLD) {
			return "\\*\\*";
		}
		if (this == UNDERLINE) {
			return "__";
		}
		if (this == WAVE) {
			return "~~";
		}
		if (this == STRIKE) {
			return "--";
		}
		throw new UnsupportedOperationException();
	}

	public HColor getExtendedColor(String s) {
		final Matcher2 m = MyPattern.cmpile(getActivationPattern()).matcher(s);
		if (m.find() == false || m.groupCount() != 1) {
			return null;
		}
		final String color = m.group(1);
		if (color == null) {
			return null;
		}
		return HColorSet.instance().getColorOrWhite(color);
	}

	public String getDeactivationPattern() {
		if (this == PLAIN) {
			return "\\</[pP][lL][aA][iI][nN]\\>";
		}
		if (this == ITALIC) {
			return "\\</[iI]\\>";
		}
		if (this == BOLD) {
			return "\\</[bB]\\>";
		}
		if (this == UNDERLINE) {
			return "\\</[uU]\\>";
		}
		if (this == WAVE) {
			return "\\</[wW]\\>";
		}
		if (this == BACKCOLOR) {
			return "\\</[bB][aA][cC][kK]\\>";
		}
		if (this == STRIKE) {
			return "\\</(?:s|S|strike|STRIKE|del|DEL)\\>";
		}
		return null;
	}

	public static FontStyle getStyle(String line) {
		for (FontStyle style : EnumSet.allOf(FontStyle.class)) {
			if (line.matches(style.getActivationPattern()) || line.matches(style.getDeactivationPattern())) {
				return style;
			}
		}
		throw new IllegalArgumentException(line);
	}

}
