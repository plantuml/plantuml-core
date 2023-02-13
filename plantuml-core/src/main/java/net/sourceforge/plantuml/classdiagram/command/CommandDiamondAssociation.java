package net.sourceforge.plantuml.classdiagram.command;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.classdiagram.ClassDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandDiamondAssociation extends SingleLineCommand2<ClassDiagram> {

	public CommandDiamondAssociation() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandDiamondAssociation.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("\\<\\>"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("CODE", "([%pLN_.]+)"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(ClassDiagram diagram, LineLocation location, RegexResult arg) {
		final String idShort = arg.get("CODE", 0);
		final Quark<Entity> quark = diagram.quarkInContext(diagram.cleanId(idShort), false);

		if (quark.getData() != null)
			return CommandExecutionResult.error("Already existing : " + quark.getName());

		diagram.reallyCreateLeaf(quark, Display.getWithNewlines(""), LeafType.ASSOCIATION, null);

		return CommandExecutionResult.ok();
	}
}
