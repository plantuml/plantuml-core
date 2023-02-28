package net.sourceforge.plantuml.klimt.drawing;

import static net.sourceforge.plantuml.utils.ObjectUtils.instanceOfAny;

import net.sourceforge.plantuml.klimt.UBackground;
import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.MinMaxMutable;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.UText;

public class TextLimitFinder extends UGraphicNo {

	private final MinMaxMutable minmax;

	@Override
	public UGraphic apply(UChange change) {
		if (!instanceOfAny(change, UBackground.class, HColor.class, UStroke.class, UTranslate.class))
			throw new UnsupportedOperationException(change.getClass().toString());
		final UTranslate tmp = change instanceof UTranslate ? this.getTranslate().compose((UTranslate) change)
				: this.getTranslate();
		return new TextLimitFinder(this.getStringBounder(), tmp, this.minmax);
	}

	public static TextLimitFinder create(StringBounder stringBounder, boolean initToZero) {
		return new TextLimitFinder(stringBounder, new UTranslate(), MinMaxMutable.getEmpty(initToZero));
	}

	private TextLimitFinder(StringBounder stringBounder, UTranslate translate, MinMaxMutable minmax) {
		super(stringBounder, translate);
		this.minmax = minmax;
	}

	public void draw(UShape shape) {
		if (shape instanceof UText) {
			final double x = getTranslate().getDx();
			final double y = getTranslate().getDy();
			drawText(x, y, (UText) shape);
		}
	}

	private void drawText(double x, double y, UText text) {
		final XDimension2D dim = getStringBounder().calculateDimension(text.getFontConfiguration().getFont(),
				text.getText());
		y -= dim.getHeight() - 1.5;
		minmax.addPoint(x, y);
		minmax.addPoint(x, y + dim.getHeight());
		minmax.addPoint(x + dim.getWidth(), y);
		minmax.addPoint(x + dim.getWidth(), y + dim.getHeight());
	}

	public double getMaxX() {
		return minmax.getMaxX();
	}

	public double getMaxY() {
		return minmax.getMaxY();
	}

	public double getMinX() {
		return minmax.getMinX();
	}

	public double getMinY() {
		return minmax.getMinY();
	}

}