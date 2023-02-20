package net.sourceforge.plantuml.skin.rose;

import net.sourceforge.plantuml.klimt.LineBreakStrategy;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.skin.AbstractTextualComponent;
import net.sourceforge.plantuml.skin.ArrowComponent;
import net.sourceforge.plantuml.skin.ArrowConfiguration;
import net.sourceforge.plantuml.skin.Padder;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.ISkinSimple;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;

public abstract class AbstractComponentRoseArrow extends AbstractTextualComponent implements ArrowComponent {

	private final int arrowDeltaX = 10;
	private final int arrowDeltaY = 4;
	private final HColor foregroundColor;
	private final ArrowConfiguration arrowConfiguration;

	public AbstractComponentRoseArrow(Style style, Display stringsToDisplay, ArrowConfiguration arrowConfiguration,
			ISkinSimple spriteContainer, LineBreakStrategy maxMessageSize) {
		super(style, maxMessageSize, 7, 7, 1, spriteContainer, stringsToDisplay, false);

		this.foregroundColor = style.value(PName.LineColor).asColor(getIHtmlColorSet());
		final UStroke stroke = style.getStroke();
		this.arrowConfiguration = arrowConfiguration.withThickness(stroke.getThickness());

	}

	@Override
	final protected TextBlock getTextBlock() {
		final Padder padder = getISkinSimple() instanceof ISkinParam
				? ((ISkinParam) getISkinSimple()).sequenceDiagramPadder()
				: Padder.NONE;

		return padder.apply(super.getTextBlock());
	}

	abstract public double getYPoint(StringBounder stringBounder);

	protected final HColor getForegroundColor() {
		return foregroundColor;
	}

	final protected int getArrowDeltaX() {
		return arrowDeltaX;
	}

	final protected int getArrowDeltaY() {
		return arrowDeltaY;
	}

	@Override
	public final double getPaddingY() {
		return 4;
	}

	public final ArrowConfiguration getArrowConfiguration() {
		return arrowConfiguration;
	}

}
