package net.sourceforge.plantuml.klimt.shape;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.MinMax;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.svek.TextBlockBackcolored;

public class TextBlockRecentred extends AbstractTextBlock implements TextBlockBackcolored {

	private final TextBlock textBlock;

	public TextBlockRecentred(TextBlock textBlock) {
		this.textBlock = textBlock;
	}

	public void drawU(final UGraphic ug) {
		StringBounder stringBounder = ug.getStringBounder();
		final MinMax minMax = getMinMax(stringBounder);
		textBlock.drawU(ug.apply(new UTranslate(-minMax.getMinX(), -minMax.getMinY())));
	}

	// private MinMax cachedMinMax;

	public MinMax getMinMax(StringBounder stringBounder) {
		return textBlock.getMinMax(stringBounder);
		// if (cachedMinMax == null) {
		// cachedMinMax = getMinMaxSlow(stringBounder);
		// }
		// // assert
		// cachedMinMax.toString().equals(getMinMaxSlow(stringBounder).toString());
		// return cachedMinMax;
	}

	// private MinMax getMinMaxSlow(StringBounder stringBounder) {
	// final MinMax result = TextBlockUtils.getMinMax(textBlock, stringBounder);
	// return result;
	// }

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		final MinMax minMax = getMinMax(stringBounder);
		return minMax.getDimension();
	}

	public HColor getBackcolor() {
		if (textBlock instanceof TextBlockBackcolored) {
			return ((TextBlockBackcolored) textBlock).getBackcolor();
		}
		return null;
	}

}