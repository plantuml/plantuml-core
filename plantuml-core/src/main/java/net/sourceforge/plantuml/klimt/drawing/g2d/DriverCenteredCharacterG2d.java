package net.sourceforge.plantuml.klimt.drawing.g2d;

import java.awt.Graphics2D;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.font.UnusedSpace;
import net.sourceforge.plantuml.klimt.shape.UCenteredCharacter;

public class DriverCenteredCharacterG2d implements UDriver<UCenteredCharacter, Graphics2D> {

	public void draw(UCenteredCharacter characterCircled, double x, double y, ColorMapper mapper, UParam param,
			Graphics2D g2d) {
		final char c = characterCircled.getChar();
		final UFont font = characterCircled.getFont();
		final UnusedSpace unusedSpace = UnusedSpace.getUnusedSpace(font, c);

		g2d.setColor(param.getColor().toColor(mapper));
		final double xpos = x - unusedSpace.getCenterX();
		final double ypos = y - unusedSpace.getCenterY() - 0.5;

		g2d.setFont(font.getUnderlayingFont());
		g2d.drawString("" + c, (float) xpos, (float) ypos);
	}

}
