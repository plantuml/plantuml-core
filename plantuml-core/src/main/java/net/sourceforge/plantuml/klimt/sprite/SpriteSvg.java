package net.sourceforge.plantuml.klimt.sprite;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.UImageSvg;

public class SpriteSvg implements Sprite {

	private final String svg;

	public SpriteSvg(String svg) {
		this.svg = svg;
	}

	public TextBlock asTextBlock(final HColor color, final double scale) {
		final UImageSvg img = new UImageSvg(svg, scale);
		return new AbstractTextBlock() {

			public void drawU(UGraphic ug) {
				ug.draw(img);
			}

			public XDimension2D calculateDimension(StringBounder stringBounder) {
				return new XDimension2D(img.getWidth() * scale, img.getHeight() * scale);
			}
		};
	}

}
