package net.sourceforge.plantuml.project;

import net.sourceforge.plantuml.project.time.Day;

public class ConstantPlan implements LoadPlanable {

	private final int loadPerInstant;

	private ConstantPlan(int loadPerInstant) {
		this.loadPerInstant = loadPerInstant;
	}

	public static LoadPlanable normal() {
		return new ConstantPlan(100);
	}

	public static LoadPlanable partial(int load) {
		return new ConstantPlan(load);
	}

	public int getLoadAt(Day instant) {
		return loadPerInstant;

	}
}
