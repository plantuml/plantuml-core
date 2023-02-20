package net.sourceforge.plantuml.project.draw;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.project.TimeHeaderParameters;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.project.time.MonthYear;
import net.sourceforge.plantuml.project.timescale.TimeScaleCompressed;

public class TimeHeaderQuarterly extends TimeHeaderCalendar {

	public double getTimeHeaderHeight() {
		return 16 + 13;
	}

	public double getTimeFooterHeight() {
		return 16 + 13 - 1;
	}

	public TimeHeaderQuarterly(TimeHeaderParameters thParam) {
		super(thParam, new TimeScaleCompressed(thParam.getStartingDay(), thParam.getScale()));
	}

	@Override
	public void drawTimeHeader(final UGraphic ug, double totalHeightWithoutFooter) {
		drawTextsBackground(ug, totalHeightWithoutFooter);
		drawYears(ug);
		drawQuarters(ug.apply(UTranslate.dy(16)));
		drawHline(ug, 0);
		drawHline(ug, 16);
		drawHline(ug, getFullHeaderHeight());
	}

	@Override
	public void drawTimeFooter(UGraphic ug) {
		ug = ug.apply(UTranslate.dy(3));
		drawQuarters(ug);
		drawYears(ug.apply(UTranslate.dy(13)));
		drawHline(ug, 0);
		drawHline(ug, 13);
		drawHline(ug, getTimeFooterHeight());
	}

	private void drawYears(final UGraphic ug) {
		MonthYear last = null;
		double lastChange = -1;
		for (Day wink = min; wink.compareTo(max) < 0; wink = wink.increment()) {
			final double x1 = getTimeScale().getStartingPosition(wink);
			if (last == null || wink.monthYear().year() != last.year()) {
				drawVbar(ug, x1, 0, 15, false);
				if (last != null) {
					printYear(ug, last, lastChange, x1);
				}
				lastChange = x1;
				last = wink.monthYear();
			}
		}
		final double x1 = getTimeScale().getStartingPosition(max.increment());
		if (x1 > lastChange) {
			printYear(ug, last, lastChange, x1);
		}
		drawVbar(ug, getTimeScale().getEndingPosition(max), 0, 15, false);
	}

	private void drawQuarters(UGraphic ug) {
		String last = null;
		double lastChange = -1;
		for (Day wink = min; wink.compareTo(max) < 0; wink = wink.increment()) {
			final double x1 = getTimeScale().getStartingPosition(wink);
			if (quarter(wink).equals(last) == false) {
				drawVbar(ug, x1, 0, 12, false);
				if (last != null) {
					printQuarter(ug, last, lastChange, x1);
				}
				lastChange = x1;
				last = quarter(wink);
			}
		}
		final double x1 = getTimeScale().getStartingPosition(max.increment());
		if (x1 > lastChange) {
			printQuarter(ug, last, lastChange, x1);
		}
		drawVbar(ug, getTimeScale().getEndingPosition(max), 0, 12, false);
	}

	private String quarter(Day day) {
		return "Q" + ((day.month().ordinal() + 3) / 3);
	}

	private void printYear(UGraphic ug, MonthYear monthYear, double start, double end) {
		final TextBlock small = getTextBlock("" + monthYear.year(), 12, true, openFontColor());
		printCentered(ug, false, start, end, small);
	}

	private void printQuarter(UGraphic ug, String quarter, double start, double end) {
		final TextBlock small = getTextBlock(quarter, 10, false, openFontColor());
		printCentered(ug, false, start, end, small);
	}

	private void printLeft(UGraphic ug, TextBlock text, double start) {
		text.drawU(ug.apply(UTranslate.dx(start)));
	}

	@Override
	public double getFullHeaderHeight() {
		return getTimeHeaderHeight();
	}

}
