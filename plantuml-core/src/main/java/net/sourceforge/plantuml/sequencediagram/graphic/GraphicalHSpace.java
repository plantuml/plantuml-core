package net.sourceforge.plantuml.sequencediagram.graphic;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.skin.Context2D;

class GraphicalHSpace extends GraphicalElement {

	private final int size;

	public GraphicalHSpace(double startingY, int size) {
		super(startingY);
		this.size = size;
	}

	@Override
	protected void drawInternalU(UGraphic ug, double maxX, Context2D context) {
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		return size;
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		return 0;
	}

	@Override
	public double getStartingX(StringBounder stringBounder) {
		return 0;
	}

}
