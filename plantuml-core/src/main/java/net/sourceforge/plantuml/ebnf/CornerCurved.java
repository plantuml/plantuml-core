package net.sourceforge.plantuml.ebnf;

import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.ugraphic.CopyForegroundColorToBackgroundColor;
import net.sourceforge.plantuml.ugraphic.UGraphic;

enum CornerType {
	NW, NE, SE, SW;
}

public class CornerCurved implements UDrawable {

	private final double delta;
	private final CornerType type;
	private final boolean withArrow;

	private CornerCurved(CornerType type, double delta, boolean withArrow) {
		this.delta = delta;
		this.type = type;
		this.withArrow = withArrow;
		if (delta <= 0)
			throw new IllegalArgumentException();
	}

	public static UDrawable createSW(double delta) {
		return new CornerCurved(CornerType.SW, delta, false);
	}

	public static UDrawable createSE(double delta) {
		return new CornerCurved(CornerType.SE, delta, false);
	}

	public static UDrawable createNE(double delta) {
		return new CornerCurved(CornerType.NE, delta, false);
	}

	public static UDrawable createNE_arrow(double delta) {
		return new CornerCurved(CornerType.NE, delta, true);
	}

	public static UDrawable createNW(double delta) {
		return new CornerCurved(CornerType.NW, delta, false);
	}

	public static UDrawable createNW_arrow(double delta) {
		return new CornerCurved(CornerType.NW, delta, true);
	}

	@Override
	public void drawU(UGraphic ug) {
		final UPath path = new UPath();
		final double a = delta / 4;

		switch (type) {
		case SW:
			path.moveTo(0, -delta);
			path.cubicTo(0, -a, a, 0, delta, 0);
			break;
		case SE:
			path.moveTo(0, -delta);
			path.cubicTo(0, -a, -a, 0, -delta, 0);
			break;
		case NE:
			path.moveTo(-delta, 0);
			path.cubicTo(-a, 0, 0, a, 0, delta);
			if (withArrow)
				ug.apply(new CopyForegroundColorToBackgroundColor()).apply(UTranslate.dy(delta - 5))
						.draw(ETile.getArrowToBottom());
			break;
		case NW:
			path.moveTo(0, delta);
			path.cubicTo(0, a, a, 0, delta, 0);
			if (withArrow)
				ug.apply(new CopyForegroundColorToBackgroundColor()).apply(UTranslate.dy(delta))
						.draw(ETile.getArrowToTop());
			break;
		}

		ug.draw(path);
	}

}
