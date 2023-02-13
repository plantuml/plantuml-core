package net.sourceforge.plantuml.sequencediagram.command;

import java.text.DecimalFormat;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandAutonumberResume extends SingleLineCommand2<SequenceDiagram> {

	public CommandAutonumberResume() {
		super(getConcat());
	}

	private static RegexConcat getConcat() {
		return RegexConcat.build(CommandAutonumberResume.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("autonumber"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("resume"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional( //
						new RegexConcat( //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("INC", "(\\d+)") //
						)), //
				new RegexOptional( //
						new RegexConcat( //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("DF", "[%g]([^%g]+)[%g]") //
						)), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		final String df = arg.get("DF", 0);

		DecimalFormat decimalFormat = null;
		if (df != null) {
			try {
				decimalFormat = new DecimalFormat(df);
			} catch (IllegalArgumentException e) {
				return CommandExecutionResult.error("Error in pattern : " + df);
			}
		}

		final String inc = arg.get("INC", 0);
		if (inc == null) {
			diagram.getAutoNumber().resume(decimalFormat);
		} else {
			diagram.getAutoNumber().resume(Integer.parseInt(inc), decimalFormat);
		}
		return CommandExecutionResult.ok();
	}

}
