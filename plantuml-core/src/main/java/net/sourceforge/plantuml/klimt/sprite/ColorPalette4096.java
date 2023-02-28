package net.sourceforge.plantuml.klimt.sprite;

import java.awt.Color;
import java.util.Objects;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSimple;
import net.sourceforge.plantuml.klimt.color.HColors;

public class ColorPalette4096 {

	private static final String colorValue = "!#$%&*+-:;<=>?@^_~GHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public String getStringFor(Color dest) {
		return getStringFor(HColors.simple(dest));
	}

	public String getStringFor(HColor dest) {
		int result = 0;
		int resultDist = Integer.MAX_VALUE;
		for (int i = 0; i < 4096; i++) {
			final int dist = ((HColorSimple) dest).distanceTo((HColorSimple) getHtmlColorSimpleFor(i));
			if (dist < resultDist) {
				result = i;
				resultDist = dist;
			}
		}
		return encodeInt(result);
	}

	protected String encodeInt(int result) {
		final int v2 = result % 64;
		final int v1 = result / 64;
		assert v1 >= 0 && v1 <= 63 && v2 >= 0 && v2 <= 63;
		return "" + colorValue.charAt(v1) + colorValue.charAt(v2);
	}

	private HColor getHtmlColorSimpleFor(int s) {
		final Color color = Objects.requireNonNull(getColorFor(s));
		return HColors.simple(color);
	}

	public Color getColorFor(String s) {
		if (s.length() != 2)
			throw new IllegalArgumentException();

		final int v1 = colorValue.indexOf(s.charAt(0));
		if (v1 == -1)
			return null;

		final int v2 = colorValue.indexOf(s.charAt(1));
		if (v2 == -1)
			return null;

		final int code = v1 * 64 + v2;
		return getColorFor(code);
	}

	protected Color getColorFor(final int code) {
		final int blue = code % 16;
		final int green = (code / 16) % 16;
		final int red = (code / 256) % 16;
		return new Color(dup(red), dup(green), dup(blue));
	}

	private int dup(int v) {
		assert v >= 0 && v <= 15;
		return v * 16 + v;
	}

}