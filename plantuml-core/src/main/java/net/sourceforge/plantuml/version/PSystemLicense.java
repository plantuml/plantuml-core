package net.sourceforge.plantuml.version;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.atmp.PixelImage;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.PlainDiagram;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.klimt.AffineTransformType;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.shape.GraphicStrings;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.klimt.shape.UImage;
import net.sourceforge.plantuml.svek.TextBlockBackcolored;

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

	@Override
	public void exportDiagramGraphic(UGraphic ug) {
		final LicenseInfo licenseInfo = LicenseInfo.retrieveQuick();
		getTextBlock(licenseInfo).drawU(ug);
	}

	public void drawU(UGraphic ug) {

		final LicenseInfo licenseInfo = LicenseInfo.retrieveQuick();
			getTextBlock(licenseInfo).drawU(ug);
	}

	protected TextBlockBackcolored getTextBlock(final LicenseInfo licenseInfo) {
		final List<String> strings = new ArrayList<>();
		strings.addAll(License.getCurrent().getText1(licenseInfo));
		strings.addAll(License.getCurrent().getText2(licenseInfo));
		return getGraphicStrings(strings);
	}

}
