package net.sourceforge.plantuml.klimt.drawing.svg;

import java.awt.geom.Line2D;

import net.sourceforge.plantuml.klimt.ClipContainer;
import net.sourceforge.plantuml.klimt.UClip;
import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorGradient;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.svg.SvgGraphics;

public class DriverLineSvg implements UDriver<ULine, SvgGraphics> {

	private final ClipContainer clipContainer;

	public DriverLineSvg(ClipContainer clipContainer) {
		this.clipContainer = clipContainer;
	}

	public void draw(ULine shape, double x, double y, ColorMapper mapper, UParam param, SvgGraphics svg) {
		double x2 = x + shape.getDX();
		double y2 = y + shape.getDY();

		final UClip clip = clipContainer.getClip();
		if (clip != null) {
			final Line2D.Double line = clip.getClippedLine(new Line2D.Double(x, y, x2, y2));
			if (line == null) {
				return;
			}
			x = line.x1;
			y = line.y1;
			x2 = line.x2;
			y2 = line.y2;
		}

		// // Shadow
		// if (shape.getDeltaShadow() != 0) {
		// svg.svgLineShadow(x, y, x2, y2, shape.getDeltaShadow());
		// }

		final HColor color = param.getColor();
		if (color instanceof HColorGradient) {
			final HColorGradient gr = (HColorGradient) color;
			svg.setStrokeColor(gr.getColor1().toSvg(mapper));
		} else {
			svg.setStrokeColor(color.toSvg(mapper));
		}
		svg.setStrokeWidth(param.getStroke().getThickness(), param.getStroke().getDasharraySvg());
		svg.svgLine(x, y, x2, y2, shape.getDeltaShadow());
	}
}
