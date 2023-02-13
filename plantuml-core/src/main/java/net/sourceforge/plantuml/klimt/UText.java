package net.sourceforge.plantuml.klimt;

import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class UText implements UShape {

	private final String text;
	private final FontConfiguration font;
	private final int orientation;

	@Override
	public String toString() {
		return "UText[" + text + "]";
	}

	private UText(String text, FontConfiguration font, int orientation) {
		assert text.indexOf('\t') == -1;
		this.text = text;
		this.font = font;
		this.orientation = orientation;
	}

	public UText(String text, FontConfiguration font) {
		this(text, font, 0);
	}

	public UText withOrientation(int orientation) {
		return new UText(text, font, orientation);
	}

	public String getText() {
		return text;
	}

	public FontConfiguration getFontConfiguration() {
		return font;
	}

	public double getDescent(StringBounder stringBounder) {
		return stringBounder.getDescent(font.getFont(), text);
	}

	public final int getOrientation() {
		return orientation;
	}

}
