package net.sourceforge.plantuml.descdiagram;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.URectangle;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.ugraphic.UCenteredCharacter;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class BoxedCharacter extends AbstractTextBlock implements TextBlock {

	private final String c;
	private final UFont font;
	private final HColor innerCircle;
	private final HColor circle;
	private final HColor fontColor;
	private final double radius;

	public BoxedCharacter(char c, double size, UFont font, HColor innerCircle, HColor circle, HColor fontColor) {
		this.c = "" + c;
		this.font = font;
		this.innerCircle = innerCircle;
		this.circle = circle;
		this.fontColor = fontColor;
		this.radius = size;
	}

	public void drawU(UGraphic ug) {
		if (circle != null) {
			ug = ug.apply(circle);
		}
		if (innerCircle == null) {
			ug = ug.apply(HColors.none().bg());
		} else {
			ug = ug.apply(innerCircle.bg());
		}
		ug.draw(new URectangle(2 * radius, 2 * radius));
		ug = ug.apply(fontColor);
		ug = ug.apply(new UTranslate(radius, radius));
		ug.draw(new UCenteredCharacter(c.charAt(0), font));
	}

	final public double getPreferredWidth(StringBounder stringBounder) {
		return 2 * radius;
	}

	final public double getPreferredHeight(StringBounder stringBounder) {
		return 2 * radius;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(getPreferredWidth(stringBounder), getPreferredHeight(stringBounder));
	}
}
