package net.sourceforge.plantuml.salt.element;

import java.util.List;

import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.style.ISkinSimple;

public class ElementText extends AbstractElement {

	private final TextBlock block;
	private final boolean show;
	private final String text;

	public ElementText(List<String> text, UFont font, ISkinSimple spriteContainer) {
		final FontConfiguration config = FontConfiguration.blackBlueTrue(font);
		this.block = Display.create(text).create(config, HorizontalAlignment.LEFT, spriteContainer);
		this.show = text.get(0).equals(".") == false;
		this.text = text.get(0);
	}

	public XDimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		return block.calculateDimension(stringBounder);
	}

	public void drawU(UGraphic ug, int zIndex, XDimension2D dimToUse) {
		if (zIndex != 0) {
			return;
		}
		if (show) {
			block.drawU(ug);
		}
	}

	public final String getText() {
		return text;
	}

}
