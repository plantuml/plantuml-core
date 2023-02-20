package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.AbstractMessage;
import net.sourceforge.plantuml.sequencediagram.EventWithDeactivate;
import net.sourceforge.plantuml.sequencediagram.LifeEventType;
import net.sourceforge.plantuml.sequencediagram.Message;
import net.sourceforge.plantuml.sequencediagram.MessageExo;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.skin.ArrowBody;
import net.sourceforge.plantuml.skin.ArrowConfiguration;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandReturn extends SingleLineCommand2<SequenceDiagram> {

	public CommandReturn() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandReturn.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("PARALLEL", "(&[%s]*)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("return"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf("COLOR", "(#\\w+)"), //
								RegexLeaf.spaceOneOrMore() //
						)), //
				new RegexLeaf("MESSAGE", "(.*)"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {

		AbstractMessage message1 = diagram.getActivatingMessage();
		boolean doDeactivation = true;
		if (message1 == null) {
			final EventWithDeactivate last = diagram.getLastEventWithDeactivate();
			if (last instanceof Message == false)
				return CommandExecutionResult.error("Nowhere to return to.");

			message1 = (Message) last;
			doDeactivation = false;
		}

		ArrowConfiguration arrow = message1.getArrowConfiguration().withBody(ArrowBody.DOTTED);
		final String color = arg.get("COLOR", 0);
		if (color != null)
			arrow = arrow.withColor(HColorSet.instance().getColor(color));

		final Display display = Display.getWithNewlines(arg.get("MESSAGE", 0));
		final AbstractMessage message2;
		if (message1 instanceof MessageExo) {
			final MessageExo exo1 = (MessageExo) message1;
			message2 = new MessageExo(diagram.getSkinParam().getCurrentStyleBuilder(), exo1.getParticipant(),
					exo1.getType().reverse(), display, arrow, diagram.getNextMessageNumber(), false);
		} else {
			message2 = new Message(diagram.getSkinParam().getCurrentStyleBuilder(), message1.getParticipant2(),
					message1.getParticipant1(), display, arrow, diagram.getNextMessageNumber());
			final boolean parallel = arg.get("PARALLEL", 0) != null;
			if (parallel)
				message2.goParallel();

		}
		final CommandExecutionResult status = diagram.addMessage(message2);
		if (status.isOk() == false)
			return status;

		if (doDeactivation) {
			final String error = diagram.activate(message1.getParticipant2(), LifeEventType.DEACTIVATE, null);
			if (error != null)
				return CommandExecutionResult.error(error);

		}
		return CommandExecutionResult.ok();

	}
}
