package net.sourceforge.plantuml.klimt.shape;

import java.awt.Color;
import java.awt.image.BufferedImage;

import net.sourceforge.plantuml.klimt.MutableImage;
import net.sourceforge.plantuml.klimt.UShape;

public class UImage implements UShape {

	private final MutableImage image;
	private final String formula;
	private final String rawFileName;

	public UImage(MutableImage image) {
		this(image, null, null);
	}

	private UImage(MutableImage image, String rawFileName, String formula) {
		this.image = image;
		this.formula = formula;
		this.rawFileName = rawFileName;
	}

	public final UImage withRawFileName(String rawFileName) {
		return new UImage(image, rawFileName, formula);
	}

	public final UImage withFormula(String formula) {
		return new UImage(image, rawFileName, formula);
	}

	public final String getRawFileName() {
		return rawFileName;
	}

	public final String getFormula() {
		return formula;
	}

	public UImage scale(double scale) {
		return new UImage(image.withScale(scale), rawFileName, formula);
	}

	public final BufferedImage getImage(double withScale) {
		return image.withScale(withScale).getImage();
		// return bufferedImage.getImage();
	}

	public int getWidth() {
		return image.getImage().getWidth() - 1;
	}

	public int getHeight() {
		return image.getImage().getHeight() - 1;
	}

	public UImage muteColor(Color newColor) {
		return new UImage(image.muteColor(newColor), rawFileName, formula);
	}

	public UImage muteTransparentColor(Color newColor) {
		return new UImage(image.muteTransparentColor(newColor), rawFileName, formula);
	}

	public UImage monochrome() {
		return new UImage(image.monochrome(), rawFileName, formula);
	}

	public double getScale() {
		return image.getScale();
	}

}