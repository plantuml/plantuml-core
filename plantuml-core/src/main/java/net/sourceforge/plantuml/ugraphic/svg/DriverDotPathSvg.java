package net.sourceforge.plantuml.ugraphic.svg;

import net.sourceforge.plantuml.klimt.DotPath;
import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.svg.SvgGraphics;
import net.sourceforge.plantuml.ugraphic.UDriver;

public class DriverDotPathSvg implements UDriver<DotPath, SvgGraphics> {

	public void draw(DotPath shape, double x, double y, ColorMapper mapper, UParam param, SvgGraphics svg) {
		// DriverLineG2d.manageStroke(param, g2d);

		if (param.getColor().isTransparent() == false) {
			DriverRectangleSvg.applyStrokeColor(svg, mapper, param);

			svg.setFillColor(null);
			svg.setStrokeWidth(param.getStroke().getThickness(), param.getStroke().getDasharraySvg());

			svg.svgPath(x, y, shape.toUPath(), 0);
		}
	}
}
