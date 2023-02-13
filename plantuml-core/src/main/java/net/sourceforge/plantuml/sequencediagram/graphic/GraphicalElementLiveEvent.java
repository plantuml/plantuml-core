package net.sourceforge.plantuml.sequencediagram.graphic;

import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class GraphicalElementLiveEvent extends GraphicalElement {

	public GraphicalElementLiveEvent(double startingY) {
		super(startingY);
	}

	protected void drawInternalU(UGraphic ug, double maxX, Context2D context) {
	}

	public double getStartingX(StringBounder stringBounder) {
		return 0;
	}

	public double getPreferredWidth(StringBounder stringBounder) {
		return 0;
	}

	public double getPreferredHeight(StringBounder stringBounder) {
		return 0;
	}

}
