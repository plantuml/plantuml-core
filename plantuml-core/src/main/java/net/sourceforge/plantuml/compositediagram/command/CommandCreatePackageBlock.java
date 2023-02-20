package net.sourceforge.plantuml.compositediagram.command;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.compositediagram.CompositeDiagram;
import net.sourceforge.plantuml.cucadiagram.GroupType;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandCreatePackageBlock extends SingleLineCommand2<CompositeDiagram> {

	public CommandCreatePackageBlock() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandCreatePackageBlock.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("block"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf("DISPLAY", "[%g]([^%g]+)[%g]"), //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("as"), //
								RegexLeaf.spaceOneOrMore() //
						)), //
				new RegexLeaf("CODE", "([%pLN_.]+)"), //
				new RegexLeaf("(?:[%s]*\\{|[%s]+begin)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(CompositeDiagram diagram, LineLocation location, RegexResult arg) {

		String display = arg.get("DISPLAY", 0);
		final String idShort = arg.get("CODE", 0);
		final Quark<Entity> quark = diagram.quarkInContext(idShort, false);
		if (display == null)
			display = quark.getName();

		return diagram.gotoGroup(quark, Display.getWithNewlines(display), GroupType.PACKAGE);
	}

}
