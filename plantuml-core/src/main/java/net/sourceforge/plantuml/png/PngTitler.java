package net.sourceforge.plantuml.png;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.DisplaySection;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.style.Style;

public class PngTitler {

	private final DisplaySection text;

	private final Style style;
	private final HColorSet set;
	private final ISkinSimple spriteContainer;

	public PngTitler(DisplaySection text, Style style, HColorSet set, ISkinSimple spriteContainer) {
		this.style = style;
		this.set = set;
		this.spriteContainer = spriteContainer;
		this.text = text;

	}

	public XDimension2D getTextDimension(StringBounder stringBounder) {
		final TextBlock textBloc = getRibbonBlock();
		if (textBloc == null)
			return null;

		return textBloc.calculateDimension(stringBounder);
	}

	public TextBlock getRibbonBlock() {
		final Display display = text.getDisplay();
		if (display == null)
			return null;

		return style.createTextBlockBordered(display, set, spriteContainer, Style.ID_TITLE);
	}
}
