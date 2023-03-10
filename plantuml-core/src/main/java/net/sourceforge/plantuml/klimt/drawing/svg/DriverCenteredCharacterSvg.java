// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.drawing.svg;

import java.awt.font.TextLayout;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.font.UFontContext;
import net.sourceforge.plantuml.klimt.font.UnusedSpace;
import net.sourceforge.plantuml.klimt.shape.UCenteredCharacter;

public class DriverCenteredCharacterSvg implements UDriver<UCenteredCharacter, SvgGraphics> {

	public void draw(UCenteredCharacter characterCircled, double x, double y, ColorMapper mapper, UParam param,
			SvgGraphics svg) {
		final char c = characterCircled.getChar();
		final UFont font = characterCircled.getFont();
		final UnusedSpace unusedSpace = UnusedSpace.getUnusedSpace(font, c);

		final double xpos = x - unusedSpace.getCenterX() - 0.5;
		final double ypos = y - unusedSpace.getCenterY() - 0.5;

		final TextLayout t = UFontContext.SVG.createTextLayout(font, "" + c);
		final HColor textColor = param.getColor();
		svg.setFillColor(textColor.toSvg(mapper));

		svg.drawPathIterator(xpos, ypos, t.getOutline(null).getPathIterator(null));
	}
}
