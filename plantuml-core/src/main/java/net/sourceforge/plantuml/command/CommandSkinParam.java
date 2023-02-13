package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.style.NoStyleAvailableException;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandSkinParam extends SingleLineCommand2<TitledDiagram> {

	public static final CommandSkinParam ME = new CommandSkinParam();

	private CommandSkinParam() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandSkinParam.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("TYPE", "(skinparam|skinparamlocked)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("NAME", "([\\w.]*(?:\\<\\<.*\\>\\>)?[\\w.]*)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("VALUE", "([^{}]*)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(TitledDiagram diagram, LineLocation location, RegexResult arg) {
		try {
			diagram.setParam(arg.get("NAME", 0), arg.get("VALUE", 0));
			return CommandExecutionResult.ok();
		} catch (NoStyleAvailableException e) {
			// Logme.error(e);
			return CommandExecutionResult.error("General failure: no style available.");
		}

	}

}
