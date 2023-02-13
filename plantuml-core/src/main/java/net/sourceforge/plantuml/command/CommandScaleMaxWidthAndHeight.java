package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.ScaleMaxWidthAndHeight;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandScaleMaxWidthAndHeight extends SingleLineCommand2<AbstractPSystem> {

	public static final CommandScaleMaxWidthAndHeight ME = new CommandScaleMaxWidthAndHeight();

	private CommandScaleMaxWidthAndHeight() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandScaleMaxWidthAndHeight.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("scale"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("max"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("WIDTH", "([0-9.]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("[*x]"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("HEIGHT", "([0-9.]+)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(AbstractPSystem diagram, LineLocation location, RegexResult arg) {
		final double width = Double.parseDouble(arg.get("WIDTH", 0));
		final double height = Double.parseDouble(arg.get("HEIGHT", 0));
		diagram.setScale(new ScaleMaxWidthAndHeight(width, height));
		return CommandExecutionResult.ok();
	}

}
