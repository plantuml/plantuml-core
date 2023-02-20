package net.sourceforge.plantuml.project.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.lang.CenterBorderColor;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandColorTask extends SingleLineCommand2<GanttDiagram> {

	public CommandColorTask() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandColorTask.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("CODE", "\\[([%pLN_.]+)\\]"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("COLORS", "#(\\w+)(?:/(#?\\w+))?"), //
				RegexLeaf.spaceZeroOrMore(), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {

		final String code = arg.get("CODE", 0);
		final Task task = diagram.getExistingTask(code);
		if (task == null) {
			return CommandExecutionResult.error("No such task " + code);
		}

		final String color1 = arg.get("COLORS", 0);
		final String color2 = arg.get("COLORS", 1);
		final HColor col1 = color1 == null ? null : diagram.getIHtmlColorSet().getColor(color1);
		final HColor col2 = color2 == null ? null : diagram.getIHtmlColorSet().getColor(color2);
		task.setColors(new CenterBorderColor(col1, col2));

		return CommandExecutionResult.ok();
	}

}
