package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.klimt.color.ColorParser;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.url.UrlBuilder;

public class CommandParticipantA4 extends CommandParticipant {

	public CommandParticipantA4() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandParticipantA4.class.getName(), RegexLeaf.start(), //
				getRegexType(), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("CODE", "[%g]([^%g]+)[%g]"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREO", "(\\<\\<.*\\>\\>)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				getOrderRegex(), //
				RegexLeaf.spaceZeroOrMore(), //
				UrlBuilder.OPTIONAL, //
				RegexLeaf.spaceZeroOrMore(), //
				ColorParser.exp1(), RegexLeaf.end());
	}

}
