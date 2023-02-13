package net.sourceforge.plantuml.klimt.color;

import java.awt.Color;

public enum ColorOrder {
	RGB, RBG, GRB, GBR, BRG, BGR;

	public Color getColor(Color color) {
		if (this == RGB)
			return new Color(color.getRed(), color.getGreen(), color.getBlue());

		if (this == RBG)
			return new Color(color.getRed(), color.getBlue(), color.getGreen());

		if (this == GRB)
			return new Color(color.getGreen(), color.getRed(), color.getBlue());

		if (this == GBR)
			return new Color(color.getGreen(), color.getBlue(), color.getRed());

		if (this == BRG)
			return new Color(color.getBlue(), color.getRed(), color.getGreen());

		if (this == BGR)
			return new Color(color.getBlue(), color.getGreen(), color.getRed());

		throw new IllegalStateException();
	}

	public Color getReverse(Color color) {
		color = this.getColor(color);
		return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
	}

	public static ColorOrder fromString(String order) {
		try {
			return ColorOrder.valueOf(order.toUpperCase());
		} catch (Exception e) {
			return null;
		}
	}

}
