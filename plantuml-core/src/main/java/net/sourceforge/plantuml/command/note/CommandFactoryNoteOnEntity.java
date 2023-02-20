package net.sourceforge.plantuml.command.note;

import net.atmp.Link;
import net.atmp.LinkArg;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.classdiagram.AbstractEntityDiagram;
import net.sourceforge.plantuml.classdiagram.command.CommandCreateClassMultilines;
import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.CommandMultilines2;
import net.sourceforge.plantuml.command.MultilinesStrategy;
import net.sourceforge.plantuml.command.Position;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.Trim;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.decoration.LinkDecor;
import net.sourceforge.plantuml.decoration.LinkType;
import net.sourceforge.plantuml.klimt.color.ColorParser;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.Colors;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.skin.ColorParam;
import net.sourceforge.plantuml.stereo.Stereotag;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.url.Url;
import net.sourceforge.plantuml.url.UrlBuilder;
import net.sourceforge.plantuml.url.UrlMode;
import net.sourceforge.plantuml.utils.BlocLines;
import net.sourceforge.plantuml.utils.LineLocation;

public final class CommandFactoryNoteOnEntity implements SingleMultiFactoryCommand<AbstractEntityDiagram> {

	private final IRegex partialPattern;
	private final String key;

	public CommandFactoryNoteOnEntity(String key, IRegex partialPattern) {
		this.partialPattern = partialPattern;
		this.key = key;
	}

