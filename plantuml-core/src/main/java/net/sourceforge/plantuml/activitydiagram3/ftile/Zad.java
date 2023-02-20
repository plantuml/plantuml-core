package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.MinMax;

public class Zad {

	private final List<MinMax> rectangles = new ArrayList<>();

	public void add(MinMax rect) {
		// System.err.println("add " + rect);
		this.rectangles.add(rect);

	}

	public void drawDebug(UGraphic ug) {
		ug = ug.apply(HColors.BLUE.bg()).apply(HColors.RED_LIGHT);
		for (MinMax minMax : rectangles) {
			System.err.println("minmax=" + minMax);
			minMax.drawGray(ug);
		}

	}

	public boolean doesHorizontalCross(Snake snake) {
		for (MinMax minMax : rectangles) {
			if (snake.doesHorizontalCross(minMax)) {
				return true;
			}
		}
		return false;
	}
}
