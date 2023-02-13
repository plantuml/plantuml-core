package net.sourceforge.plantuml.project.solver;

import net.sourceforge.plantuml.project.Value;
import net.sourceforge.plantuml.project.core.TaskAttribute;

public interface Solver {

	public Value getData(TaskAttribute attribute);

	public void setData(TaskAttribute attribute, Value value);

}
