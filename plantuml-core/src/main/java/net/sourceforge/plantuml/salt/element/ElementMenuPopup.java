package net.sourceforge.plantuml.salt.element;

import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.klimt.shape.URectangle;
import net.sourceforge.plantuml.style.ISkinSimple;

public class ElementMenuPopup extends AbstractElement {

	private final Collection<ElementMenuEntry> entries = new ArrayList<>();
	private final UFont font;
	private final ISkinSimple spriteContainer;

	public ElementMenuPopup(UFont font, ISkinSimple spriteContainer) {
		this.font = font;
		this.spriteContainer = spriteContainer;
	}

	public void addEntry(String s) {
		entries.add(new ElementMenuEntry(s, font, spriteContainer));
	}

	public XDimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		double w = 0;
		double h = 0;
		for (ElementMenuEntry entry : entries) {
			final XDimension2D dim = entry.getPreferredDimension(stringBounder, x, y);
			w = Math.max(w, dim.getWidth());
			h += dim.getHeight();
		}
		return new XDimension2D(w, h);
	}

	public void drawU(UGraphic ug, int zIndex, XDimension2D dimToUse) {
		if (zIndex != 1)
			return;

		ug = ug.apply(getBlack());

		ug.apply(getColorDD().bg()).draw(new URectangle(dimToUse.getWidth(), dimToUse.getHeight()));

		double y1 = 0;
		for (ElementMenuEntry entry : entries) {
			final double h = entry.getPreferredDimension(ug.getStringBounder(), 0, y1).getHeight();
			if (entry.getText().equals("-"))
				ug.apply(UTranslate.dy(y1 + h / 2)).draw(ULine.hline(dimToUse.getWidth()));
			else
				entry.drawU(ug.apply(UTranslate.dy(y1)), zIndex, dimToUse);

			y1 += h;
		}
	}

}
