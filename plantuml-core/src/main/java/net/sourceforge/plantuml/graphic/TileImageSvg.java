package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UImageSvg;

public class TileImageSvg extends AbstractTextBlock implements TextBlock {

	private final UImageSvg svg;
	private final double scale;

	public TileImageSvg(String svg, double scale) {
		this.svg = new UImageSvg(svg, scale);
		this.scale = scale;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(svg.getWidth(), svg.getHeight());
	}

	public void drawU(UGraphic ug) {
		ug.draw(svg);
	}

}
