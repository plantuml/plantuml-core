package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.klimt.color.ColorParser;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.url.UrlBuilder;

public class CommandParticipantA2 extends CommandParticipant {

	public CommandParticipantA2() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandParticipantA2.class.getName(), RegexLeaf.start(), //
				getRegexType(), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("CODE", "([%pLN_.@]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("as"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("FULL", "[%g]([^%g]+)[%g]"), //
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
