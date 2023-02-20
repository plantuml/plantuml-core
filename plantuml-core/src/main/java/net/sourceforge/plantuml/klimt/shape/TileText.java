package net.sourceforge.plantuml.klimt.shape;

import java.util.StringTokenizer;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.url.Url;
import net.sourceforge.plantuml.utils.Log;

public class TileText extends AbstractTextBlock implements TextBlock {

	private final String text;
	private final FontConfiguration fontConfiguration;
	private final Url url;

	public TileText(String text, FontConfiguration fontConfiguration, Url url) {
		this.fontConfiguration = fontConfiguration;
		this.text = text;
		this.url = url;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		final XDimension2D rect = stringBounder.calculateDimension(fontConfiguration.getFont(), text);
		final int spaceBottom = Math.abs(fontConfiguration.getSpace());
		Log.debug("g2d=" + rect);
		Log.debug("Size for " + text + " is " + rect);
		double h = rect.getHeight();
		if (h < 10) {
			h = 10;
		}
		final double width = text.indexOf('\t') == -1 ? rect.getWidth() : getWidth(stringBounder);
		return new XDimension2D(width, h + spaceBottom);
	}

	public double getFontSize2D() {
		return fontConfiguration.getFont().getSize2D();
	}

	double getTabSize(StringBounder stringBounder) {
		return stringBounder.calculateDimension(fontConfiguration.getFont(), "        ").getWidth();
	}

	public void drawU(UGraphic ug) {
		double x = 0;
		if (url != null) {
			ug.startUrl(url);
		}
		ug = ug.apply(fontConfiguration.getColor());

		final StringTokenizer tokenizer = new StringTokenizer(text, "\t", true);

		if (tokenizer.hasMoreTokens()) {
			final double tabSize = getTabSize(ug.getStringBounder());
			while (tokenizer.hasMoreTokens()) {
				final String s = tokenizer.nextToken();
				if (s.equals("\t")) {
					final double remainder = x % tabSize;
					x += tabSize - remainder;
				} else {
					final UText utext = new UText(s, fontConfiguration);
					final XDimension2D dim = ug.getStringBounder().calculateDimension(fontConfiguration.getFont(), s);
					final int space = fontConfiguration.getSpace();
					final double ypos;
					if (space < 0) {
						ypos = space /*- getFontSize2D() - space*/;
					} else {
						ypos = space;
					}
					ug.apply(new UTranslate(x, ypos)).draw(utext);
					x += dim.getWidth();
				}
			}
		}
		if (url != null) {
			ug.closeUrl();
		}
	}

	double getWidth(StringBounder stringBounder) {
		final StringTokenizer tokenizer = new StringTokenizer(text, "\t", true);
		final double tabSize = getTabSize(stringBounder);
		double x = 0;
		while (tokenizer.hasMoreTokens()) {
			final String s = tokenizer.nextToken();
			if (s.equals("\t")) {
				final double remainder = x % tabSize;
				x += tabSize - remainder;
			} else {
				final XDimension2D dim = stringBounder.calculateDimension(fontConfiguration.getFont(), s);
				x += dim.getWidth();
			}
		}
		return x;
	}

}
