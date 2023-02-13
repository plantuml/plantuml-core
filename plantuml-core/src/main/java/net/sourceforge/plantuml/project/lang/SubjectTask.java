package net.sourceforge.plantuml.project.lang;

import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;

public class SubjectTask implements Subject {

	public static final Subject ME = new SubjectTask();

	private SubjectTask() {
	}

	public Failable<Task> getMe(GanttDiagram project, RegexResult arg) {
		final String s = arg.get("SUBJECT", 0);
		final String shortName = arg.get("SUBJECT", 1);
		final String then = arg.get("THEN", 0);
		final String resource = arg.get("RESOURCE", 0);
		final Task result = project.getOrCreateTask(s, shortName, then != null);
		if (result == null) {
			throw new IllegalStateException();
		}
		if (resource != null) {
			for (final StringTokenizer st = new StringTokenizer(resource, "{}"); st.hasMoreTokens();) {
				final String part = st.nextToken().trim();
				if (part.length() > 0) {
					final boolean ok = project.affectResource(result, part);
					if (ok == false) {
						return Failable.error("Bad argument for resource");
					}
				}
			}

		}
		return Failable.ok(result);
	}

	public Collection<? extends SentenceSimple> getSentences() {
		return Arrays.asList(new SentenceLasts(), new SentenceTaskStarts(), new SentenceTaskStartsWithColor(),
				new SentenceTaskStartsAbsolute(), new SentenceHappens(), new SentenceHappensDate(), new SentenceEnds(),
				new SentenceTaskEndsAbsolute(), new SentenceIsColored(), new SentenceIsColoredForCompletion(),
				new SentenceIsDeleted(), new SentenceIsForTask(), new SentenceLinksTo(), new SentenceOccurs(),
				new SentenceDisplayOnSameRowAs(), new SentencePausesDate(), new SentencePausesDates(),
				new SentencePausesDayOfWeek());
	}

	public IRegex toRegex() {
		return new RegexConcat( //
				new RegexLeaf("THEN", "(then[%s]+)?"), //
				new RegexLeaf("SUBJECT", "\\[([^\\[\\]]+?)\\](?:[%s]+as[%s]+\\[([^\\[\\]]+?)\\])?"), //
				new RegexOptional( //
						new RegexConcat( //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("on"), //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("RESOURCE", "((?:\\{[^{}]+\\}[%s]*)+)") //
						)) //
		);
	}

}
