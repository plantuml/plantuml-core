package net.sourceforge.plantuml.command.note;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.classdiagram.AbstractEntityDiagram;
import net.sourceforge.plantuml.classdiagram.command.CommandCreateClassMultilines;
import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.CommandMultilines2;
import net.sourceforge.plantuml.command.MultilinesStrategy;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.Trim;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.klimt.color.ColorParser;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.stereo.Stereotag;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.utils.BlocLines;
import net.sourceforge.plantuml.utils.LineLocation;

public final class CommandFactoryNote implements SingleMultiFactoryCommand<AbstractEntityDiagram> {

	private IRegex getRegexConcatMultiLine() {
		return RegexConcat.build(CommandFactoryNote.class.getName() + "multi", RegexLeaf.start(), //
				new RegexLeaf("note"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("as"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("CODE", "([%pLN_.]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("TAGS", Stereotag.pattern() + "?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREO", "(\\<\\<.*\\>\\>)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				ColorParser.exp1(), //
				RegexLeaf.end() //
		);
	}

	private IRegex getRegexConcatSingleLine() {
		return RegexConcat.build(CommandFactoryNote.class.getName() + "single", RegexLeaf.start(), //
				new RegexLeaf("note"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("[%g]"), //
				new RegexLeaf("DISPLAY", "([^%g]+)"), //
				new RegexLeaf("[%g]"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("as"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("CODE", "([%pLN_.]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("TAGS", Stereotag.pattern() + "?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREO", "(\\<\\<.*\\>\\>)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				ColorParser.exp1(), //
				RegexLeaf.end() //
		);

	}

	public Command<AbstractEntityDiagram> createSingleLine() {
		return new SingleLineCommand2<AbstractEntityDiagram>(getRegexConcatSingleLine()) {

			@Override
			protected CommandExecutionResult executeArg(final AbstractEntityDiagram system, LineLocation location,
					RegexResult arg) throws NoSuchColorException {
				final String display = arg.get("DISPLAY", 0);
				return executeInternal(system, arg, BlocLines.getWithNewlines(display));
			}

		};
	}

	public Command<AbstractEntityDiagram> createMultiLine(boolean withBracket) {
		return new CommandMultilines2<AbstractEntityDiagram>(getRegexConcatMultiLine(),
				MultilinesStrategy.KEEP_STARTING_QUOTE, Trim.BOTH) {

			@Override
			public String getPatternEnd() {
				return "^[%s]*end[%s]?note$";
			}

			protected CommandExecutionResult executeNow(final AbstractEntityDiagram system, BlocLines lines)
					throws NoSuchColorException {
				// StringUtils.trim(lines, false);
				final RegexResult line0 = getStartingPattern().matcher(lines.getFirst().getTrimmed().getString());
				lines = lines.subExtract(1, 1);
				lines = lines.removeEmptyColumns();
				return executeInternal(system, line0, lines);
			}
		};
	}

	private CommandExecutionResult executeInternal(AbstractEntityDiagram diagram, RegexResult arg, BlocLines display)
			throws NoSuchColorException {
		final String idShort = arg.get("CODE", 0);
		final Quark<Entity> quark = diagram.quarkInContext(diagram.cleanId(idShort), false);

		if (quark.getData() != null)
			return CommandExecutionResult.error("Note already created: " + quark.getName());

		final Entity entity = diagram.reallyCreateLeaf(quark, display.toDisplay(), LeafType.NOTE, null);

		assert entity != null;
		final String s = arg.get("COLOR", 0);
		entity.setSpecificColorTOBEREMOVED(ColorType.BACK,
				s == null ? null : diagram.getSkinParam().getIHtmlColorSet().getColor(s));
		final String stereotypeString = arg.get("STEREO", 0);
		Stereotype stereotype = null;
		if (stereotypeString != null) {
			stereotype = Stereotype.build(stereotypeString);
			entity.setStereotype(stereotype);
		}

		CommandCreateClassMultilines.addTags(entity, arg.get("TAGS", 0));
		return CommandExecutionResult.ok();
	}

}
