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

public class CommandLineSimple extends SingleLineCommand2<FlowDiagram> {

	public CommandLineSimple() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandLineSimple.class.getName(), RegexLeaf.start(), //
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf("ORIENTATION", "([nsew])"), //
								RegexLeaf.spaceOneOrMore() //
						)), //
				new RegexLeaf("ID_DEST", "(\\w+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("LABEL", "[%g](.*)[%g]"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(FlowDiagram diagram, LineLocation location, RegexResult arg) {
		final String idDest = arg.get("ID_DEST", 0);
		final String label = arg.get("LABEL", 0);
		final String orientationString = arg.get("ORIENTATION", 0);
		TileGeometry orientation = TileGeometry.SOUTH;
		if (orientationString != null) {
			orientation = TileGeometry.fromString(orientationString);
		}
		diagram.lineSimple(orientation, idDest, label);
		return CommandExecutionResult.ok();
	}

}
