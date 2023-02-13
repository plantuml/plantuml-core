package net.sourceforge.plantuml.tim.expression;

import net.sourceforge.plantuml.json.JsonValue;

public class Token {

	private final String surface;
	private final JsonValue json;
	private final TokenType tokenType;

	@Override
	public String toString() {
		return tokenType + "{" + surface + "}";
	}

	public Token(char surface, TokenType tokenType, JsonValue json) {
		this("" + surface, tokenType, json);
	}

	public Token(String surface, TokenType tokenType, JsonValue json) {
		this.surface = surface;
		this.tokenType = tokenType;
		this.json = json;
	}

	public TokenOperator getTokenOperator() {
		if (this.tokenType != TokenType.OPERATOR)
			throw new IllegalStateException();

		final char ch2 = surface.length() > 1 ? surface.charAt(1) : 0;
		return TokenOperator.getTokenOperator(surface.charAt(0), ch2);
	}

	public final String getSurface() {
		return surface;
	}

	public final TokenType getTokenType() {
		return tokenType;
	}

	public Token muteToFunction() {
		if (this.tokenType != TokenType.PLAIN_TEXT)
			throw new IllegalStateException();

		return new Token(surface, TokenType.FUNCTION_NAME, null);
	}

	public JsonValue getJson() {
		if (this.tokenType != TokenType.JSON_DATA)
			throw new IllegalStateException();

		return json;
	}

}
