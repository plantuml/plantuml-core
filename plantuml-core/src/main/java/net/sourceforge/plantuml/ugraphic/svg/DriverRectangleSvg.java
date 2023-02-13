package net.sourceforge.plantuml.ugraphic.svg;

import java.awt.geom.Rectangle2D;

import net.sourceforge.plantuml.klimt.UClip;
import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.URectangle;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorGradient;
import net.sourceforge.plantuml.svg.SvgGraphics;
import net.sourceforge.plantuml.ugraphic.ClipContainer;
import net.sourceforge.plantuml.ugraphic.UDriver;

public class DriverRectangleSvg implements UDriver<URectangle, SvgGraphics> {

	private final ClipContainer clipContainer;

	public DriverRectangleSvg(ClipContainer clipContainer) {
		this.clipContainer = clipContainer;
	}

	public void draw(URectangle rect, double x, double y, ColorMapper mapper, UParam param, SvgGraphics svg) {
		final double rx = rect.getRx();
		final double ry = rect.getRy();
		double width = rect.getWidth();
		double height = rect.getHeight();

		applyFillColor(svg, mapper, param);
		applyStrokeColor(svg, mapper, param);

		svg.setStrokeWidth(param.getStroke().getThickness(), param.getStroke().getDasharraySvg());

		final UClip clip = clipContainer.getClip();
		if (clip != null) {
			final Rectangle2D.Double r = clip.getClippedRectangle(new Rectangle2D.Double(x, y, width, height));
			x = r.x;
			y = r.y;
			width = r.width;
			height = r.height;
			if (height <= 0) {
				return;
			}
		}
		svg.svgRectangle(x, y, width, height, rx / 2, ry / 2, rect.getDeltaShadow(), rect.getComment(),
				rect.getCodeLine());
	}

	public static void applyFillColor(SvgGraphics svg, ColorMapper mapper, UParam param) {
		final HColor background = param.getBackcolor();
		if (background instanceof HColorGradient) {
			final HColorGradient gr = (HColorGradient) background;
			final String id = svg.createSvgGradient(gr.getColor1().toRGB(mapper), gr.getColor2().toRGB(mapper),
					gr.getPolicy());
			svg.setFillColor("url(#" + id + ")");
		} else {
			svg.setFillColor(background.toSvg(mapper));
		}
	}

	public static void applyStrokeColor(SvgGraphics svg, ColorMapper mapper, UParam param) {
		final HColor color = param.getColor();
		if (color instanceof HColorGradient) {
			final HColorGradient gr = (HColorGradient) color;
			final String id = svg.createSvgGradient(gr.getColor1().toRGB(mapper), gr.getColor2().toRGB(mapper),
					gr.getPolicy());
			svg.setStrokeColor("url(#" + id + ")");
		} else {
			svg.setStrokeColor(color.toSvg(mapper));
		}

	}
}
