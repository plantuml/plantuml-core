// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.statediagram;

import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.classdiagram.command.CommandHideShow2;
import net.sourceforge.plantuml.classdiagram.command.CommandNamespaceSeparator;
import net.sourceforge.plantuml.classdiagram.command.CommandRemoveRestore;
import net.sourceforge.plantuml.classdiagram.command.CommandUrl;
import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommandFootboxIgnored;
import net.sourceforge.plantuml.command.CommandRankDir;
import net.sourceforge.plantuml.command.CommonCommands;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.command.note.CommandFactoryNote;
import net.sourceforge.plantuml.command.note.CommandFactoryNoteOnEntity;
import net.sourceforge.plantuml.command.note.CommandFactoryNoteOnLink;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.objectdiagram.command.CommandCreateJson;
import net.sourceforge.plantuml.objectdiagram.command.CommandCreateJsonSingleLine;
import net.sourceforge.plantuml.objectdiagram.command.CommandCreateMap;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.statediagram.command.CommandAddField;
import net.sourceforge.plantuml.statediagram.command.CommandConcurrentState;
import net.sourceforge.plantuml.statediagram.command.CommandCreatePackage2;
import net.sourceforge.plantuml.statediagram.command.CommandCreatePackageState;
import net.sourceforge.plantuml.statediagram.command.CommandCreateState;
import net.sourceforge.plantuml.statediagram.command.CommandEndState;
import net.sourceforge.plantuml.statediagram.command.CommandLinkState;
import net.sourceforge.plantuml.statediagram.command.CommandLinkStateReverse;

public class StateDiagramFactory extends PSystemCommandFactory {

	@Override
	public StateDiagram createEmptyDiagram(UmlSource source, Map<String, String> skinParam) {
		return new StateDiagram(source, skinParam);
	}

	@Override
	protected void initCommandsList(List<Command> cmds) {
		cmds.add(new CommandFootboxIgnored());
		cmds.add(new CommandRankDir());
		cmds.add(new CommandRemoveRestore());
		cmds.add(new CommandCreateState());
		cmds.add(new CommandLinkState());
		cmds.add(new CommandLinkStateReverse());
		cmds.add(new CommandCreatePackageState());
		cmds.add(new CommandCreatePackage2());
		cmds.add(new CommandEndState());
		cmds.add(new CommandAddField());
		cmds.add(new CommandConcurrentState());

		final CommandFactoryNoteOnEntity factoryNoteOnEntityCommand = new CommandFactoryNoteOnEntity("state",
				new RegexOr("ENTITY", new RegexLeaf("[%pLN_.]+"), //
						new RegexLeaf("[%g][^%g]+[%g]") //
				));
		cmds.add(factoryNoteOnEntityCommand.createMultiLine(true));
		cmds.add(factoryNoteOnEntityCommand.createMultiLine(false));

		cmds.add(factoryNoteOnEntityCommand.createSingleLine());
		final CommandFactoryNoteOnLink factoryNoteOnLinkCommand = new CommandFactoryNoteOnLink();
		cmds.add(factoryNoteOnLinkCommand.createSingleLine());
		cmds.add(factoryNoteOnLinkCommand.createMultiLine(false));
		cmds.add(new CommandUrl());

		final CommandFactoryNote factoryNoteCommand = new CommandFactoryNote();
		cmds.add(factoryNoteCommand.createSingleLine());
		cmds.add(factoryNoteCommand.createMultiLine(false));

		cmds.add(new CommandCreateMap());
		cmds.add(new CommandCreateJson());
		cmds.add(new CommandCreateJsonSingleLine());

		CommonCommands.addCommonCommands1(cmds);
		cmds.add(new CommandHideShow2());
	}

}
