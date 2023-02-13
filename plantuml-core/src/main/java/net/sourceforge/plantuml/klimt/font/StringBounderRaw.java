package net.sourceforge.plantuml.klimt.font;

import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.text.RichText;
import net.sourceforge.plantuml.text.StyledString;

public abstract class StringBounderRaw implements StringBounder {

	private final FontRenderContext frc;

	protected StringBounderRaw(FontRenderContext frc) {
		this.frc = frc;
	}

	public final XDimension2D calculateDimension(UFont font, String text) {
		if (RichText.isRich(text)) {
			double width = 0;
			double height = 0;
			for (StyledString s : StyledString.build(text)) {
				final UFont newFont = s.getStyle().mutateFont(font);
				final XDimension2D rect = calculateDimensionInternal(newFont, s.getText());
				width += rect.getWidth();
				height = Math.max(height, rect.getHeight());
			}
			return new XDimension2D(width, height);
		}
		return calculateDimensionInternal(font, text);
	}

	protected abstract XDimension2D calculateDimensionInternal(UFont font, String text);

	public double getDescent(UFont font, String text) {
		final LineMetrics lineMetrics = font.getUnderlayingFont().getLineMetrics(text, frc);
		final double descent = lineMetrics.getDescent();
		return descent;
	}

}
