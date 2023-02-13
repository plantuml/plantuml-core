package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.ScaleMaxHeight;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandScaleMaxHeight extends SingleLineCommand2<AbstractPSystem> {

	public static final CommandScaleMaxHeight ME = new CommandScaleMaxHeight();

	private CommandScaleMaxHeight() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandScaleMaxHeight.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("scale"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("max"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("HEIGHT", "([0-9.]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("height"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(AbstractPSystem diagram, LineLocation location, RegexResult arg) {
		final double height = Double.parseDouble(arg.get("HEIGHT", 0));
		diagram.setScale(new ScaleMaxHeight(height));
		return CommandExecutionResult.ok();
	}

}
