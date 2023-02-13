package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.Load;
import net.sourceforge.plantuml.project.core.Task;

public class SentenceLasts extends SentenceSimple {

	public SentenceLasts() {
		super(SubjectTask.ME, Verbs.lasts, new ComplementSeveralDays());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task = (Task) subject;
		final Load duration = (Load) complement;
		task.setLoad(duration);
		return CommandExecutionResult.ok();
	}

}
