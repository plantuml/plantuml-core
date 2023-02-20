package net.sourceforge.plantuml.classdiagram.command;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.classdiagram.ClassDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.skin.VisibilityModifier;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandAddMethod extends SingleLineCommand2<ClassDiagram> {

	public CommandAddMethod() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandAddMethod.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("NAME", "([%pLN_.]+|[%g][^%g]+[%g])"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf(":"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("DATA", "(.*)"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(ClassDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String idShort = arg.get("NAME", 0);
		final Quark<Entity> quark = diagram.quarkInContext(diagram.cleanId(idShort), false);
		Entity entity = quark.getData();
		if (entity == null)
			entity = diagram.reallyCreateLeaf(quark, Display.getWithNewlines(quark), LeafType.CLASS, null);

		final String field = arg.get("DATA", 0);
		if (field.length() > 0 && VisibilityModifier.isVisibilityCharacter(field))
			diagram.setVisibilityModifierPresent(true);

		entity.getBodier().addFieldOrMethod(field);
		return CommandExecutionResult.ok();
	}
}
