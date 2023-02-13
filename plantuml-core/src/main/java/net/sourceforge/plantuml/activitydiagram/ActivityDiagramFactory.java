package net.sourceforge.plantuml.activitydiagram;

import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.activitydiagram.command.CommandElse;
import net.sourceforge.plantuml.activitydiagram.command.CommandEndPartition;
import net.sourceforge.plantuml.activitydiagram.command.CommandEndif;
import net.sourceforge.plantuml.activitydiagram.command.CommandIf;
import net.sourceforge.plantuml.activitydiagram.command.CommandLinkActivity;
import net.sourceforge.plantuml.activitydiagram.command.CommandLinkLongActivity;
import net.sourceforge.plantuml.activitydiagram.command.CommandPartition;
import net.sourceforge.plantuml.classdiagram.command.CommandHideShow2;
import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommandFootboxIgnored;
import net.sourceforge.plantuml.command.CommandRankDir;
import net.sourceforge.plantuml.command.CommonCommands;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.command.note.CommandFactoryNoteActivity;
import net.sourceforge.plantuml.command.note.CommandFactoryNoteOnLink;
import net.sourceforge.plantuml.core.UmlSource;

public class ActivityDiagramFactory extends PSystemCommandFactory {

	@Override
	public ActivityDiagram createEmptyDiagram(UmlSource source, Map<String, String> skinParam) {
		return new ActivityDiagram(source, skinParam);
	}

	@Override
	protected void initCommandsList(List<Command> cmds) {
		cmds.add(new CommandFootboxIgnored());
		CommonCommands.addCommonCommands1(cmds);
		cmds.add(new CommandRankDir());

		cmds.add(new CommandPartition());
		cmds.add(new CommandEndPartition());
		cmds.add(new CommandLinkLongActivity());

		final CommandFactoryNoteActivity factoryNoteActivityCommand = new CommandFactoryNoteActivity();
		cmds.add(factoryNoteActivityCommand.createSingleLine());
		cmds.add(factoryNoteActivityCommand.createMultiLine(false));

		final CommandFactoryNoteOnLink factoryNoteOnLinkCommand = new CommandFactoryNoteOnLink();
		cmds.add(factoryNoteOnLinkCommand.createSingleLine());
		cmds.add(factoryNoteOnLinkCommand.createMultiLine(false));

		cmds.add(new CommandIf());
		cmds.add(new CommandElse());
		cmds.add(new CommandEndif());

		cmds.add(new CommandLinkActivity());
		cmds.add(new CommandHideShow2());
		// addCommand(new CommandInnerConcurrent(system));

	}

}
