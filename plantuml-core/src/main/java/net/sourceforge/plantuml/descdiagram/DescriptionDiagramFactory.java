// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.descdiagram;

import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.classdiagram.command.CommandHideShow2;
import net.sourceforge.plantuml.classdiagram.command.CommandNamespaceSeparator;
import net.sourceforge.plantuml.classdiagram.command.CommandRemoveRestore;
import net.sourceforge.plantuml.classdiagram.command.CommandUrl;
import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommandEndPackage;
import net.sourceforge.plantuml.command.CommandFootboxIgnored;
import net.sourceforge.plantuml.command.CommandRankDir;
import net.sourceforge.plantuml.command.CommonCommands;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.command.note.CommandFactoryNote;
import net.sourceforge.plantuml.command.note.CommandFactoryNoteOnEntity;
import net.sourceforge.plantuml.command.note.CommandFactoryNoteOnLink;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.descdiagram.command.CommandArchimate;
import net.sourceforge.plantuml.descdiagram.command.CommandArchimateMultilines;
import net.sourceforge.plantuml.descdiagram.command.CommandCreateElementFull;
import net.sourceforge.plantuml.descdiagram.command.CommandCreateElementMultilines;
import net.sourceforge.plantuml.descdiagram.command.CommandLinkElement;
import net.sourceforge.plantuml.descdiagram.command.CommandNewpage;
import net.sourceforge.plantuml.descdiagram.command.CommandPackageWithUSymbol;
import net.sourceforge.plantuml.descdiagram.command.CommandTogether;
import net.sourceforge.plantuml.objectdiagram.command.CommandCreateJson;
import net.sourceforge.plantuml.objectdiagram.command.CommandCreateJsonSingleLine;
import net.sourceforge.plantuml.objectdiagram.command.CommandCreateMap;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOr;

public class DescriptionDiagramFactory extends PSystemCommandFactory {

	@Override
	public DescriptionDiagram createEmptyDiagram(UmlSource source, Map<String, String> skinParam) {
		return new DescriptionDiagram(source, skinParam);
	}

	@Override
	protected void initCommandsList(List<Command> cmds) {
		cmds.add(new CommandFootboxIgnored());
		cmds.add(new CommandRankDir());
		cmds.add(new CommandNewpage(this));
		CommonCommands.addCommonCommands1(cmds);

		cmds.add(new CommandLinkElement());
		cmds.add(new CommandHideShow2());
		cmds.add(new CommandRemoveRestore());

		cmds.add(new CommandPackageWithUSymbol());
		cmds.add(new CommandTogether());

		cmds.add(new CommandEndPackage());
		final CommandFactoryNote factoryNoteCommand = new CommandFactoryNote();
		cmds.add(factoryNoteCommand.createMultiLine(false));

		final CommandFactoryNoteOnLink factoryNoteOnLinkCommand = new CommandFactoryNoteOnLink();
		cmds.add(factoryNoteOnLinkCommand.createSingleLine());
		cmds.add(factoryNoteOnLinkCommand.createMultiLine(false));

		final CommandFactoryNoteOnEntity factoryNoteOnEntityCommand = new CommandFactoryNoteOnEntity("desc",
				new RegexOr("ENTITY", //
						new RegexLeaf("[%pLN_.]+"), //
						new RegexLeaf("\\(\\)[%s]*[%pLN_.]+"), //
						new RegexLeaf("\\(\\)[%s]*[%g][^%g]+[%g]"), //
						new RegexLeaf("\\[[^\\]*]+[^\\]]*\\]"), //
						new RegexLeaf("\\((?!\\*\\))[^\\)]+\\)"), //
						new RegexLeaf(":[^:]+:"), //
						new RegexLeaf("[%g][^%g]+[%g]") //
				));
		cmds.add(factoryNoteOnEntityCommand.createSingleLine());

		cmds.add(factoryNoteCommand.createSingleLine());
		cmds.add(new CommandUrl());
		cmds.add(new CommandCreateElementFull());
		cmds.add(new CommandCreateElementMultilines(0));
		cmds.add(new CommandCreateElementMultilines(1));

		cmds.add(factoryNoteOnEntityCommand.createMultiLine(true));
		cmds.add(factoryNoteOnEntityCommand.createMultiLine(false));
		cmds.add(factoryNoteCommand.createMultiLine(false));

		cmds.add(new CommandCreateMap());
		cmds.add(new CommandCreateJson());
		cmds.add(new CommandCreateJsonSingleLine());
		// cmds.add(new CommandHideShowSpecificClass());

		cmds.add(new CommandArchimate());
		cmds.add(new CommandArchimateMultilines());
		cmds.add(new CommandCreateDomain());
	}

}
