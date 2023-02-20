package net.sourceforge.plantuml.ebnf;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class ETileConcatenation extends ETile {

	private final double marginx = 20;
	private final List<ETile> tiles = new ArrayList<>();

	@Override
	public void push(ETile tile) {
		tiles.add(0, tile);
	}

	public void overideFirst(ETile tile) {
		tiles.set(0, tile);
	}

	@Override
	public void drawU(UGraphic ug) {
		final StringBounder stringBounder = ug.getStringBounder();

		final double fullLinePos = getH1(stringBounder);
		double x = 0;
		drawHline(ug, fullLinePos, 0, x);
		for (int i = 0; i < tiles.size(); i++) {
			final ETile tile = tiles.get(i);
			final double linePos = tile.getH1(stringBounder);
			tile.drawU(ug.apply(new UTranslate(x, fullLinePos - linePos)));
			x += tile.calculateDimension(stringBounder).getWidth();
			if (i != tiles.size() - 1) {
				drawHlineDirected(ug, fullLinePos, x, x + marginx, 0.5);
				x += marginx;
			}
		}

	}

	@Override
	public double getH1(StringBounder stringBounder) {
		double result = 0;

		for (ETile tile : tiles)
			result = Math.max(result, tile.getH1(stringBounder));

		return result;
	}

	@Override
	public double getH2(StringBounder stringBounder) {
		double result = 0;

		for (ETile tile : tiles)
			result = Math.max(result, tile.getH2(stringBounder));

		return result;
	}

	@Override
	public double getWidth(StringBounder stringBounder) {
		double width = 0;
		for (int i = 0; i < tiles.size(); i++) {
			final ETile tile = tiles.get(i);
			width += tile.getWidth(stringBounder);
			if (i != tiles.size() - 1)
				width += marginx;
		}
		return width;
	}

	public ETile getFirst() {
		return tiles.get(0);
	}

}
