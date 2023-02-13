package net.sourceforge.plantuml.ugraphic.svg;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.svg.SvgGraphics;
import net.sourceforge.plantuml.ugraphic.UDriver;
import net.sourceforge.plantuml.ugraphic.UImageSvg;

public class DriverImageSvgSvg implements UDriver<UImageSvg, SvgGraphics> {

	public void draw(UImageSvg image, double x, double y, ColorMapper mapper, UParam param, SvgGraphics svg) {
		svg.svgImage(image, x, y);
	}
}
