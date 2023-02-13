package net.sourceforge.plantuml.salt.element;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.UFont;

public abstract class AbstractElement implements Element {

	final protected HColor getBlack() {
		return HColors.BLACK.withDark(HColors.WHITE);
	}

	final protected HColor getColor88() {
		return buildColor("#8", "#8");
	}

	final protected HColor getColorAA() {
		return buildColor("#A", "#6");
	}

	final protected HColor getColorBB() {
		return buildColor("#B", "#5");
	}

	final protected HColor getColorDD() {
		return buildColor("#D", "#3");
	}

	final protected HColor getColorEE() {
		return buildColor("#E", "#2");
	}

	final protected HColor getWhite() {
		return HColors.WHITE.withDark(HColors.BLACK);
	}

	private HColor buildColor(String color1, String color2) {
		final HColor tmp1 = HColorSet.instance().getColorOrWhite(color1);
		final HColor tmp2 = HColorSet.instance().getColorOrWhite(color2);
		return tmp1.withDark(tmp2);
	}

	final protected FontConfiguration blackBlueTrue(UFont font) {
		return FontConfiguration.blackBlueTrue(font);
	}

}
