package net.sourceforge.plantuml.api;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NiceNumber {

	public static int getNicer(final int value) {
		if (value <= 18) {
			return value;
		}
		if (value < 93) {
			return ((value + 2) / 5) * 5;
		}
		if (value < 100) {
			return ((value + 5) / 10) * 10;
		}
		int m = 1;
		double head = value;
		while (head >= 100) {
			head = head / 10.0;
			m *= 10;
		}
		return getNicer((int) Math.round(head)) * m;
	}

	public static String format(final long v) {
		final DecimalFormat df = new DecimalFormat();
		df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
		df.setGroupingSize(3);
		df.setMaximumFractionDigits(0);
		final String t = df.format(v).replace(',', ' ');
		return t;
	}
}
