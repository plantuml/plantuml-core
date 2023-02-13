package net.sourceforge.plantuml.timingdiagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.timingdiagram.Player;
import net.sourceforge.plantuml.timingdiagram.TimingDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandDefineStateLong extends SingleLineCommand2<TimingDiagram> {

	public CommandDefineStateLong() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandDefineStateLong.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("PLAYER", "([%pLN_.@]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("has"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("LABEL", "[%g]([^%g]+)[%g]"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("as"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("STATE", "([%pLN_.@]+)"), RegexLeaf.end());
	}

	@Override
	final protected CommandExecutionResult executeArg(TimingDiagram diagram, LineLocation location, RegexResult arg) {
		final String playerCode = arg.get("PLAYER", 0);
		final Player player = diagram.getPlayer(playerCode);
		if (player == null) {
			return CommandExecutionResult.error("Unknown " + playerCode);
		}
		final String label = arg.get("LABEL", 0);
		final String stateCode = arg.get("STATE", 0);
		player.defineState(stateCode, label);

		return CommandExecutionResult.ok();
	}

}
