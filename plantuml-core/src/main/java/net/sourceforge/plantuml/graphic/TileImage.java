package net.sourceforge.plantuml.graphic;

import java.awt.image.BufferedImage;
import java.util.Objects;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.UImage;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.AffineTransformType;
import net.sourceforge.plantuml.ugraphic.PixelImage;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class TileImage extends AbstractTextBlock implements TextBlock {

	private final BufferedImage image;
	private final int vspace;

	public TileImage(BufferedImage image, ImgValign valign, int vspace) {
		this.image = Objects.requireNonNull(image);
		this.vspace = vspace;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(image.getWidth(), image.getHeight() + 2 * vspace);
	}

	public void drawU(UGraphic ug) {
		ug.apply(UTranslate.dy(vspace)).draw(new UImage(new PixelImage(image, AffineTransformType.TYPE_BILINEAR)));
	}

}
