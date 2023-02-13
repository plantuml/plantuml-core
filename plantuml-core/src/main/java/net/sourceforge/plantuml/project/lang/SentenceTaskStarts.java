package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.project.GanttConstraint;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.core.TaskAttribute;
import net.sourceforge.plantuml.project.core.TaskInstant;

public class SentenceTaskStarts extends SentenceSimple {

	public SentenceTaskStarts() {
		super(SubjectTask.ME, Verbs.starts2, new ComplementBeforeOrAfterOrAtTaskStartOrEnd());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task = (Task) subject;
		final TaskInstant when;
		HColor color = null;
		when = (TaskInstant) complement;
		task.setStart(when.getInstantPrecise());
		if (when.isTask()) {
			project.addContraint(new GanttConstraint(project.getIHtmlColorSet(), project.getCurrentStyleBuilder(), when,
					new TaskInstant(task, TaskAttribute.START), color));
		}
		return CommandExecutionResult.ok();

	};
}
