package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.LifeEventType;
import net.sourceforge.plantuml.sequencediagram.Participant;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandActivate extends SingleLineCommand2<SequenceDiagram> {

	public CommandActivate() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandActivate.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("TYPE", "(activate|deactivate|destroy|create)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("WHO", "([%pLN_.@]+|[%g][^%g]+[%g])"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("BACK", "(#\\w+)?"), //
				new RegexOptional( //
						new RegexConcat( //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("LINE", "(#\\w+)") //
						)), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final LifeEventType type = LifeEventType.valueOf(StringUtils.goUpperCase(arg.get("TYPE", 0)));
		final Participant p = diagram
				.getOrCreateParticipant(StringUtils.eventuallyRemoveStartingAndEndingDoubleQuote(arg.get("WHO", 0)));
		final String back = arg.get("BACK", 0);
		final HColor backColor = back == null ? null
				: diagram.getSkinParam().getIHtmlColorSet().getColor(back);
		final String line = arg.get("LINE", 0);
		final HColor lineColor = line == null ? null
				: diagram.getSkinParam().getIHtmlColorSet().getColor(line);
		final String error = diagram.activate(p, type, backColor, lineColor);
		if (error == null) {
			return CommandExecutionResult.ok();
		}
		return CommandExecutionResult.error(error);
	}

}
