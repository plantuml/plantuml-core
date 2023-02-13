package net.sourceforge.plantuml.style.parser;

public class StyleToken {

	private final StyleTokenType type;
	private final String data;

	public StyleToken(StyleTokenType type, String data) {
		this.type = type;
		this.data = data;
	}

	@Override
	public String toString() {
		return type.toString() + "[" + data + "]";
	}

	public final StyleTokenType getType() {
		return type;
	}

	public final String getData() {
		return data;
	}

}
