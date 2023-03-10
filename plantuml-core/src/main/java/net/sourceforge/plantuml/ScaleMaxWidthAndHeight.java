// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml;

public class ScaleMaxWidthAndHeight extends ScaleProtected implements Scale {

	private final double maxWidth;
	private final double maxHeight;

	public ScaleMaxWidthAndHeight(double maxWidth, double maxHeight) {
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
	}

	public double getScaleInternal(double width, double height) {
		final double scale1 = maxWidth / width;
		final double scale2 = maxHeight / height;
		final double min = Math.min(scale1, scale2);
		if (min > 1) {
			return 1;
		}
		return min;
	}
}
