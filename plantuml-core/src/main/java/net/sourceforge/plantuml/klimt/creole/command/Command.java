package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.creole.legacy.StripeSimple;

public interface Command {

	public String startingChars();

	public int matchingSize(String line);

	public String executeAndGetRemaining(String line, StripeSimple stripe);
}
