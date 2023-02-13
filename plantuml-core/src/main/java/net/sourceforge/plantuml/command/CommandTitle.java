package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.DisplayPositioned;
import net.sourceforge.plantuml.graphic.VerticalAlignment;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandTitle extends SingleLineCommand2<TitledDiagram> {

	public static final CommandTitle ME = new CommandTitle();

	private CommandTitle() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandTitle.class.getName(), //
				RegexLeaf.start(), //
				new RegexLeaf("title"), //
				new RegexLeaf("(?:[%s]*:[%s]*|[%s]+)"), //
				new RegexOr(//
						new RegexLeaf("TITLE1", "[%g](.*)[%g]"), //
						new RegexLeaf("TITLE2", "(.*[%pLN_.].*)")), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(TitledDiagram diagram, LineLocation location, RegexResult arg) {
		final Display s = Display.getWithNewlines(arg.getLazzy("TITLE", 0));
		diagram.setTitle(DisplayPositioned.single(s, HorizontalAlignment.CENTER, VerticalAlignment.TOP));
		return CommandExecutionResult.ok();
	}
}
