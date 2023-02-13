package net.sourceforge.plantuml.project.lang;

import java.util.Arrays;
import java.util.Collection;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.TaskInstant;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class SubjectSeparator implements Subject {

	public static final Subject ME = new SubjectSeparator();

	private SubjectSeparator() {
	}

	public IRegex toRegex() {
		return new RegexLeaf("SUBJECT", "separator");
	}

	public Failable<GanttDiagram> getMe(GanttDiagram project, RegexResult arg) {
		return Failable.ok(project);
	}

	public Collection<? extends SentenceSimple> getSentences() {
		return Arrays.asList(new JustBefore(), new JustAfter(), new Just());
	}

	class JustBefore extends SentenceSimple {

		public JustBefore() {
			super(SubjectSeparator.this, Verbs.justBefore, new ComplementDate());
		}

		@Override
		public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
			final Day day = (Day) complement;
			assert project == subject;
			project.addVerticalSeparatorBefore(day);
			return CommandExecutionResult.ok();
		}

	}

	class JustAfter extends SentenceSimple {

		public JustAfter() {
			super(SubjectSeparator.this, Verbs.justAfter, new ComplementDate());
		}

		@Override
		public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
			final Day day = (Day) complement;
			assert project == subject;
			project.addVerticalSeparatorBefore(day.increment());
			return CommandExecutionResult.ok();
		}

	}

	class Just extends SentenceSimple {

		public Just() {
			super(SubjectSeparator.this, Verbs.just, new ComplementBeforeOrAfterOrAtTaskStartOrEnd());
		}

		@Override
		public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
			final TaskInstant when = (TaskInstant) complement;

			assert project == subject;
			project.addVerticalSeparatorBefore(when.getInstantPrecise());
			return CommandExecutionResult.ok();
		}

	}

}
