package net.sourceforge.plantuml.skin.rose;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.LineBreakStrategy;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.SymbolContext;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.skin.AbstractTextualComponent;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.svek.EntityDomain;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ComponentRoseEntity extends AbstractTextualComponent {

	private final TextBlock stickman;
	private final boolean head;

	public ComponentRoseEntity(Style style, Style stereo, Display stringsToDisplay, boolean head,
			ISkinSimple spriteContainer) {
		super(style, stereo, LineBreakStrategy.NONE, 3, 3, 0, spriteContainer, stringsToDisplay, false);

		final SymbolContext biColor = style.getSymbolContext(getIHtmlColorSet());

		this.head = head;
		this.stickman = new EntityDomain(biColor);
	}

	@Override
	protected void drawInternalU(UGraphic ug, Area area) {
		final TextBlock textBlock = getTextBlock();
		final StringBounder stringBounder = ug.getStringBounder();
		final XDimension2D dimStickman = stickman.calculateDimension(stringBounder);
		final double delta = (getPreferredWidth(stringBounder) - dimStickman.getWidth()) / 2;

		if (head) {
			textBlock.drawU(ug.apply(new UTranslate(getTextMiddlePostion(stringBounder), dimStickman.getHeight())));
			ug = ug.apply(UTranslate.dx(delta));
		} else {
			textBlock.drawU(ug.apply(UTranslate.dx(getTextMiddlePostion(stringBounder))));
			ug = ug.apply(new UTranslate(delta, getTextHeight(stringBounder)));
		}
		stickman.drawU(ug);
	}

	private double getTextMiddlePostion(StringBounder stringBounder) {
		return (getPreferredWidth(stringBounder) - getTextWidth(stringBounder)) / 2.0;
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		final XDimension2D dimStickman = stickman.calculateDimension(stringBounder);
		return dimStickman.getHeight() + getTextHeight(stringBounder);
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		final XDimension2D dimStickman = stickman.calculateDimension(stringBounder);
		return Math.max(dimStickman.getWidth(), getTextWidth(stringBounder));
	}

}
