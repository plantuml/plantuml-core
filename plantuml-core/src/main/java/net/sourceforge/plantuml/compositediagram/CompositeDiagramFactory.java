package net.sourceforge.plantuml.compositediagram;

import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommonCommands;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.compositediagram.command.CommandCreateBlock;
import net.sourceforge.plantuml.compositediagram.command.CommandCreatePackageBlock;
import net.sourceforge.plantuml.compositediagram.command.CommandEndPackageBlock;
import net.sourceforge.plantuml.compositediagram.command.CommandLinkBlock;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.style.ISkinSimple;

public class CompositeDiagramFactory extends PSystemCommandFactory {

	private final ISkinSimple skinParam;

	public CompositeDiagramFactory(ISkinSimple skinParam) {
		this.skinParam = skinParam;
	}

	@Override
	protected void initCommandsList(List<Command> cmds) {
		cmds.add(new CommandCreateBlock());
		cmds.add(new CommandLinkBlock());
		cmds.add(new CommandCreatePackageBlock());
		cmds.add(new CommandEndPackageBlock());
		CommonCommands.addCommonCommands1(cmds);
	}

	@Override
	public CompositeDiagram createEmptyDiagram(UmlSource source, Map<String, String> skinParam) {
		return new CompositeDiagram(source, skinParam);
	}
}
