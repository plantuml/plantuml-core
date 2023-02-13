package net.sourceforge.plantuml.project.lang;

import java.util.Collection;

import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexResult;

public interface Subject {

	public Collection<? extends SentenceSimple> getSentences();

	public IRegex toRegex();

	public Failable<? extends Object> getMe(GanttDiagram project, RegexResult arg);

}
