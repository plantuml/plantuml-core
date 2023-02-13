package net.sourceforge.plantuml.ugraphic.svg;

import net.sourceforge.plantuml.klimt.UClip;
import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.svg.SvgGraphics;
import net.sourceforge.plantuml.ugraphic.ClipContainer;
import net.sourceforge.plantuml.ugraphic.UDriver;
import net.sourceforge.plantuml.ugraphic.UEllipse;

public class DriverEllipseSvg implements UDriver<UEllipse, SvgGraphics> {

	private final ClipContainer clipContainer;

	public DriverEllipseSvg(ClipContainer clipContainer) {
		this.clipContainer = clipContainer;
	}

	public void draw(UEllipse shape, double x, double y, ColorMapper mapper, UParam param, SvgGraphics svg) {
		final double width = shape.getWidth();
		final double height = shape.getHeight();

		final UClip clip = clipContainer.getClip();
		if (clip != null) {
			if (clip.isInside(x, y) == false) {
				return;
			}
			if (clip.isInside(x + width, y + height) == false) {
				return;
			}
		}

		DriverRectangleSvg.applyStrokeColor(svg, mapper, param);
		DriverRectangleSvg.applyFillColor(svg, mapper, param);

		svg.setStrokeWidth(param.getStroke().getThickness(), param.getStroke().getDasharraySvg());

		double start = shape.getStart();
		final double extend = shape.getExtend();
		final double cx = x + width / 2;
		final double cy = y + height / 2;
		if (start == 0 && extend == 0) {
			svg.svgEllipse(cx, cy, width / 2, height / 2, shape.getDeltaShadow());
		} else {
			start = start + 90;
			if (extend > 0) {
				// http://www.itk.ilstu.edu/faculty/javila/SVG/SVG_drawing1/elliptical_curve.htm
				final double x1 = cx + Math.sin(start * Math.PI / 180.) * width / 2;
				final double y1 = cy + Math.cos(start * Math.PI / 180.) * height / 2;
				final double x2 = cx + Math.sin((start + extend) * Math.PI / 180.) * width / 2;
				final double y2 = cy + Math.cos((start + extend) * Math.PI / 180.) * height / 2;
				svg.svgArcEllipse(width / 2, height / 2, x1, y1, x2, y2);
			} else {
				final double x1 = cx + Math.sin((start + extend) * Math.PI / 180.) * width / 2;
				final double y1 = cy + Math.cos((start + extend) * Math.PI / 180.) * height / 2;
				final double x2 = cx + Math.sin(start * Math.PI / 180.) * width / 2;
				final double y2 = cy + Math.cos(start * Math.PI / 180.) * height / 2;
				svg.svgArcEllipse(width / 2, height / 2, x1, y1, x2, y2);

			}
		}
	}

}
