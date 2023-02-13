package net.sourceforge.plantuml.project.time;

import java.util.Locale;

public class MonthYear implements Comparable<MonthYear> {

	private final int year;
	private final Month month;

	public static MonthYear create(int year, Month month) {
		return new MonthYear(year, month);
	}

	public String shortName(Locale locale) {
		return month.shortName(locale);
	}

	public String shortNameYYYY(Locale locale) {
		return month.shortName(locale) + " " + year;
	}

	public String longName(Locale locale) {
		return month.longName(locale);
	}

	public String longNameYYYY(Locale locale) {
		return month.longName(locale) + " " + year;
	}

	private MonthYear(int year, Month month) {
		this.year = year;
		this.month = month;
	}

	public int year() {
		return year;
	}

	public MonthYear next() {
		final Month newMonth = month.next();
		final int newYear = newMonth == Month.JANUARY ? year + 1 : year;
		return new MonthYear(newYear, newMonth);
	}

	public Month month() {
		return month;
	}

	private int internalNumber() {
		return year * 100 + month.ordinal();
	}

	@Override
	public String toString() {
		return "" + year + "/" + month;
	}

	@Override
	public int hashCode() {
		return year * 113 + month.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		final MonthYear other = (MonthYear) obj;
		return other.internalNumber() == this.internalNumber();
	}

	public int compareTo(MonthYear other) {
		return this.internalNumber() - other.internalNumber();
	}

}
