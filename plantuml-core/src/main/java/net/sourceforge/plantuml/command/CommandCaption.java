package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.cucadiagram.DisplayPositioned;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.VerticalAlignment;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandCaption extends SingleLineCommand2<TitledDiagram> {

	public static final CommandCaption ME = new CommandCaption();

	private CommandCaption() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandCaption.class.getName(), //
				RegexLeaf.start(), //
				new RegexLeaf("caption"), //
				new RegexLeaf("(?:[%s]*:[%s]*|[%s]+)"), //
				new RegexOr(//
						new RegexLeaf("DISPLAY1", "[%g](.*)[%g]"), //
						new RegexLeaf("DISPLAY2", "(.*[%pLN_.].*)")), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(TitledDiagram diagram, LineLocation location, RegexResult arg) {
		final Display s = Display.getWithNewlines(arg.getLazzy("DISPLAY", 0));
		diagram.setCaption(DisplayPositioned.single(s, HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM));
		return CommandExecutionResult.ok();
	}

}
