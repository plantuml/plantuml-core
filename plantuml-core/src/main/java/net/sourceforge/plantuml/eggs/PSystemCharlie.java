package net.sourceforge.plantuml.eggs;

import java.awt.image.BufferedImage;
import java.io.IOException;

import net.atmp.ImageBuilder;
import net.atmp.PixelImage;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.PlainDiagram;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.klimt.AffineTransformType;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.klimt.shape.UImage;
import net.sourceforge.plantuml.version.PSystemVersion;

public class PSystemCharlie extends PlainDiagram {

	private BufferedImage image;

	PSystemCharlie(UmlSource source) {
		super(source);
		image = PSystemVersion.getCharlieImage();
	}

	@Override
	public ImageBuilder createImageBuilder(FileFormatOption fileFormatOption) throws IOException {
		return super.createImageBuilder(fileFormatOption).blackBackcolor();
	}

	@Override
	public UDrawable getRootDrawable(FileFormatOption fileFormatOption) {
		return new UDrawable() {
			public void drawU(UGraphic ug) {
				final UImage im = new UImage(new PixelImage(image, AffineTransformType.TYPE_BILINEAR));
				ug.draw(im);
			}
		};
	}

	public DiagramDescription getDescription() {
		return new DiagramDescription("(Je Suis Charlie)");
	}

}
