package net.sourceforge.plantuml.salt.element;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.ULine;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ElementTextField extends AbstractElementText implements Element {

	public ElementTextField(String text, UFont font, ISkinSimple spriteContainer) {
		super(text, font, true, spriteContainer);
	}

	public XDimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		final XDimension2D dim = getTextDimensionAt(stringBounder, x);
		return dim.delta(6, 2);
	}

	public void drawU(UGraphic ug, int zIndex, XDimension2D dimToUse) {
		if (zIndex != 0) {
			return;
		}
		drawText(ug, 3, 0);
		final XDimension2D dim = getPreferredDimension(ug.getStringBounder(), 0, 0);
		final XDimension2D textDim = getTextDimensionAt(ug.getStringBounder(), 0);
		ug.apply(new UTranslate(1, textDim.getHeight())).draw(ULine.hline(dim.getWidth() - 3));
		final double y3 = textDim.getHeight() - 3;
		ug.apply(new UTranslate(1, y3)).draw(ULine.vline(2));
		ug.apply(new UTranslate(3 + textDim.getWidth() + 1, y3)).draw(ULine.vline(2));
	}

}
