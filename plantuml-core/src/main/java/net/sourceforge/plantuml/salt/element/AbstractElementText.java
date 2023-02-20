package net.sourceforge.plantuml.salt.element;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.creole.command.Splitter;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.style.ISkinSimple;

abstract class AbstractElementText extends AbstractElement {

	private final TextBlock block;
	private final FontConfiguration config;
	private final int charLength;

	public AbstractElementText(String text, UFont font, boolean manageLength, ISkinSimple spriteContainer) {
		config = blackBlueTrue(font);
		if (manageLength) {
			this.charLength = getCharNumber(text);
			text = StringUtils.trin(text);
		} else {
			this.charLength = 0;
		}
		this.block = Display.create(text).create(config, HorizontalAlignment.LEFT, spriteContainer);
	}

	private int getCharNumber(String text) {
		text = text.replaceAll("<&[-\\w]+>", "00");
		text = Splitter.purgeAllTag(text);
		return text.length();
	}

	protected void drawText(UGraphic ug, double x, double y) {
		block.drawU(ug.apply(new UTranslate(x, y)));
	}

	protected XDimension2D getPureTextDimension(StringBounder stringBounder) {
		return block.calculateDimension(stringBounder);
	}

	protected XDimension2D getTextDimensionAt(StringBounder stringBounder, double x) {
		final XDimension2D result = block.calculateDimension(stringBounder);
		if (charLength == 0) {
			return result;
		}
		final double dimSpace = getSingleSpace(stringBounder);
		// final double endx = x + result.getWidth();
		// final double mod = endx % CHAR_SIZE;
		// final double delta = charLength * CHAR_SIZE - mod;
		// return Dimension2DDouble.delta(result, delta, 0);
		return new XDimension2D(Math.max(result.getWidth(), charLength * dimSpace), result.getHeight());
	}

	private double getSingleSpace(StringBounder stringBounder) {
		// double max = 0;
		// for (int i = 32; i < 127; i++) {
		// final char c = (char) i;
		// final double w = Display.create(Arrays.asList("" + c), config,
		// HorizontalAlignment.LEFT)
		// .calculateDimension(stringBounder).getWidth();
		// if (w > max) {
		// Log.println("c="+c+" "+max);
		// max = w;
		// }
		// }
		// return max;
		return 8;
	}

	protected final FontConfiguration getConfig() {
		return config;
	}

}
