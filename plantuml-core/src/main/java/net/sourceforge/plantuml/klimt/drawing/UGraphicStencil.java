package net.sourceforge.plantuml.klimt.drawing;

import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.creole.Stencil;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.UHorizontalLine;

public class UGraphicStencil extends AbstractUGraphicHorizontalLine {

	private final Stencil stencil;
	private final UStroke defaultStroke;

	public static UGraphic create(UGraphic ug, Stencil stencil, UStroke defaultStroke) {
		return new UGraphicStencil(ug, stencil, defaultStroke);
	}

	public static UGraphic create(UGraphic ug, XDimension2D dim) {
		return new UGraphicStencil(ug, getRectangleStencil(dim), new UStroke());
	}

	private static Stencil getRectangleStencil(final XDimension2D dim) {
		return new Stencil() {
			public double getStartingX(StringBounder stringBounder, double y) {
				return 0;
			}

			public double getEndingX(StringBounder stringBounder, double y) {
				return dim.getWidth();
			}
		};
	}

	private UGraphicStencil(UGraphic ug, Stencil stencil, UStroke defaultStroke) {
		super(ug);
		this.stencil = stencil;
		this.defaultStroke = defaultStroke;
	}

	@Override
	protected AbstractUGraphicHorizontalLine copy(UGraphic ug) {
		return new UGraphicStencil(ug, stencil, defaultStroke);
	}

	@Override
	protected void drawHline(UGraphic ug, UHorizontalLine line, UTranslate translate) {
		line.drawLineInternal(ug, stencil, translate.getDy(), defaultStroke);
		// final UDrawable ud = stencil.convert(line, ug.getStringBounder());
		// ud.drawU(ug.apply(translate));
		// line.drawLine(ug.apply(translate), startingX, endingX, 0, defaultStroke);
	}

}
