package net.sourceforge.plantuml.version;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.PlainDiagram;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.graphic.GraphicStrings;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.UImage;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.svek.TextBlockBackcolored;
import net.sourceforge.plantuml.ugraphic.AffineTransformType;
import net.sourceforge.plantuml.ugraphic.PixelImage;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class PSystemLicense extends PlainDiagram implements UDrawable {

	@Override
	protected UDrawable getRootDrawable(FileFormatOption fileFormatOption) {
		return this;
	}

	public static PSystemLicense create(UmlSource source) throws IOException {
		return new PSystemLicense(source);
	}

	public PSystemLicense(UmlSource source) {
		super(source);
	}

	private TextBlockBackcolored getGraphicStrings(List<String> strings) {
		return GraphicStrings.createBlackOnWhite(strings);
	}

	public DiagramDescription getDescription() {
		return new DiagramDescription("(License)");
	}

	public void drawU(UGraphic ug) {

			final List<String> strings = new ArrayList<>();
			getGraphicStrings(strings).drawU(ug);
	}
}
