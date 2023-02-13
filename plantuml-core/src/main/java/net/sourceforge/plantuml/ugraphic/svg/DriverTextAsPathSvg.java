package net.sourceforge.plantuml.ugraphic.svg;

import static net.sourceforge.plantuml.graphic.TextBlockUtils.createTextLayout;

import java.awt.font.TextLayout;

import net.sourceforge.plantuml.klimt.UClip;
import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.UText;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.svg.SvgGraphics;
import net.sourceforge.plantuml.ugraphic.ClipContainer;
import net.sourceforge.plantuml.ugraphic.UDriver;

public class DriverTextAsPathSvg implements UDriver<UText, SvgGraphics> {

	private final ClipContainer clipContainer;

	public DriverTextAsPathSvg(ClipContainer clipContainer) {
		this.clipContainer = clipContainer;
	}

	public void draw(UText ushape, double x, double y, ColorMapper mapper, UParam param, SvgGraphics svg) {
		final UClip clip = clipContainer.getClip();
		if (clip != null && clip.isInside(x, y) == false) {
			return;
		}

		final TextLayout t = createTextLayout(ushape);
		svg.drawPathIterator(x, y, t.getOutline(null).getPathIterator(null));
	}

}
