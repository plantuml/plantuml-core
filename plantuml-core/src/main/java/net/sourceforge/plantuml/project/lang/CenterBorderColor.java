package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class CenterBorderColor {

	private final HColor center;
	private final HColor border;
	private final String style;

	public CenterBorderColor(HColor center, HColor border) {
		this(center, border, null);
	}

	public CenterBorderColor(HColor center, HColor border, String style) {
		this.center = center;
		this.border = border;
		this.style = style;
	}

	public UGraphic apply(UGraphic ug) {
		if (isOk() == false) {
			throw new IllegalStateException();
		}
		ug = ug.apply(center.bg());
		if (border == null) {
			ug = ug.apply(center);
		} else {
			ug = ug.apply(border);
		}
		return ug;
	}

	public boolean isOk() {
		return center != null;
	}

	public final HColor getCenter() {
		return center;
	}

	public final String getStyle() {
		return style;
	}

	public CenterBorderColor unlinearTo(CenterBorderColor other, int completion) {
		final HColor newCenter = HColors.unlinear(this.center, other.center, completion);
		final HColor newBorder = HColors.unlinear(this.border, other.border, completion);

		return new CenterBorderColor(newCenter, newBorder, style);
	}
}
