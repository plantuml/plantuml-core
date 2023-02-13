package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandDecoratorMultine<D extends Diagram> implements Command<D> {

	private final SingleLineCommand2<D> cmd;
	private final boolean removeEmptyColumn;
	private final int nbMaxLines;

	public static <D extends Diagram> CommandDecoratorMultine<D> create(SingleLineCommand2<D> cmd, int nbMaxLines) {
		return new CommandDecoratorMultine<D>(cmd, false, nbMaxLines);
	}

	private CommandDecoratorMultine(SingleLineCommand2<D> cmd, boolean removeEmptyColumn, int nbMaxLines) {
		this.cmd = cmd;
		this.removeEmptyColumn = removeEmptyColumn;
		this.nbMaxLines = nbMaxLines;
	}

	public CommandExecutionResult execute(D diagram, BlocLines lines) {
		if (removeEmptyColumn)
			lines = lines.removeEmptyColumns();

		lines = lines.toSingleLineWithHiddenNewLine();
		return cmd.execute(diagram, lines);
	}

	public CommandControl isValid(BlocLines lines) {
		if (cmd.isCommandForbidden())
			return CommandControl.NOT_OK;

		lines = lines.toSingleLineWithHiddenNewLine();
		if (cmd.isForbidden(lines.getFirst().getString()))
			return CommandControl.NOT_OK;

		final CommandControl tmp = cmd.isValid(lines);
		if (tmp == CommandControl.OK_PARTIAL)
			throw new IllegalStateException();

		if (tmp == CommandControl.OK)
			return tmp;

		return CommandControl.OK_PARTIAL;
	}

	public String[] getDescription() {
		return cmd.getDescription();
	}

	public int getNbMaxLines() {
		return nbMaxLines;
	}

}
