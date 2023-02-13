package net.sourceforge.plantuml.project.draw;

import java.util.Locale;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.project.TimeHeaderParameters;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.project.timescale.TimeScale;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public abstract class TimeHeaderCalendar extends TimeHeader {

	private final TimeHeaderParameters thParam;

	public TimeHeaderCalendar(TimeHeaderParameters thParam, TimeScale timeScale) {
		super(thParam.getTimelineStyle(), thParam.getClosedStyle(), thParam.getMin(), thParam.getMax(), timeScale,
				thParam.getColorSet());
		this.thParam = thParam;
	}

	protected final Locale locale() {
		return thParam.getLocale();
	}

	protected final int getLoadAt(Day instant) {
		return thParam.getLoadPlanable().getLoadAt(instant);
	}

	// Duplicate in TimeHeaderSimple
	class Pending {
		final double x1;
		double x2;
		final HColor color;

		Pending(HColor color, double x1, double x2) {
			this.x1 = x1;
			this.x2 = x2;
			this.color = color;
		}

		public void draw(UGraphic ug, double height) {
			drawRectangle(ug.apply(color.bg()), height, x1, x2);
		}
	}

	protected final void drawTextsBackground(UGraphic ug, double totalHeightWithoutFooter) {

		final double height = totalHeightWithoutFooter - getFullHeaderHeight();
		Pending pending = null;

		for (Day wink = min; wink.compareTo(max) <= 0; wink = wink.increment()) {
			final double x1 = getTimeScale().getStartingPosition(wink);
			final double x2 = getTimeScale().getEndingPosition(wink);
			HColor back = thParam.getColor(wink);
			// Day of week should be stronger than period of time (back color).
			final HColor backDoW = thParam.getColor(wink.getDayOfWeek());
			if (backDoW != null)
				back = backDoW;

			if (back == null && getLoadAt(wink) == 0)
				back = closedBackgroundColor();

			if (back == null) {
				if (pending != null)
					pending.draw(ug, height);
				pending = null;
			} else {
				if (pending != null && pending.color.equals(back) == false) {
					pending.draw(ug, height);
					pending = null;
				}
				if (pending == null)
					pending = new Pending(back, x1, x2);
				else
					pending.x2 = x2;

			}
		}
	}

}
