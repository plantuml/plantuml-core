package net.sourceforge.plantuml.klimt.shape;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class CircledCharacter extends AbstractTextBlock implements TextBlock {

	private final String c;
	private final UFont font;
	private final HColor spotBackColor;
	private final HColor spotBorder;
	private final HColor fontColor;
	private final double radius;

	public CircledCharacter(char c, double radius, UFont font, HColor spotBackColor, HColor spotBorder,
			HColor fontColor) {
		this.c = "" + c;
		this.radius = radius;
		this.font = font;
		this.spotBackColor = spotBackColor;
		this.spotBorder = spotBorder;
		this.fontColor = fontColor.getAppropriateColor(spotBackColor);
	}

	public void drawU(UGraphic ug) {
		if (spotBorder != null)
			ug = ug.apply(spotBorder);

		ug = ug.apply(spotBackColor.bg());
		ug.draw(new UEllipse(radius * 2, radius * 2));
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