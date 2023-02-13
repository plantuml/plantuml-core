package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class DecorateEntityImage3 extends AbstractTextBlock  implements TextBlockBackcolored {

	private final TextBlock original;
	private final HColor color;

	private DecorateEntityImage3(TextBlock original, HColor color) {
		this.original = original;
		this.color = color;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return original.calculateDimension(stringBounder);
	}

	public void drawU(UGraphic ug) {
		original.drawU(ug);
	}

	public HColor getBackcolor() {
		return color;
	}

}
