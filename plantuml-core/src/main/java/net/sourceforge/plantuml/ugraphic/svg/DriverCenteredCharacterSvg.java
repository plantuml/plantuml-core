package net.sourceforge.plantuml.ugraphic.svg;

import static net.sourceforge.plantuml.graphic.TextBlockUtils.createTextLayout;

import java.awt.font.TextLayout;

import net.sourceforge.plantuml.graphic.UnusedSpace;
import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.svg.SvgGraphics;
import net.sourceforge.plantuml.ugraphic.UCenteredCharacter;
import net.sourceforge.plantuml.ugraphic.UDriver;

public class DriverCenteredCharacterSvg implements UDriver<UCenteredCharacter, SvgGraphics> {

	public void draw(UCenteredCharacter characterCircled, double x, double y, ColorMapper mapper, UParam param,
			SvgGraphics svg) {
		final char c = characterCircled.getChar();
		final UFont font = characterCircled.getFont();
		final UnusedSpace unusedSpace = UnusedSpace.getUnusedSpace(font, c);

		final double xpos = x - unusedSpace.getCenterX() - 0.5;
		final double ypos = y - unusedSpace.getCenterY() - 0.5;

		final TextLayout t = createTextLayout(font, "" + c);
		final HColor textColor = param.getColor();
		svg.setFillColor(textColor.toSvg(mapper));

		svg.drawPathIterator(xpos, ypos, t.getOutline(null).getPathIterator(null));
	}
}
