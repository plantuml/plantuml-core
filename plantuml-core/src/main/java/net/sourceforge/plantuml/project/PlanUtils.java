package net.sourceforge.plantuml.project;

import net.sourceforge.plantuml.project.time.Day;

public class PlanUtils {

	private PlanUtils() {

	}

	public static LoadPlanable minOf(final LoadPlanable p1, final LoadPlanable p2) {
		return new LoadPlanable() {
			public int getLoadAt(Day instant) {
				return Math.min(p1.getLoadAt(instant), p2.getLoadAt(instant));
			}
		};
	}

	public static LoadPlanable multiply(final LoadPlanable p1, final LoadPlanable p2) {
		return new LoadPlanable() {
			public int getLoadAt(Day instant) {
				return p1.getLoadAt(instant) * p2.getLoadAt(instant) / 100;
			}
		};
	}

}
