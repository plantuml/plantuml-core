package net.sourceforge.plantuml.ebnf;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.CopyForegroundColorToBackgroundColor;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ETileWithCircles extends ETile {

	private static final double SIZE = 8;

	private final double deltax = 30;
	private final ETile orig;
	private final HColor lineColor;

	public ETileWithCircles(ETile orig, HColor lineColor) {
		this.orig = orig;
		this.lineColor = lineColor;
	}

	@Override
	public double getWidth(StringBounder stringBounder) {
		return orig.getWidth(stringBounder) + 2 * deltax;
	}

	@Override
	public double getH1(StringBounder stringBounder) {
		return orig.getH1(stringBounder);
	}

	@Override
	public double getH2(StringBounder stringBounder) {
		return orig.getH2(stringBounder);
	}

	@Override
	public void drawU(UGraphic ug) {
		final double linePos = getH1(ug.getStringBounder());
		final XDimension2D fullDim = calculateDimension(ug.getStringBounder());
		ug = ug.apply(lineColor).apply(new UStroke(1.5));
		orig.drawU(ug.apply(UTranslate.dx(deltax)));

		final UEllipse circle = new UEllipse(SIZE, SIZE);

		ug.apply(new UStroke(2)).apply(new UTranslate(0, linePos - SIZE / 2)).draw(circle);
		ug.apply(new UStroke(1)).apply(new CopyForegroundColorToBackgroundColor())
				.apply(new UTranslate(fullDim.getWidth() - SIZE / 2, linePos - SIZE / 2)).draw(circle);

		ug = ug.apply(new UStroke(1.5));
		drawHlineDirected(ug, linePos, SIZE, deltax, 0.5);
		drawHlineDirected(ug, linePos, fullDim.getWidth() - deltax, fullDim.getWidth() - SIZE / 2, 0.5);
	}

	@Override
	public void push(ETile tile) {
		throw new UnsupportedOperationException();
	}

}
