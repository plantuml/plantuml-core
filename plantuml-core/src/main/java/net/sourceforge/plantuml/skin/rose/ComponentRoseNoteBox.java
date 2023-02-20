package net.sourceforge.plantuml.skin.rose;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.Colors;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.shape.URectangle;
import net.sourceforge.plantuml.skin.AbstractTextualComponent;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.style.ISkinSimple;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;

final public class ComponentRoseNoteBox extends AbstractTextualComponent {

	private final Fashion symbolContext;
	private final double roundCorner;

	public ComponentRoseNoteBox(Style style, Display strings, ISkinSimple spriteContainer, Colors colors) {
		super(style, style.wrapWidth(), 4, 4, 4, spriteContainer, strings, false);

		this.symbolContext = style.getSymbolContext(getIHtmlColorSet(), colors);
		this.roundCorner = style.value(PName.RoundCorner).asInt(false);
	}

	@Override
	final public double getPreferredWidth(StringBounder stringBounder) {
		final double result = getTextWidth(stringBounder) + 2 * getPaddingX();
		return result;
	}

	@Override
	final public double getPreferredHeight(StringBounder stringBounder) {
		return getTextHeight(stringBounder) + 2 * getPaddingY();
	}

	@Override
	public double getPaddingX() {
		return 5;
	}

	@Override
	public double getPaddingY() {
		return 5;
	}

	@Override
	protected void drawInternalU(UGraphic ug, Area area) {
		final StringBounder stringBounder = ug.getStringBounder();
		final int textHeight = (int) getTextHeight(stringBounder);

		int x2 = (int) getTextWidth(stringBounder);
		final double diffX = area.getDimensionToUse().getWidth() - getPreferredWidth(stringBounder);
		if (diffX < 0)
			throw new IllegalArgumentException();

		if (area.getDimensionToUse().getWidth() > getPreferredWidth(stringBounder))
			x2 = (int) (area.getDimensionToUse().getWidth() - 2 * getPaddingX());

		ug = symbolContext.apply(ug);
		final URectangle rect = new URectangle(x2, textHeight).rounded(roundCorner);
		rect.setDeltaShadow(symbolContext.getDeltaShadow());
		ug.draw(rect);
		ug = ug.apply(new UStroke());

		getTextBlock().drawU(ug.apply(new UTranslate(getMarginX1() + diffX / 2, getMarginY())));

	}
}
