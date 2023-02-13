package net.sourceforge.plantuml.flowdiagram;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.golem.TileGeometry;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandLink extends SingleLineCommand2<FlowDiagram> {

	public CommandLink() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandLink.class.getName(), RegexLeaf.start(), //
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf("ORIENTATION", "([nsew])"), //
								RegexLeaf.spaceOneOrMore() //
						)), //
				new RegexLeaf("ID_DEST", "(\\w+)"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(FlowDiagram system, LineLocation location, RegexResult arg) {
		final String idDest = arg.get("ID_DEST", 0);
		final String orientationString = arg.get("ORIENTATION", 0);
		TileGeometry orientation = TileGeometry.SOUTH;
		if (orientationString != null) {
			orientation = TileGeometry.fromString(orientationString);
		}
		system.linkSimple(orientation, idDest);
		return CommandExecutionResult.ok();
	}

}
