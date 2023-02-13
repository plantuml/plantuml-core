package net.sourceforge.plantuml.sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.UImage;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.security.SImageIO;
import net.sourceforge.plantuml.ugraphic.AffineTransformType;
import net.sourceforge.plantuml.ugraphic.PixelImage;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.utils.Log;

public class SpriteImage implements Sprite {

	private final UImage img;

	public SpriteImage(BufferedImage img) {
		this.img = new UImage(new PixelImage(Objects.requireNonNull(img), AffineTransformType.TYPE_BILINEAR));
	}

	public TextBlock asTextBlock(final HColor color, final double scale) {
		return new AbstractTextBlock() {

			public void drawU(UGraphic ug) {
				final ColorMapper colorMapper = ug.getColorMapper();
				if (colorMapper == ColorMapper.MONOCHROME)
					ug.draw(img.monochrome().scale(scale));
				else if (color == null)
					ug.draw(img.scale(scale));
				else
					ug.draw(img.muteColor(color.toColor(colorMapper)).scale(scale));
			}

			public XDimension2D calculateDimension(StringBounder stringBounder) {
				return new XDimension2D(img.getWidth() * scale, img.getHeight() * scale);
			}
		};
	}

	public static Sprite fromInternal(String name) {
		if (name.endsWith(".png"))
			throw new IllegalArgumentException();

		final InputStream is = getInternalSprite(name + ".png");
		if (is == null)
			return null;

		try {
			return new SpriteImage(SImageIO.read(is));
		} catch (IOException e) {
			Logme.error(e);
			return null;
		}

	}

	public static InputStream getInternalSprite(final String inner) {
		final String path = "/sprites/" + inner;
		Log.info("Triying " + path);
		final InputStream is = SpriteImage.class.getResourceAsStream(path);
		return is;
	}

}
