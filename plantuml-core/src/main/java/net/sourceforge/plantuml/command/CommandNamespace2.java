package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.classdiagram.ClassDiagram;
import net.sourceforge.plantuml.cucadiagram.GroupType;
import net.sourceforge.plantuml.klimt.color.ColorParser;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.url.Url;
import net.sourceforge.plantuml.url.UrlBuilder;
import net.sourceforge.plantuml.url.UrlMode;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandNamespace2 extends SingleLineCommand2<ClassDiagram> {

	public CommandNamespace2() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandNamespace2.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("namespace"), //
				RegexLeaf.spaceOneOrMore(), //

				new RegexLeaf("[%g]"), //
				new RegexLeaf("DISPLAY", "([^%g]+)"), //
				new RegexLeaf("[%g]"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("as"), //
				RegexLeaf.spaceOneOrMore(), //

				new RegexLeaf("NAME", CommandNamespace.NAMESPACE_REGEX), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREOTYPE", "(\\<\\<.*\\>\\>)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				UrlBuilder.OPTIONAL, //
				RegexLeaf.spaceZeroOrMore(), //
				ColorParser.exp1(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\\{"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ClassDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String idShort = arg.get("NAME", 0);
		final Quark<Entity> quark = diagram.quarkInContext(diagram.cleanId(idShort), true);

		final String disp = arg.getLazzy("DISPLAY", 0);
		final Display display = Display.getWithNewlines(disp);
		final CommandExecutionResult status = diagram.gotoGroup(quark, display, GroupType.PACKAGE);
		if (status.isOk() == false)
			return status;
		final Entity p = diagram.getCurrentGroup();
		final String stereotype = arg.get("STEREOTYPE", 0);
		if (stereotype != null)
			p.setStereotype(Stereotype.build(stereotype));

		final String urlString = arg.get("URL", 0);
		if (urlString != null) {
			final UrlBuilder urlBuilder = new UrlBuilder(diagram.getSkinParam().getValue("topurl"), UrlMode.STRICT);
			final Url url = urlBuilder.getUrl(urlString);
			p.addUrl(url);
		}

		final String color = arg.get("COLOR", 0);
		if (color != null)
			p.setSpecificColorTOBEREMOVED(ColorType.BACK, diagram.getSkinParam().getIHtmlColorSet().getColor(color));

		return CommandExecutionResult.ok();
	}

}
