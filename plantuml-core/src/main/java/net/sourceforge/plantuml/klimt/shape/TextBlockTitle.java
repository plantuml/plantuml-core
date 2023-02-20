package net.sourceforge.plantuml.klimt.shape;

import net.atmp.InnerStrategy;
import net.sourceforge.plantuml.klimt.LineBreakStrategy;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.creole.CreoleMode;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.MinMax;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XRectangle2D;
import net.sourceforge.plantuml.style.ISkinSimple;

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
