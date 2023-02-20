package net.sourceforge.plantuml.skin.rose;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.Colors;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.creole.Stencil;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.drawing.UGraphicStencil;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.skin.AbstractTextualComponent;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.style.ISkinSimple;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.svek.image.Opale;

final public class ComponentRoseNote extends AbstractTextualComponent implements Stencil {

	private final double paddingX;
	private final double paddingY;
	private final Fashion symbolContext;
	private final double roundCorner;
	private final HorizontalAlignment position;

	public ComponentRoseNote(Style style, Display strings, double paddingX, double paddingY,
			ISkinSimple spriteContainer, HorizontalAlignment textAlignment, HorizontalAlignment position,
			Colors colors) {
		super(style, style.wrapWidth(), textAlignment == HorizontalAlignment.CENTER ? 15 : 6, 15, 5, spriteContainer,
				strings, true);
		this.paddingX = paddingX;
		this.paddingY = paddingY;
		this.position = position;
		this.symbolContext = style.getSymbolContext(getIHtmlColorSet(), colors);
		this.roundCorner = style.value(PName.RoundCorner).asInt(false);

	}

	@Override
	final public double getPreferredWidth(StringBounder stringBounder) {
		final double result = getTextWidth(stringBounder) + 2 * getPaddingX() + symbolContext.getDeltaShadow();
		return result;
	}

	@Override
	final public double getPreferredHeight(StringBounder stringBounder) {
		return getTextHeight(stringBounder) + 2 * getPaddingY() + symbolContext.getDeltaShadow();
	}

	@Override
	public double getPaddingX() {
		return paddingX;
	}

	@Override
	public double getPaddingY() {
		return paddingY;
	}

	@Override
	protected void drawInternalU(UGraphic ug, Area area) {

		final StringBounder stringBounder = ug.getStringBounder();
		final int textHeight = (int) getTextHeight(stringBounder);

		int x2 = (int) getTextWidth(stringBounder);
		final double diffX = area.getDimensionToUse().getWidth() - getPreferredWidth(stringBounder);
		if (diffX < 0) {
			throw new IllegalArgumentException();
		}
		if (area.getDimensionToUse().getWidth() > getPreferredWidth(stringBounder)) {
			x2 = (int) (area.getDimensionToUse().getWidth() - 2 * getPaddingX());
		}

		final UPath polygon = Opale.getPolygonNormal(x2, textHeight, roundCorner);
		polygon.setDeltaShadow(symbolContext.getDeltaShadow());

		ug = symbolContext.apply(ug);
		ug.draw(polygon);

		ug.draw(Opale.getCorner(x2, roundCorner));
		UGraphic ug2 = UGraphicStencil.create(ug, this, new UStroke());

		if (position == HorizontalAlignment.LEFT) {
			ug2 = ug2.apply(new UTranslate(getMarginX1(), getMarginY()));
		} else if (position == HorizontalAlignment.RIGHT) {
			ug2 = ug2.apply(
					new UTranslate(area.getDimensionToUse().getWidth() - getTextWidth(stringBounder), getMarginY()));
		} else {
			ug2 = ug2.apply(new UTranslate(getMarginX1() + diffX / 2, getMarginY()));
		}

		getTextBlock().drawU(ug2);

	}

	public double getStartingX(StringBounder stringBounder, double y) {
		return 0;
	}

	public double getEndingX(StringBounder stringBounder, double y) {
		return getTextWidth(stringBounder);
	}

}
