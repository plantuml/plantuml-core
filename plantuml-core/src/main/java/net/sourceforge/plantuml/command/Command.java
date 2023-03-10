// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.utils.BlocLines;

public interface Command<D extends Diagram> {

	CommandExecutionResult execute(D diagram, BlocLines lines) throws NoSuchColorException;

	CommandControl isValid(BlocLines lines);

	String[] getDescription();

}
