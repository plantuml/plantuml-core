package net.sourceforge.plantuml.graphic;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.LineBreakStrategy;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XRectangle2D;
import net.sourceforge.plantuml.creole.CreoleMode;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.MinMax;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class TextBlockTitle implements TextBlock {

	private final double outMargin = 2;

	private final TextBlock textBlock;

	TextBlockTitle(FontConfiguration font, Display stringsToDisplay, ISkinSimple spriteContainer) {
		if (stringsToDisplay.size() == 1 && stringsToDisplay.get(0).length() == 0) {
			throw new IllegalArgumentException();
		}
		final LineBreakStrategy lineBreak = LineBreakStrategy.NONE;
		textBlock = stringsToDisplay.create0(font, HorizontalAlignment.CENTER, spriteContainer, lineBreak,
				CreoleMode.FULL, null, null);
	}

	public final void drawU(UGraphic ug) {
		textBlock.drawU(ug.apply(UTranslate.dx(outMargin)));
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		final XDimension2D textDim = textBlock.calculateDimension(stringBounder);
		final double width = textDim.getWidth() + outMargin * 2;
		final double height = textDim.getHeight();
		return new XDimension2D(width, height);
	}

	public MinMax getMinMax(StringBounder stringBounder) {
		throw new UnsupportedOperationException();
	}

	public XRectangle2D getInnerPosition(String member, StringBounder stringBounder, InnerStrategy strategy) {
		return null;
	}

}
