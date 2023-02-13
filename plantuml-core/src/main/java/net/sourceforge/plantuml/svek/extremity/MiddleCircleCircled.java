package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class MiddleCircleCircled extends Extremity {

	private final HColor diagramBackColor;
	private final double angle;
	private final MiddleCircleCircledMode mode;
	private final double radius1 = 6;
	private final UEllipse circle = new UEllipse(2 * radius1, 2 * radius1);

	private final double radius2 = 10;
	private final UEllipse bigcircle = new UEllipse(2 * radius2, 2 * radius2);
	private final HColor backColor;

	public MiddleCircleCircled(double angle, MiddleCircleCircledMode mode, HColor backColor, HColor diagramBackColor) {
		this.angle = angle;
		this.mode = mode;
		this.backColor = backColor;
		this.diagramBackColor = diagramBackColor;
	}

	@Override
	public XPoint2D somePoint() {
		return null;
	}

	public void drawU(UGraphic ug) {
		if (mode == MiddleCircleCircledMode.BOTH) {
			ug.apply(diagramBackColor).apply(diagramBackColor.bg())
					.apply(new UTranslate(-radius2, -radius2)).draw(bigcircle);
		}

		ug = ug.apply(backColor.bg());
		ug = ug.apply(new UStroke(1.5));

		final double d = 0;
		if (mode == MiddleCircleCircledMode.MODE1 || mode == MiddleCircleCircledMode.BOTH) {
			final UEllipse arc1 = new UEllipse(2 * radius2, 2 * radius2, angle, 90);
			ug.apply(HColors.none().bg()).apply(new UTranslate(-radius2 + d, -radius2 + d)).draw(arc1);
		}
		if (mode == MiddleCircleCircledMode.MODE2 || mode == MiddleCircleCircledMode.BOTH) {
			final UEllipse arc2 = new UEllipse(2 * radius2, 2 * radius2, angle + 180, 90);
			ug.apply(HColors.none().bg()).apply(new UTranslate(-radius2 + d, -radius2 + d)).draw(arc2);
		}
		ug.apply(new UTranslate(-radius1, -radius1)).draw(circle);
	}

}
