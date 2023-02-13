package net.sourceforge.plantuml.wbs;

import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommonCommands;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;

public class WBSDiagramFactory extends PSystemCommandFactory {

	public WBSDiagramFactory() {
		super(DiagramType.WBS);
	}

	@Override
	protected void initCommandsList(List<Command> cmds) {
		CommonCommands.addCommonCommands1(cmds);
		cmds.add(new CommandWBSItem(1));
		cmds.add(new CommandWBSItem(0));
		cmds.add(new CommandWBSItemMultiline());
		cmds.add(new CommandWBSLink());
	}

	@Override
	public WBSDiagram createEmptyDiagram(UmlSource source, Map<String, String> skinParam) {
		return new WBSDiagram(source);
	}

}
