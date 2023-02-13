package net.sourceforge.plantuml.compositediagram.command;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.compositediagram.CompositeDiagram;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.Link;
import net.sourceforge.plantuml.cucadiagram.LinkArg;
import net.sourceforge.plantuml.cucadiagram.LinkDecor;
import net.sourceforge.plantuml.cucadiagram.LinkType;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandLinkBlock extends SingleLineCommand2<CompositeDiagram> {

	public CommandLinkBlock() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandLinkBlock.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("ENT1", "([%pLN_.]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("DECO1", "(\\[\\]|\\*\\))?"), //
				new RegexLeaf("QUEUE", "([=-]+|\\.+)"), //
				new RegexLeaf("DECO2", "(\\[\\]|\\(\\*)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("ENT2", "([%pLN_.]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf(":"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("DISPLAY", "(\\S*+)") //
						)), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(CompositeDiagram diagram, LineLocation location, RegexResult arg) {
		final String ent1 = arg.get("ENT1", 0);
		final String ent2 = arg.get("ENT2", 0);
		final Quark<Entity> quark1 = diagram.quarkInContext(diagram.cleanId(ent1), false);
		final Quark<Entity> quark2 = diagram.quarkInContext(diagram.cleanId(ent2), false);
		final Entity cl1 = quark1.getData();
		if (cl1 == null)
			return CommandExecutionResult.error("No such element " + quark1.getName());

		final Entity cl2 = quark2.getData();
		if (cl2 == null)
			return CommandExecutionResult.error("No such element " + quark2.getName());

		final String deco1 = arg.get("DECO1", 0);
		final String deco2 = arg.get("DECO2", 0);
		LinkType linkType = new LinkType(getLinkDecor(deco1), getLinkDecor(deco2));

		// if ("*)".equals(deco1)) {
		// linkType = linkType.getInterfaceProvider();
		// } else if ("(*".equals(deco2)) {
		// linkType = linkType.getInterfaceUser();
		// }

		final String queue = arg.get("QUEUE", 0);

		final LinkArg linkArg = LinkArg.build(Display.getWithNewlines(arg.get("DISPLAY", 0)), queue.length(),
				diagram.getSkinParam().classAttributeIconSize() > 0);
		final Link link = new Link(diagram.getEntityFactory(), diagram.getSkinParam().getCurrentStyleBuilder(), cl1,
				cl2, linkType, linkArg);
		diagram.addLink(link);
		return CommandExecutionResult.ok();
	}

	private LinkDecor getLinkDecor(String s) {
		if ("[]".equals(s))
			return LinkDecor.SQUARE_toberemoved;

		return LinkDecor.NONE;
	}

}
