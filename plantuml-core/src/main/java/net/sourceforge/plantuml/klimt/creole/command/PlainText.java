// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.text.BackSlash;

public class PlainText implements HtmlCommand {

	private final String text;

	public static final PlainText TEXT_BS_BS_N = new PlainText(BackSlash.BS_BS_N);

	PlainText(String text) {
		this.text = text.replaceAll("\\\\\\[", "[").replaceAll("\\\\\\]", "]");
		if (text.indexOf(BackSlash.CHAR_NEWLINE) != -1)
			throw new IllegalArgumentException();

		if (text.length() == 0)
			throw new IllegalArgumentException();

	}

	public String getText() {
		assert text.length() > 0;
		return text;
	}

	public boolean isNewline() {
		return text.equals(BackSlash.BS_BS_N);
	}
}
