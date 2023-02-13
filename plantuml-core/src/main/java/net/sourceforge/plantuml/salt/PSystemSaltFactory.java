package net.sourceforge.plantuml.salt;

import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommonCommands;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;

public class PSystemSaltFactory extends PSystemCommandFactory {

	public PSystemSaltFactory(DiagramType init) {
		super(init);
	}

	@Override
	protected void initCommandsList(List<Command> cmds) {
		if (getDiagramType() == DiagramType.UML)
			cmds.add(new CommandSalt());

		CommonCommands.addCommonCommands2(cmds);
		CommonCommands.addTitleCommands(cmds);
		cmds.add(new CommandAnything());
	}

	@Override
	public PSystemSalt createEmptyDiagram(UmlSource source, Map<String, String> skinParam) {
		final PSystemSalt result = new PSystemSalt(source);
		if (getDiagramType() == DiagramType.SALT) {
			result.setIamSalt(true);
		}
		return result;
	}

}
