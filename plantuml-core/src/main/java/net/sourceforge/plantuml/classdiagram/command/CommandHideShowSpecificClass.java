package net.sourceforge.plantuml.classdiagram.command;

import net.sourceforge.plantuml.baraye.CucaDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandHideShowSpecificClass extends SingleLineCommand2<CucaDiagram> {

	public CommandHideShowSpecificClass() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandHideShowSpecificClass.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("COMMAND", "(hide|show)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("CODE", "(" + CommandCreateClass.CODE + ")"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(CucaDiagram diagram, LineLocation location, RegexResult arg) {

		// final String codeString = arg.get("CODE", 0);
		// if (codeString.equals("class")) {
		// diagram.hideOrShow(LeafType.CLASS, arg.get("COMMAND",
		// 0).equalsIgnoreCase("show"));
		// } else if (codeString.equals("interface")) {
		// diagram.hideOrShow(LeafType.INTERFACE, arg.get("COMMAND",
		// 0).equalsIgnoreCase("show"));
		// } else {
		// final Code code = diagram.buildCode(codeString);
		// IEntity hidden = diagram.getEntityFactory().getLeafsget(code);
		// if (hidden == null) {
		// hidden = diagram.getEntityFactory().getGroupsget(code);
		// }
		// if (hidden == null) {
		// return CommandExecutionResult.error("Class/Package does not exist : " +
		// code.getFullName());
		// }
		// diagram.hideOrShow(hidden, arg.get("COMMAND", 0).equalsIgnoreCase("show"));
		// }
		// return CommandExecutionResult.ok();
		throw new UnsupportedOperationException();
	}
}
