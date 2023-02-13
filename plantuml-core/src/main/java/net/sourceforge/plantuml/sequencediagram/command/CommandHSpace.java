package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandHSpace extends SingleLineCommand2<SequenceDiagram> {

	public CommandHSpace() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandHSpace.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("\\|\\|"), //
				new RegexLeaf("VALUE", "(\\d+)?"), //
				new RegexLeaf("\\|+"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		final String size = arg.get("VALUE", 0);
		if (StringUtils.isNotEmpty(size)) {
			diagram.hspace(Integer.parseInt(size));
		} else {
			diagram.hspace();
		}
		return CommandExecutionResult.ok();
	}
}
