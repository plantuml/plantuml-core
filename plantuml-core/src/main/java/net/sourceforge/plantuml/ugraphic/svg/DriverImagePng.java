package net.sourceforge.plantuml.ugraphic.svg;

import java.io.IOException;

import net.sourceforge.plantuml.klimt.UClip;
import net.sourceforge.plantuml.klimt.UImage;
import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.svg.SvgGraphics;
import net.sourceforge.plantuml.ugraphic.ClipContainer;
import net.sourceforge.plantuml.ugraphic.UDriver;
import net.sourceforge.plantuml.utils.Log;

public class DriverImagePng implements UDriver<UImage, SvgGraphics> {

	private final ClipContainer clipContainer;

	public DriverImagePng(ClipContainer clipContainer) {
		this.clipContainer = clipContainer;
	}

	public void draw(UImage image, double x, double y, ColorMapper mapper, UParam param, SvgGraphics svg) {
		final UClip clip = clipContainer.getClip();
		if (clip != null) {
			if (clip.isInside(x, y) == false) {
				return;
			}
			if (clip.isInside(x + image.getWidth(), y + image.getHeight()) == false) {
				return;
			}
		}

		try {
			svg.svgImage(image.getImage(1), x, y);
		} catch (IOException e) {
			Log.error("Error in svg for image " + e);
		}
	}
}
