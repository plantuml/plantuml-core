package net.sourceforge.plantuml.nwdiag;

import java.util.Set;

import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.shape.UDrawable;

public class VerticalLine implements UDrawable {

	private final double y1;
	private final double y2;
	private final Set<Double> skip;

	public VerticalLine(double y1, double y2, Set<Double> skip) {
		this.y1 = Math.min(y1, y2);
		this.y2 = Math.max(y1, y2);
		this.skip = skip;
	}

	public void drawU(UGraphic ug) {
		ug = ug.apply(HColors.none().bg());
		boolean drawn = false;
		double current = y1;
		UPath path = new UPath();
		path.moveTo(0, current);
		for (Double step : skip) {
			if (step < y1) {
				continue;
			}
			assert step >= y1;
			drawn = true;
			if (step == y2) {
				path.lineTo(0, y2);
			} else {
				path.lineTo(0, Math.min(y2, step - 3));
				if (y2 > step) {
					path.arcTo(4, 4, 0, 0, 1, 0, step + 9);
					continue;
				}
			}
			ug.draw(path);
			path = new UPath();
			current = step + 9;
			path.moveTo(0, current);
			if (current >= y2) {
				break;
			}
		}
		if (drawn == false) {
			path.lineTo(0, y2);
			ug.draw(path);
		}

	}

}
