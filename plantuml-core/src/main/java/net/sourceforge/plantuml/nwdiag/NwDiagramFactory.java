package net.sourceforge.plantuml.nwdiag;

import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommandFootboxIgnored;
import net.sourceforge.plantuml.command.CommonCommands;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;

public class NwDiagramFactory extends PSystemCommandFactory {

	public NwDiagramFactory(DiagramType type) {
		super(type);
	}

	@Override
	public NwDiagram createEmptyDiagram(UmlSource source, Map<String, String> skinParam) {
		return new NwDiagram(source);
	}

	@Override
	protected void initCommandsList(List<Command> cmds) {
		CommonCommands.addCommonCommands1(cmds);
		cmds.add(new CommandNwDiagInit());
		cmds.add(new CommandComment());
		cmds.add(new CommandElement());
		cmds.add(new CommandGroup());
		cmds.add(new CommandNetwork());
		cmds.add(new CommandLink());
		cmds.add(new CommandProperty());
		cmds.add(new CommandEndSomething());
		cmds.add(new CommandFootboxIgnored());
	}

}
