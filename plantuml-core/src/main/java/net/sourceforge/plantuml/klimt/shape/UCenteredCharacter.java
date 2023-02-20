package net.sourceforge.plantuml.klimt.shape;

import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.font.UFont;

public class UCenteredCharacter implements UShape {

	private final char c;
	private final UFont font;

	public UCenteredCharacter(char c, UFont font) {
		this.c = c;
		this.font = font;
	}

	public char getChar() {
		return c;
	}

	public UFont getFont() {
		return font;
	}

}
