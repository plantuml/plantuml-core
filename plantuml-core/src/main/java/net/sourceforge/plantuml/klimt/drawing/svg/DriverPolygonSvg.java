package net.sourceforge.plantuml.klimt.drawing.svg;

import net.sourceforge.plantuml.klimt.ClipContainer;
import net.sourceforge.plantuml.klimt.UClip;
import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.shape.UPolygon;
import net.sourceforge.plantuml.svg.SvgGraphics;

public class DriverPolygonSvg implements UDriver<UPolygon, SvgGraphics> {

	private final ClipContainer clipContainer;

	public DriverPolygonSvg(ClipContainer clipContainer) {
		this.clipContainer = clipContainer;
	}

	public void draw(UPolygon shape, double x, double y, ColorMapper mapper, UParam param, SvgGraphics svg) {
		final double points[] = shape.getPointArray(x, y);
		assert points.length % 2 == 0;
		final UClip clip = clipContainer.getClip();
		if (clip != null)
			for (int j = 0; j < points.length; j += 2)
				if (clip.isInside(points[j], points[j + 1]) == false)
					return;

		DriverRectangleSvg.applyFillColor(svg, mapper, param);
		DriverRectangleSvg.applyStrokeColor(svg, mapper, param);

		svg.setStrokeWidth(param.getStroke().getThickness(), param.getStroke().getDasharraySvg());

		svg.svgPolygon(shape.getDeltaShadow(), points);
	}
}
