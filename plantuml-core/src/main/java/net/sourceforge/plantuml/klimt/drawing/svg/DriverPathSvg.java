package net.sourceforge.plantuml.klimt.drawing.svg;

import net.sourceforge.plantuml.klimt.ClipContainer;
import net.sourceforge.plantuml.klimt.UClip;
import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.drawing.g2d.DriverShadowedG2d;
import net.sourceforge.plantuml.svg.SvgGraphics;

public class DriverPathSvg extends DriverShadowedG2d implements UDriver<UPath, SvgGraphics> {

	private final ClipContainer clipContainer;

	public DriverPathSvg(ClipContainer clipContainer) {
		this.clipContainer = clipContainer;
	}

	public void draw(UPath shape, double x, double y, ColorMapper mapper, UParam param, SvgGraphics svg) {
		final UClip clip = clipContainer.getClip();
		if (clip != null && clip.isInside(x, y, shape) == false)
			return;

		if (shape.isOpenIconic()) {
			final HColor color = param.getColor();
			svg.setFillColor(color.toSvg(mapper));
			svg.setStrokeColor("");
			svg.setStrokeWidth(0, "");
		} else {
			DriverRectangleSvg.applyFillColor(svg, mapper, param);
			DriverRectangleSvg.applyStrokeColor(svg, mapper, param);

			svg.setStrokeWidth(param.getStroke().getThickness(), param.getStroke().getDasharraySvg());
		}

		svg.svgPath(x, y, shape, shape.getDeltaShadow());

	}
}