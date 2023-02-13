package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class TextBlockEmpty extends AbstractTextBlock {

	private final double width;
	private final double height;

	public TextBlockEmpty(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public TextBlockEmpty() {
		this(0, 0);
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(width, height);
	}

	public void drawU(UGraphic ug) {
	}

}
