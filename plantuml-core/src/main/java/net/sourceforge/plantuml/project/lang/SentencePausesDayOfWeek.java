package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.time.DayOfWeek;

public class SentencePausesDayOfWeek extends SentenceSimple {

	public SentencePausesDayOfWeek() {
		super(SubjectTask.ME, Verbs.pauses, new ComplementDayOfWeek());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task = (Task) subject;
		final DayOfWeek pause = (DayOfWeek) complement;
		task.addPause(pause);
		return CommandExecutionResult.ok();
	}

}
