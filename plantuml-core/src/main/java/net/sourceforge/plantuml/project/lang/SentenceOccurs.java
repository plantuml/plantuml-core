package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.GanttConstraint;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.core.TaskAttribute;
import net.sourceforge.plantuml.project.core.TaskInstant;

public class SentenceOccurs extends SentenceSimple {

	public SentenceOccurs() {
		super(SubjectTask.ME, Verbs.occurs, new ComplementFromTo());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task = (Task) subject;
		final TwoNames bothNames = (TwoNames) complement;
		final String name1 = bothNames.getName1();
		final String name2 = bothNames.getName2();
		final Task from = project.getExistingTask(name1);
		if (from == null) {
			return CommandExecutionResult.error("No such " + name1 + " task");
		}
		final Task to = project.getExistingTask(name2);
		if (to == null) {
			return CommandExecutionResult.error("No such " + name2 + " task");
		}
		task.setStart(from.getEnd());
		task.setEnd(to.getEnd());
		project.addContraint(new GanttConstraint(project.getIHtmlColorSet(), project.getCurrentStyleBuilder(),
				new TaskInstant(from, TaskAttribute.START), new TaskInstant(task, TaskAttribute.START)));
		project.addContraint(new GanttConstraint(project.getIHtmlColorSet(), project.getCurrentStyleBuilder(),
				new TaskInstant(to, TaskAttribute.END), new TaskInstant(task, TaskAttribute.END)));
		return CommandExecutionResult.ok();
	}

}
