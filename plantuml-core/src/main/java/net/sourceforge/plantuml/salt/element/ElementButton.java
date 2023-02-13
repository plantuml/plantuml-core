package net.sourceforge.plantuml.salt.element;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.URectangle;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ElementButton extends AbstractElementText implements Element {

	private final double stroke = 2.5;
	private final double marginX = 2;
	private final double marginY = 2;

	public ElementButton(String text, UFont font, ISkinSimple spriteContainer) {
		super(text, font, true, spriteContainer);
	}

	public XDimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		XDimension2D dim = getTextDimensionAt(stringBounder, x + stroke + marginX);
		dim = dim.delta(2 * marginX, 2 * marginY);
		return dim.delta(2 * stroke);
	}

	public void drawU(UGraphic ug, int zIndex, XDimension2D dimToUse) {
		if (zIndex != 0)
			return;

		final XDimension2D dim = getPreferredDimension(ug.getStringBounder(), 0, 0);
		ug = ug.apply(new UStroke(stroke));
		ug = ug.apply(getColorEE().bg()).apply(getBlack());
		ug.apply(new UTranslate(stroke, stroke))
				.draw(new URectangle(dim.getWidth() - 2 * stroke, dim.getHeight() - 2 * stroke).rounded(10));
		final XDimension2D dimPureText = getPureTextDimension(ug.getStringBounder());
		drawText(ug, (dim.getWidth() - dimPureText.getWidth()) / 2, stroke + marginY);
	}
}
