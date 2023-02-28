package net.sourceforge.plantuml.klimt.drawing.svg;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.shape.UImageSvg;
import net.sourceforge.plantuml.svg.SvgGraphics;

public class DriverImageSvgSvg implements UDriver<UImageSvg, SvgGraphics> {

	public void draw(UImageSvg image, double x, double y, ColorMapper mapper, UParam param, SvgGraphics svg) {
		svg.svgImage(image, x, y);
	}
}