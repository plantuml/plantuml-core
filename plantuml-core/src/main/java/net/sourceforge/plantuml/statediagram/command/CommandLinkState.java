package net.sourceforge.plantuml.statediagram.command;

import net.sourceforge.plantuml.descdiagram.command.CommandLinkElement;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;

public class CommandLinkState extends CommandLinkStateCommon {

	public CommandLinkState() {
		super(getRegex());
	}

	static RegexConcat getRegex() {
		return RegexConcat.build(CommandLinkState.class.getName(), RegexLeaf.start(), //
				getStatePattern("ENT1"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexConcat(
						//
						new RegexLeaf("ARROW_CROSS_START", "(x)?"), //
						new RegexLeaf("ARROW_BODY1", "(-+)"), //
						new RegexLeaf("ARROW_STYLE1", "(?:\\[(" + CommandLinkElement.LINE_STYLE + ")\\])?"), //
						new RegexLeaf("ARROW_DIRECTION", "(left|right|up|down|le?|ri?|up?|do?)?"), //
						new RegexLeaf("ARROW_STYLE2", "(?:\\[(" + CommandLinkElement.LINE_STYLE + ")\\])?"), //
						new RegexLeaf("ARROW_BODY2", "(-*)"), //
						new RegexLeaf("\\>"), //
						new RegexLeaf("ARROW_CIRCLE_END", "(o[%s]+)?")), //
				RegexLeaf.spaceZeroOrMore(), //
				getStatePattern("ENT2"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf(":"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("LABEL", "(.+)") //
						)), RegexLeaf.end());
	}

}
