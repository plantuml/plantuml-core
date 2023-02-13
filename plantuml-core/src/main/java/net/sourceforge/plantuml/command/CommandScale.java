package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.ScaleSimple;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandScale extends SingleLineCommand2<AbstractPSystem> {

	public static final CommandScale ME = new CommandScale();

	private CommandScale() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandScale.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("scale"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("SCALE", "([0-9.]+)"), //
				new RegexOptional( //
						new RegexConcat( //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("/"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("DIV", "([0-9.]+)") //
						)), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(AbstractPSystem diagram, LineLocation location, RegexResult arg) {
		double scale = Double.parseDouble(arg.get("SCALE", 0));
		if (scale == 0)
			return CommandExecutionResult.error("Scale cannot be zero");

		if (arg.get("DIV", 0) != null) {
			final double div = Double.parseDouble(arg.get("DIV", 0));
			if (div == 0)
				return CommandExecutionResult.error("Scale cannot be zero");

			scale /= div;
		}
		diagram.setScale(new ScaleSimple(scale));
		return CommandExecutionResult.ok();
	}

}
