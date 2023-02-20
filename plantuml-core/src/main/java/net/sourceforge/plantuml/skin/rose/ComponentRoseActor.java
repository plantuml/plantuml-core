package net.sourceforge.plantuml.skin.rose;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.LineBreakStrategy;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.skin.AbstractTextualComponent;
import net.sourceforge.plantuml.skin.ActorStyle;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.style.ISkinSimple;
import net.sourceforge.plantuml.style.Style;

public class ComponentRoseActor extends AbstractTextualComponent {

	private final TextBlock stickman;
	private final boolean head;

	public ComponentRoseActor(ActorStyle actorStyle, Style style, Style stereo, Display stringsToDisplay, boolean head,
			ISkinSimple spriteContainer) {
		super(style, stereo, LineBreakStrategy.NONE, 3, 3, 0, spriteContainer, stringsToDisplay, false);
		this.head = head;
		final Fashion biColor = style.getSymbolContext(getIHtmlColorSet());
		this.stickman = actorStyle.getTextBlock(biColor);
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
