package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandSkinParamMultilines extends CommandMultilinesBracket<TitledDiagram> {

	public static final CommandSkinParamMultilines ME = new CommandSkinParamMultilines();

	private CommandSkinParamMultilines() {
		super("^skinparam[%s]*(?:[%s]+([\\w.]*(?:\\<\\<.*\\>\\>)?[\\w.]*))?[%s]*\\{$");
	}

	@Override
	protected boolean isLineConsistent(String line, int level) {
		line = StringUtils.trin(line);
		if (hasStartingQuote(line))
			return true;

		return SkinLoader.p1.matcher(line).matches();
	}

	private boolean hasStartingQuote(CharSequence line) {
		// return MyPattern.mtches(line, "[%s]*[%q].*");
		return MyPattern.mtches(line, CommandMultilinesComment.COMMENT_SINGLE_LINE);
	}

	public CommandExecutionResult execute(TitledDiagram diagram, BlocLines lines) {
		final SkinLoader skinLoader = new SkinLoader(diagram);

		final Matcher2 mStart = getStartingPattern().matcher(lines.getFirst().getTrimmed().getString());
		if (mStart.find() == false)
			throw new IllegalStateException();

		final String group1 = mStart.group(1);

		return skinLoader.execute(lines, group1);

	}

}
