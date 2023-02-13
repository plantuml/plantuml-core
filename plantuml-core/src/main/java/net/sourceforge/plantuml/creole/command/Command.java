package net.sourceforge.plantuml.creole.command;

import net.sourceforge.plantuml.creole.legacy.StripeSimple;

public interface Command {

	public String startingChars();

	public int matchingSize(String line);

	public String executeAndGetRemaining(String line, StripeSimple stripe);
}
