package net.sourceforge.plantuml.flowdiagram;

import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;

public class FlowDiagramFactory extends PSystemCommandFactory {

	public FlowDiagramFactory() {
		super(DiagramType.FLOW);
	}

	@Override
	public FlowDiagram createEmptyDiagram(UmlSource source, Map<String, String> skinParam) {
		return new FlowDiagram(source);
	}

	@Override
	protected void initCommandsList(List<Command> cmds) {
		cmds.add(new CommandLineSimple());
		cmds.add(new CommandLink());
	}

}
