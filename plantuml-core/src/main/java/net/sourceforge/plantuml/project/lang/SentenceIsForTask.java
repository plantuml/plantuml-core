package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.Completion;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;

public class SentenceIsForTask extends SentenceSimple {

	public SentenceIsForTask() {
		super(SubjectTask.ME, Verbs.is, new ComplementCompleted());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task = (Task) subject;
		final Completion completed = (Completion) complement;
		task.setCompletion(completed.getCompletion());
		return CommandExecutionResult.ok();
	}

}
