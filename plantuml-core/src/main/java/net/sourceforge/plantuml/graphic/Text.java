package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.text.BackSlash;

public class Text implements HtmlCommand {

	private final String text;

	public static final Text TEXT_BS_BS_N = new Text(BackSlash.BS_BS_N);

	Text(String text) {
		this.text = text.replaceAll("\\\\\\[", "[").replaceAll("\\\\\\]", "]");
		if (text.indexOf(BackSlash.CHAR_NEWLINE) != -1) {
			throw new IllegalArgumentException();
		}
		if (text.length() == 0) {
			throw new IllegalArgumentException();
		}
	}

	public String getText() {
		assert text.length() > 0;
		return text;
	}

	public boolean isNewline() {
		return text.equals(BackSlash.BS_BS_N);
	}
}