	private IRegex getRegexConcatSingleLine(IRegex partialPattern) {
		return RegexConcat.build(CommandFactoryNoteOnEntity.class.getName() + key + "single", RegexLeaf.start(), //
				new RegexLeaf("note"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("POSITION", "(right|left|top|bottom)"), //
				new RegexOr(//
						new RegexConcat(RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("of"), //
								RegexLeaf.spaceOneOrMore(), partialPattern), //
						new RegexLeaf("")), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("TAGS1", Stereotag.pattern() + "?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREO", "(\\<\\<.*\\>\\>)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("TAGS2", Stereotag.pattern() + "?"), //
				RegexLeaf.spaceZeroOrMore(), //
				color().getRegex(), //
				RegexLeaf.spaceZeroOrMore(), //
				UrlBuilder.OPTIONAL, //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf(":"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("NOTE", "(.*)"), //
				RegexLeaf.end() //
		);
	}

	private static ColorParser color() {
		return ColorParser.simpleColor(ColorType.BACK);
	}

	private IRegex getRegexConcatMultiLine(IRegex partialPattern, final boolean withBracket) {
		if (withBracket) {
			return RegexConcat.build(CommandFactoryNoteOnEntity.class.getName() + key + "multi" + withBracket,
					RegexLeaf.start(), //
					new RegexLeaf("note"), //
					RegexLeaf.spaceOneOrMore(), //
					new RegexLeaf("POSITION", "(right|left|top|bottom)"), //
					new RegexOr(//
							new RegexConcat(RegexLeaf.spaceOneOrMore(), //
									new RegexLeaf("of"), //
									RegexLeaf.spaceOneOrMore(), //
									partialPattern), //
							new RegexLeaf("")), //
					RegexLeaf.spaceZeroOrMore(), //
					new RegexLeaf("TAGS1", Stereotag.pattern() + "?"), //
					RegexLeaf.spaceZeroOrMore(), //
					new RegexLeaf("STEREO", "(\\<\\<.*\\>\\>)?"), //
					RegexLeaf.spaceZeroOrMore(), //
					new RegexLeaf("TAGS2", Stereotag.pattern() + "?"), //
					RegexLeaf.spaceZeroOrMore(), //
					color().getRegex(), //
					RegexLeaf.spaceZeroOrMore(), //
					UrlBuilder.OPTIONAL, //
					RegexLeaf.spaceZeroOrMore(), //
					new RegexLeaf("\\{"), //
					RegexLeaf.end() //
			);
		}
		return RegexConcat.build(CommandFactoryNoteOnEntity.class.getName() + key + "multi" + withBracket,
				RegexLeaf.start(), //
				new RegexLeaf("note"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("POSITION", "(right|left|top|bottom)"), //
				new RegexOr(//
						new RegexConcat(RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("of"), //
								RegexLeaf.spaceOneOrMore(), //
								partialPattern), //
						new RegexLeaf("")), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("TAGS1", Stereotag.pattern() + "?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREO", "(\\<\\<.*\\>\\>)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("TAGS2", Stereotag.pattern() + "?"), //
				RegexLeaf.spaceZeroOrMore(), //
				color().getRegex(), //
				RegexLeaf.spaceZeroOrMore(), //
				UrlBuilder.OPTIONAL, //
				RegexLeaf.end() //
		);
	}

	public Command<AbstractEntityDiagram> createSingleLine() {
		return new SingleLineCommand2<AbstractEntityDiagram>(getRegexConcatSingleLine(partialPattern)) {

			@Override
			protected CommandExecutionResult executeArg(final AbstractEntityDiagram system, LineLocation location,
					RegexResult arg) throws NoSuchColorException {
				final String s = arg.get("NOTE", 0);
				return executeInternal(arg, system, null, BlocLines.getWithNewlines(s));
			}
		};
	}

	public Command<AbstractEntityDiagram> createMultiLine(final boolean withBracket) {
		return new CommandMultilines2<AbstractEntityDiagram>(getRegexConcatMultiLine(partialPattern, withBracket),
				MultilinesStrategy.KEEP_STARTING_QUOTE, Trim.BOTH) {

			@Override
			public String getPatternEnd() {
				if (withBracket)
					return "^(\\})$";

				return "^[%s]*(end[%s]?note)$";
			}

			protected CommandExecutionResult executeNow(final AbstractEntityDiagram system, BlocLines lines)
					throws NoSuchColorException {
				// StringUtils.trim(lines, false);
				final RegexResult line0 = getStartingPattern().matcher(lines.getFirst().getTrimmed().getString());
				lines = lines.subExtract(1, 1);
				lines = lines.removeEmptyColumns();

				Url url = null;
				if (line0.get("URL", 0) != null) {
					final UrlBuilder urlBuilder = new UrlBuilder(system.getSkinParam().getValue("topurl"),
							UrlMode.STRICT);
					url = urlBuilder.getUrl(line0.get("URL", 0));
				}

				return executeInternal(line0, system, url, lines);
			}
		};
	}

	private CommandExecutionResult executeInternal(RegexResult line0, AbstractEntityDiagram diagram, Url url,
			BlocLines strings) throws NoSuchColorException {
		final String pos = line0.get("POSITION", 0);
		final String idShort = diagram.cleanId(line0.get("ENTITY", 0));
		final Entity cl1;
		if (idShort == null) {
			cl1 = diagram.getLastEntity();
			if (cl1 == null)
				return CommandExecutionResult.error("Nothing to note to");

		} else {
			final Quark<Entity> quark = diagram.quarkInContext(idShort, false);
			cl1 = quark.getData();
			if (cl1 == null)
				return CommandExecutionResult.error("Not known: " + idShort);
		}

		final Position position = Position.valueOf(StringUtils.goUpperCase(pos))
				.withRankdir(diagram.getSkinParam().getRankdir());
		Colors colors = color().getColor(line0, diagram.getSkinParam().getIHtmlColorSet());

		final String stereotypeString = line0.get("STEREO", 0);
		Stereotype stereotype = null;
		if (stereotypeString != null) {
			stereotype = Stereotype.build(stereotypeString);
			colors = colors.applyStereotypeForNote(stereotype, diagram.getSkinParam(), ColorParam.noteBackground,
					ColorParam.noteBorder);
		}

		if (diagram.getPragma().useKermor() && cl1.isGroup()) {
			cl1.addNote(strings.toDisplay(), position, colors);
			return CommandExecutionResult.ok();
		}

		final String tmp = diagram.getUniqueSequence("GMN");
		final Quark<Entity> quark = diagram.quarkInContext(tmp, false);
		final Entity note = diagram.reallyCreateLeaf(quark, strings.toDisplay(), LeafType.NOTE, null);

		if (stereotypeString != null)
			note.setStereotype(stereotype);

		note.setColors(colors);
		if (url != null)
			note.addUrl(url);

		CommandCreateClassMultilines.addTags(note, line0.getLazzy("TAGS", 0));

		final Link link;

		final LinkType type = new LinkType(LinkDecor.NONE, LinkDecor.NONE).goDashed();
		if (position == Position.RIGHT) {
			link = new Link(diagram.getEntityFactory(), diagram.getSkinParam().getCurrentStyleBuilder(), cl1, note,
					type, LinkArg.noDisplay(1));
			link.setHorizontalSolitary(true);
		} else if (position == Position.LEFT) {
			link = new Link(diagram.getEntityFactory(), diagram.getSkinParam().getCurrentStyleBuilder(), note, cl1,
					type, LinkArg.noDisplay(1));
			link.setHorizontalSolitary(true);
		} else if (position == Position.BOTTOM) {
			link = new Link(diagram.getEntityFactory(), diagram.getSkinParam().getCurrentStyleBuilder(), cl1, note,
					type, LinkArg.noDisplay(2));
		} else if (position == Position.TOP) {
			link = new Link(diagram.getEntityFactory(), diagram.getSkinParam().getCurrentStyleBuilder(), note, cl1,
					type, LinkArg.noDisplay(2));
		} else {
			throw new IllegalArgumentException();
		}
		diagram.addLink(link);
		return CommandExecutionResult.ok();
	}

}
