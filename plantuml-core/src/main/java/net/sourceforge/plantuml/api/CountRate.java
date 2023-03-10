// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.api;

public final class CountRate {

	private final MagicArray lastMinute = new MagicArray(60);
	private final MagicArray lastHour = new MagicArray(60);
	private final MagicArray lastDay = new MagicArray(140);

	public void increment() {
		final long now = System.currentTimeMillis();
		lastMinute.incKey(now / 1000L);
		lastHour.incKey(now / (60 * 1000L));
		lastDay.incKey(now / (10 * 60 * 1000L));
	}

	public void increment(int value) {
		final long now = System.currentTimeMillis();
		lastMinute.incKey(now / 1000L, value);
		lastHour.incKey(now / (60 * 1000L), value);
		lastDay.incKey(now / (10 * 60 * 1000L), value);
	}

	public long perMinute() {
		return lastMinute.getSum();
	}

	public long perHour() {
		return lastHour.getSum();
	}

	public long perDay() {
		return lastDay.getSum();
	}

	public long perMinuteMax() {
		return lastMinute.getMaxSum();
	}

	public long perHourMax() {
		return lastHour.getMaxSum();
	}

	public long perDayMax() {
		return lastDay.getMaxSum();
	}

}
