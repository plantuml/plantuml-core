package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.klimt.geom.Rankdir;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.skin.SkinParam;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandRankDir extends SingleLineCommand2<TitledDiagram> {

	public CommandRankDir() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandRankDir.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("DIRECTION", "(left[%s]to[%s]right|top[%s]to[%s]bottom)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("direction"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(TitledDiagram diagram, LineLocation location, RegexResult arg) {
		final String s = StringUtils.goUpperCase(arg.get("DIRECTION", 0)).replace(' ', '_');
		((SkinParam) diagram.getSkinParam()).setRankdir(Rankdir.valueOf(s));
		// diagram.setRankdir(Rankdir.valueOf(s));
		return CommandExecutionResult.ok();
	}

}
