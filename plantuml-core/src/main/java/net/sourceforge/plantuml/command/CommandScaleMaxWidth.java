package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.ScaleMaxWidth;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandScaleMaxWidth extends SingleLineCommand2<AbstractPSystem> {

	public static final CommandScaleMaxWidth ME = new CommandScaleMaxWidth();

	private CommandScaleMaxWidth() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandScaleMaxWidth.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("scale"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("max"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("WIDTH", "([0-9.]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("width"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(AbstractPSystem diagram, LineLocation location, RegexResult arg) {
		final double width = Double.parseDouble(arg.get("WIDTH", 0));
		diagram.setScale(new ScaleMaxWidth(width));
		return CommandExecutionResult.ok();
	}

}
