package net.sourceforge.plantuml.tim.expression;

public interface TokenIterator {

	public Token nextToken();

	public Token peekToken();

	public boolean hasMoreTokens();

}
