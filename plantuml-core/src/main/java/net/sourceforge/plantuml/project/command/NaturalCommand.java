package net.sourceforge.plantuml.project.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.lang.Sentence;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class NaturalCommand extends SingleLineCommand2<GanttDiagram> {

	private final Sentence sentence;

	public NaturalCommand(Sentence sentence) {
		super(sentence.toRegex());
		this.sentence = sentence;
	}

	@Override
	final protected CommandExecutionResult executeArg(GanttDiagram system, LineLocation location, RegexResult arg) {
		return sentence.execute(system, arg);
	}

	public static NaturalCommand create(Sentence sentence) {
		return new NaturalCommand(sentence);

	}

}
