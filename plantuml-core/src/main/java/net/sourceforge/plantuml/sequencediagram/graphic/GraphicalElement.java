package net.sourceforge.plantuml.sequencediagram.graphic;

import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.ugraphic.UGraphic;

abstract class GraphicalElement {

	private double startingY;

	GraphicalElement(double startingY) {
		this.startingY = startingY;
	}

	void pushToDown(double delta) {
		startingY += delta;
	}

	protected final double getStartingY() {
		return startingY;
	}

	public final void drawU(UGraphic ug, double maxX, Context2D context) {
		// bugnewway
		drawInternalU(ug, maxX, context);
	}

	protected abstract void drawInternalU(UGraphic ug, double maxX, Context2D context);

	public abstract double getStartingX(StringBounder stringBounder);

	public abstract double getPreferredWidth(StringBounder stringBounder);

	public abstract double getPreferredHeight(StringBounder stringBounder);

}
