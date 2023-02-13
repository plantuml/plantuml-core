package net.sourceforge.plantuml.salt.element;

import java.util.List;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.UPixel;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ElementImage extends AbstractElement {

	private final List<String> img;

	public ElementImage(List<String> img) {
		this.img = img;
	}

	public XDimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		return new XDimension2D(img.get(0).length(), img.size());
	}

	public void drawU(UGraphic ug, int zIndex, XDimension2D dimToUse) {
		if (zIndex != 0)
			return;

		ug = ug.apply(getBlack());

		final int w = img.get(0).length();
		final int h = img.size();
		for (int i = 0; i < w; i++)
			for (int j = 0; j < h; j++) {
				final char c = img.get(j).charAt(i);
				if (c == 'X')
					ug.apply(new UTranslate(i, j)).draw(new UPixel());
			}

	}
}
