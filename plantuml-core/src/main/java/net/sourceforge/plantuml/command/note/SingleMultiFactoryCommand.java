package net.sourceforge.plantuml.command.note;

import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.core.Diagram;

public interface SingleMultiFactoryCommand<D extends Diagram> {

	public Command<D> createSingleLine();

	public Command<D> createMultiLine(boolean withBracket);

}
