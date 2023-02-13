package net.sourceforge.plantuml.project.time;

public class WeekNumberStrategy {

	private final DayOfWeek firstDayOfWeek;
	private final int minimalDaysInFirstWeek;

	public WeekNumberStrategy(DayOfWeek firstDayOfWeek, int minimalDaysInFirstWeek) {
		this.firstDayOfWeek = firstDayOfWeek;
		this.minimalDaysInFirstWeek = minimalDaysInFirstWeek;

	}

	public final int getFirstDayOfWeekAsLegacyInt() {
		return firstDayOfWeek.getLegacyJavaValue();
	}

	public final int getMinimalDaysInFirstWeek() {
		return minimalDaysInFirstWeek;
	}

	public final DayOfWeek getFirstDayOfWeek() {
		return firstDayOfWeek;
	}

}
