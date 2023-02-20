package com.plantuml.api.cheerpj;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import net.sourceforge.plantuml.klimt.font.StringBounderRaw;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class StringBounderCanvas extends StringBounderRaw {

	private final Graphics2D g2d;

	public StringBounderCanvas(Graphics2D g2d) {
		super(g2d.getFontRenderContext());
		this.g2d = g2d;
	}

	@Override
	public boolean matchesProperty(String propertyName) {
		return false;
	}

	@Override
	protected XDimension2D calculateDimensionInternal(UFont font, String text) {
		final Font javaFont = font.getUnderlayingFont();
		final FontMetrics fm = g2d.getFontMetrics(javaFont);
		final Rectangle2D rect = fm.getStringBounds(text, g2d);
		return new XDimension2D(rect.getWidth(), rect.getHeight());
	}

}
