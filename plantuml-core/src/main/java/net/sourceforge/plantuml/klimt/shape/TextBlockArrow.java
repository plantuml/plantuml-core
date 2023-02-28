package net.sourceforge.plantuml.klimt.shape;

import java.util.Objects;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.utils.Direction;

public class TextBlockArrow extends AbstractTextBlock implements TextBlock {

	private final double size;
	private final Direction arrow;
	private final HColor color;

	public TextBlockArrow(Direction arrow, FontConfiguration fontConfiguration) {
		this.arrow = Objects.requireNonNull(arrow);
		// this.size = fontConfiguration.getFont().getSize2D() * 0 + 30;
		this.size = fontConfiguration.getFont().getSize2D();
		this.color = fontConfiguration.getColor();

	}

	public void drawU(UGraphic ug) {
		ug = ug.apply(color.bg());
		ug = ug.apply(color);
		int triSize = (int) (size * .8 - 3);
		if (triSize % 2 == 1) {
			triSize--;
		}
		final UPolygon triangle = getTriangle(triSize);
		ug.apply(new UTranslate(2, (size - triSize) - 2)).draw(triangle);
	}

	private UPolygon getTriangle(int triSize) {
		final UPolygon triangle = new UPolygon();
		if (arrow == Direction.RIGHT) {
			triangle.addPoint(0, 0);
			triangle.addPoint(triSize, triSize / 2);
			triangle.addPoint(0, triSize);
			triangle.addPoint(0, 0);
		} else if (arrow == Direction.LEFT) {
			triangle.addPoint(triSize, 0);
			triangle.addPoint(0, triSize / 2);
			triangle.addPoint(triSize, triSize);
			triangle.addPoint(triSize, 0);
		} else if (arrow == Direction.UP) {
			triangle.addPoint(0, triSize);
			triangle.addPoint(triSize / 2, 0);
			triangle.addPoint(triSize, triSize);
			triangle.addPoint(0, triSize);
		} else if (arrow == Direction.DOWN) {
			triangle.addPoint(0, 0);
			triangle.addPoint(triSize / 2, triSize);
			triangle.addPoint(triSize, 0);
			triangle.addPoint(0, 0);
		} else {
			throw new IllegalStateException();
		}
		return triangle;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(size, size);
	}
}