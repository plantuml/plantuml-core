package net.sourceforge.plantuml.klimt.geom;

import java.util.Objects;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.shape.TextBlock;

public enum HorizontalAlignment {

	LEFT, CENTER, RIGHT;

	public static HorizontalAlignment fromString(String s) {
		if (LEFT.name().equalsIgnoreCase(s))
			return LEFT;

		if (CENTER.name().equalsIgnoreCase(s))
			return CENTER;

		if (RIGHT.name().equalsIgnoreCase(s))
			return RIGHT;

		return null;
	}

	public static HorizontalAlignment fromString(String s, HorizontalAlignment defaultValue) {
		Objects.requireNonNull(defaultValue);
		if (s == null)
			return defaultValue;

		s = StringUtils.goUpperCase(s);
		final HorizontalAlignment result = fromString(s);
		if (result == null)
			return defaultValue;

		return result;
	}

	public String getGraphVizValue() {
		return toString().substring(0, 1).toLowerCase();
	}

	public void draw(UGraphic ug, TextBlock tb, double padding, double width) {
		if (this == HorizontalAlignment.LEFT) {
			tb.drawU(ug.apply(new UTranslate(padding, padding)));
		} else if (this == HorizontalAlignment.RIGHT) {
			final XDimension2D dimTb = tb.calculateDimension(ug.getStringBounder());
			tb.drawU(ug.apply(new UTranslate(width - dimTb.getWidth() - padding, padding)));
		} else if (this == HorizontalAlignment.CENTER) {
			final XDimension2D dimTb = tb.calculateDimension(ug.getStringBounder());
			tb.drawU(ug.apply(new UTranslate((width - dimTb.getWidth()) / 2, padding)));
		}

	}

}
