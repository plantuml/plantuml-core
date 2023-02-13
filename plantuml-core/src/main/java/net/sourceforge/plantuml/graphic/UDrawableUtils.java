package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class UDrawableUtils {

	public static UDrawable move(final UDrawable orig, final double dx, final double dy) {
		return new UDrawable() {
			public void drawU(UGraphic ug) {
				orig.drawU(ug.apply(new UTranslate(dx, dy)));
			}
		};
	}

}
