package net.sourceforge.plantuml.ugraphic;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.svek.image.ContainingEllipse;
import net.sourceforge.plantuml.svek.image.Footprint;

public class TextBlockInEllipse extends AbstractTextBlock implements TextBlock {

	private final TextBlock text;
	private final ContainingEllipse ellipse;

	public TextBlockInEllipse(TextBlock text, StringBounder stringBounder) {
		this.text = text;
		final XDimension2D textDim = text.calculateDimension(stringBounder);
		double alpha = textDim.getHeight() / textDim.getWidth();
		if (alpha < .2) {
			alpha = .2;
		} else if (alpha > .8) {
			alpha = .8;
		}
		final Footprint footprint = new Footprint(stringBounder);
		ellipse = footprint.getEllipse(text, alpha);

	}

	public UEllipse getUEllipse() {
		return ellipse.asUEllipse().bigger(6);
	}

	public void drawU(UGraphic ug) {
		final UEllipse sh = getUEllipse();
		final XPoint2D center = ellipse.getCenter();
		
		final double dx = sh.getWidth() / 2 - center.getX();
		final double dy = sh.getHeight() / 2 - center.getY();
		
		ug.draw(sh);
		
		text.drawU(ug.apply(new UTranslate(dx, (dy - 2))));
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return getUEllipse().getDimension();
	}

	public void setDeltaShadow(double deltaShadow) {
		ellipse.setDeltaShadow(deltaShadow);
	}
}
