package net.sourceforge.plantuml.klimt.shape;

import java.awt.image.BufferedImage;
import java.util.Objects;

import net.atmp.PixelImage;
import net.sourceforge.plantuml.klimt.AffineTransformType;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.ImgValign;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

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
