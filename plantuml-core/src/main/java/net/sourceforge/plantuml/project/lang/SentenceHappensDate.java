package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.Load;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.time.Day;

public class SentenceHappensDate extends SentenceSimple {

	public SentenceHappensDate() {
		super(SubjectTask.ME, Verbs.happens, new ComplementDate());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task = (Task) subject;
		task.setLoad(Load.inWinks(1));
		final Day start = (Day) complement;
//		final Day startingDate = project.getStartingDate2();
//		if (startingDate == null) {
//			return CommandExecutionResult.error("No starting date for the project");
//		}
		task.setStart(start);
		task.setDiamond(true);
		return CommandExecutionResult.ok();
	}

}
