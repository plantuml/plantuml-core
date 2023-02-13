package net.sourceforge.plantuml.regexdiagram;

import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommonCommands;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;

public class PSystemRegexFactory extends PSystemCommandFactory {

	public PSystemRegexFactory() {
		super(DiagramType.REGEX);
	}

	@Override
	protected void initCommandsList(List<Command> cmds) {
		CommonCommands.addCommonCommands1(cmds);
		cmds.add(new CommandRegexfSingleLine());
	}

	@Override
	public PSystemRegex createEmptyDiagram(UmlSource source, Map<String, String> skinParam) {
		return new PSystemRegex(source);
	}

}
