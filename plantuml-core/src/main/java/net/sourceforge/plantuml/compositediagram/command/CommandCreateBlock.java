package net.sourceforge.plantuml.compositediagram.command;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.compositediagram.CompositeDiagram;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandCreateBlock extends SingleLineCommand2<CompositeDiagram> {

	public CommandCreateBlock() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandCreateBlock.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("block"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf("DISPLAY", "[%g]([^%g]+)[%g]"), //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("as"), //
								RegexLeaf.spaceOneOrMore() //
						)), //
				new RegexLeaf("CODE", "([%pLN_.]+)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(CompositeDiagram diagram, LineLocation location, RegexResult arg) {
		String display = arg.get("DISPLAY", 0);
		final String idShort = arg.get("CODE", 0);
		final Quark<Entity> quark = diagram.quarkInContext(idShort, false);
		if (display == null)
			display = quark.getName();

		if (quark.getData() != null)
			return CommandExecutionResult.error("Already exists " + quark.getName());

		final Entity ent = diagram.reallyCreateLeaf(quark, Display.getWithNewlines(quark), LeafType.BLOCK, null);

		return CommandExecutionResult.ok();
	}

}
