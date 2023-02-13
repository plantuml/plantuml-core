package net.sourceforge.plantuml.project.solver;

import net.sourceforge.plantuml.project.Load;
import net.sourceforge.plantuml.project.LoadPlanable;
import net.sourceforge.plantuml.project.core.TaskAttribute;
import net.sourceforge.plantuml.project.time.Day;

public class SolverImpl extends AbstractSolver implements Solver {

	private final LoadPlanable loadPlanable;

	public SolverImpl(LoadPlanable loadPlanable) {
		this.loadPlanable = loadPlanable;
	}

	@Override
	protected Day computeEnd() {
		Day current = (Day) values.get(TaskAttribute.START);
		int fullLoad = ((Load) values.get(TaskAttribute.LOAD)).getFullLoad();
		int cpt = 0;
		while (fullLoad > 0) {
			fullLoad -= loadPlanable.getLoadAt(current);
			current = current.increment();
			cpt++;
			if (cpt > 100000)
				throw new IllegalStateException();

		}
		return current.decrement();
	}

	@Override
	protected Day computeStart() {
		Day current = (Day) values.get(TaskAttribute.END);
		int fullLoad = ((Load) values.get(TaskAttribute.LOAD)).getFullLoad();
		int cpt = 0;
		while (fullLoad > 0) {
			fullLoad -= loadPlanable.getLoadAt(current);
			current = current.decrement();
			if (current.getMillis() <= 0)
				return current;

			cpt++;
			if (cpt > 100000)
				throw new IllegalStateException();

		}
		return current.increment();
	}

}
