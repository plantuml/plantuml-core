package net.sourceforge.plantuml.salt.element;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.URectangle;
import net.sourceforge.plantuml.style.ISkinSimple;

public class ElementMenuEntry extends AbstractElement {

	private final TextBlock block;
	private final String text;
	private HColor background;
	private double xxx;

	public ElementMenuEntry(String text, UFont font, ISkinSimple spriteContainer) {
		final FontConfiguration config = FontConfiguration.blackBlueTrue(font);
		this.block = Display.create(text).create(config, HorizontalAlignment.LEFT, spriteContainer);
		this.text = text;
	}

	public XDimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		if (text.equals("-")) {
			return new XDimension2D(10, 5);
		}
		return block.calculateDimension(stringBounder);
	}

	public void drawU(UGraphic ug, int zIndex, XDimension2D dimToUse) {
		ug = ug.apply(getBlack());
		if (background != null) {
			final XDimension2D dim = getPreferredDimension(ug.getStringBounder(), 0, 0);
			ug.apply(background.bg()).draw(new URectangle(dim.getWidth(), dim.getHeight()));
		}
		block.drawU(ug);
	}

	public double getX() {
		return xxx;
	}

	public void setX(double x) {
		this.xxx = x;
	}

	public String getText() {
		return text;
	}

	public HColor getBackground() {
		return background;
	}

	public void setBackground(HColor background) {
		this.background = background;
	}
}
