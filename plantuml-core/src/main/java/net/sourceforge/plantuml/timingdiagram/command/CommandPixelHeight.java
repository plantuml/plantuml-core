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

public class CommandPixelHeight extends SingleLineCommand2<TimingDiagram> {

	public CommandPixelHeight() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandPixelHeight.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("PLAYER", "([%pLN_.@]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("is"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("NUM", "([0-9]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("pixels?"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("height"), //
				RegexLeaf.end());
	}

	@Override
	final protected CommandExecutionResult executeArg(TimingDiagram diagram, LineLocation location, RegexResult arg) {
		final String code = arg.get("PLAYER", 0);
		final Player player = diagram.getPlayer(code);
		if (player == null) {
			return CommandExecutionResult.error("No such participant " + code);
		}
		player.setHeight(Integer.parseInt(arg.get("NUM", 0)));
		return CommandExecutionResult.ok();
	}

}
