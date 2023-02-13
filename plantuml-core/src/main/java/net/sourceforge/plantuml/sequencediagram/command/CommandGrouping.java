package net.sourceforge.plantuml.sequencediagram.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.GroupingType;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandGrouping extends SingleLineCommand2<SequenceDiagram> {

	public CommandGrouping() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandGrouping.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("PARALLEL", "(&[%s]*)?"), //
				new RegexLeaf("TYPE", "(opt|alt|loop|par|par2|break|critical|else|end|also|group)"), //
				new RegexLeaf("COLORS", "((?<!else)(?<!also)(?<!end)#\\w+)?(?:[%s]+(#\\w+))?"), //
				new RegexOptional(//
						new RegexConcat( //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("COMMENT", "(.*?)") //
						)), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		String type = StringUtils.goLowerCase(arg.get("TYPE", 0));
		final String s = arg.get("COLORS", 0);
		final HColorSet colorSet = diagram.getSkinParam().getIHtmlColorSet();
		HColor backColorElement = null;
		if (s != null) {
			backColorElement = colorSet.getColor(s);
		}
		final String s2 = arg.get("COLORS", 1);
		HColor backColorGeneral = null;
		if (s2 != null) {
			backColorGeneral = colorSet.getColor(s2);
		}
		String comment = arg.get("COMMENT", 0);
		final GroupingType groupingType = GroupingType.getType(type);
		if ("group".equals(type)) {
			if (StringUtils.isEmpty(comment)) {
				comment = "group";
			} else {
				final Pattern p = Pattern.compile("^(.*\\[\\[.*\\]\\].*?|.*?)\\[(.*)\\]$");
				final Matcher m = p.matcher(comment);
				if (m.find()) {
					type = m.group(1);
					comment = m.group(2);
				}
			}
		}

		final boolean parallel = arg.get("PARALLEL", 0) != null;
		final boolean result = diagram.grouping(type, comment, groupingType, backColorGeneral, backColorElement,
				parallel);
		if (result == false) {
			return CommandExecutionResult.error("Cannot create group");
		}
		return CommandExecutionResult.ok();
	}
}
