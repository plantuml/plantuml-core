package net.sourceforge.plantuml.klimt.drawing.svg;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorGradient;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.shape.UPixel;
import net.sourceforge.plantuml.svg.SvgGraphics;

public class DriverPixelSvg implements UDriver<UPixel, SvgGraphics> {

	public void draw(UPixel pixel, double x, double y, ColorMapper mapper, UParam param, SvgGraphics svg) {
		final HColor color = param.getColor();
		if (color instanceof HColorGradient) {
			final HColorGradient gr = (HColorGradient) color;
			svg.setStrokeColor(gr.getColor1().toSvg(mapper));
		} else {
			svg.setStrokeColor(color.toSvg(mapper));
		}
		svg.setStrokeWidth(0.5, "");

		svg.svgRectangle(x, y, 0.5, 0.5, 0, 0, 0, null, null);

	}
}