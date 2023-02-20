package net.sourceforge.plantuml.klimt.sprite;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.code.AsciiEncoder;
import net.sourceforge.plantuml.code.ByteArray;
import net.sourceforge.plantuml.code.CompressionZlib;
import net.sourceforge.plantuml.code.NoPlantumlCompressionException;
import net.sourceforge.plantuml.klimt.color.ColorUtils;
import net.sourceforge.plantuml.log.Logme;

public enum SpriteGrayLevel {

	GRAY_16(16), GRAY_8(8), GRAY_4(4);

	private final int nbColor;

	private SpriteGrayLevel(int nbColor) {
		this.nbColor = nbColor;
	}

	public static SpriteGrayLevel get(int n) {
		if (n == 4)
			return SpriteGrayLevel.GRAY_4;

		if (n == 8)
			return SpriteGrayLevel.GRAY_8;

		if (n == 16)
			return SpriteGrayLevel.GRAY_16;

		throw new UnsupportedOperationException();
	}

	public int getNbColor() {
		return nbColor;
	}


	private List<String> encode16(BufferedImage img) {
		final int width = img.getWidth();
		final int height = img.getHeight();
		// final int type = img.getType();

		final List<String> result = new ArrayList<>();

		for (int y = 0; y < height; y++) {
			final StringBuilder sb = new StringBuilder();
			for (int x = 0; x < width; x++) {
				final int level = getGrayOn16(img, x, y);
				final char code = "0123456789ABCDEF".charAt(level);
				sb.append(code);
			}
			result.add(sb.toString());
		}
		return Collections.unmodifiableList(result);
	}

	private List<String> encode8(BufferedImage img) {
		final int width = img.getWidth();
		final int height = img.getHeight();
		// final int type = img.getType();

		final List<String> result = new ArrayList<>();

		for (int y = 0; y < height; y += 2) {
			final StringBuilder sb = new StringBuilder();
			for (int x = 0; x < width; x++) {
				final int level1 = getGrayOn16(img, x, y) / 2;
				assert level1 >= 0 && level1 <= 7;
				final int level2 = getGrayOn16(img, x, y + 1) / 2;
				assert level2 >= 0 && level2 <= 7;
				final int v = level1 * 8 + level2;
				sb.append(AsciiEncoder.encode6bit((byte) v));
			}
			result.add(sb.toString());
		}
		return Collections.unmodifiableList(result);
	}

	private List<String> encode4(BufferedImage img) {
		final int width = img.getWidth();
		final int height = img.getHeight();
		// final int type = img.getType();

		final List<String> result = new ArrayList<>();

		for (int y = 0; y < height; y += 3) {
			final StringBuilder sb = new StringBuilder();
			for (int x = 0; x < width; x++) {
				final int level1 = getGrayOn16(img, x, y) / 4;
				assert level1 >= 0 && level1 <= 3;
				final int level2 = getGrayOn16(img, x, y + 1) / 4;
				assert level2 >= 0 && level2 <= 3;
				final int level3 = getGrayOn16(img, x, y + 2) / 4;
				assert level3 >= 0 && level3 <= 3;
				final int v = level1 * 16 + level2 * 4 + level3;
				sb.append(AsciiEncoder.encode6bit((byte) v));
			}
			result.add(sb.toString());
		}
		return Collections.unmodifiableList(result);
	}

	private int getGrayOn16(BufferedImage img, int x, int y) {
		if (x >= img.getWidth()) {
			return 0;
		}
		if (y >= img.getHeight()) {
			return 0;
		}
		final Color g = ColorUtils.getGrayScaleColor(new Color(img.getRGB(x, y)));
		final int gray = 255 - g.getRed();
		return gray / 16;
	}

	public Sprite buildSprite(int width, int height, List<String> strings) {
		if (this == SpriteGrayLevel.GRAY_16)
			return buildSprite16(strings);

		if (this == SpriteGrayLevel.GRAY_8)
			return buildSprite8(width, height, strings);

		if (this == SpriteGrayLevel.GRAY_4)
			return buildSprite4(width, height, strings);

		throw new UnsupportedOperationException(toString());
	}

	private Sprite buildSprite16(List<String> strings) {
		final SpriteMonochrome result = new SpriteMonochrome(strings.get(0).length(), strings.size(), 16);
		for (int col = 0; col < result.getWidth(); col++) {
			for (int line = 0; line < result.getHeight(); line++) {
				if (col >= strings.get(line).length()) {
					continue;
				}
				if (strings.get(line).charAt(col) != '0') {
					final String s = "" + strings.get(line).charAt(col);
					final int x = Integer.parseInt(StringUtils.goUpperCase(s), 16);
					result.setGray(col, line, x);
				}
			}
		}
		return result;
	}

	private Sprite buildSprite8(int width, int height, List<String> strings) {
		final SpriteMonochrome result = new SpriteMonochrome(width, height, 8);
		for (int col = 0; col < result.getWidth(); col++) {
			for (int line = 0; line < strings.size(); line++) {
				if (col >= strings.get(line).length()) {
					continue;
				}
				final int v = AsciiEncoder.decode6bit(strings.get(line).charAt(col));
				final int w1 = v / 8;
				final int w2 = v % 8;
				result.setGray(col, line * 2, w1);
				result.setGray(col, line * 2 + 1, w2);

			}
		}
		return result;
	}

	private Sprite buildSprite4(int width, int height, List<String> strings) {
		final SpriteMonochrome result = new SpriteMonochrome(width, height, 4);
		for (int col = 0; col < result.getWidth(); col++) {
			for (int line = 0; line < strings.size(); line++) {
				if (col >= strings.get(line).length()) {
					continue;
				}
				int v = AsciiEncoder.decode6bit(strings.get(line).charAt(col));
				final int w1 = v / 16;
				v = v % 16;
				final int w2 = v / 4;
				final int w3 = v % 4;
				result.setGray(col, line * 3, w1);
				result.setGray(col, line * 3 + 1, w2);
				result.setGray(col, line * 3 + 2, w3);

			}
		}
		return result;
	}


	public Sprite buildSpriteZ(int width, int height, String compressed) {
		final byte comp[] = new AsciiEncoder().decode(compressed);
		try {
			final ByteArray img = new CompressionZlib().decompress(comp);
			final SpriteMonochrome result = new SpriteMonochrome(width, height, nbColor);
			int cpt = 0;
			for (int line = 0; line < result.getHeight(); line++)
				for (int col = 0; col < result.getWidth(); col++)
					result.setGray(col, line, img.getByteAt(cpt++));

			return result;
		} catch (NoPlantumlCompressionException e) {
			Logme.error(e);
			return null;
		}
	}

}
