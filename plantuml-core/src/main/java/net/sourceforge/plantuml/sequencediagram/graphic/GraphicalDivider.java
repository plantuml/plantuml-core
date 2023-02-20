package net.sourceforge.plantuml.sequencediagram.graphic;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.Context2D;

class GraphicalDivider extends GraphicalElement {

	private final Component comp;

	public GraphicalDivider(double startingY, Component comp) {
		super(startingY);
		this.comp = comp;
	}

	@Override
	protected void drawInternalU(UGraphic ug, double maxX, Context2D context) {
		ug = ug.apply(UTranslate.dy(getStartingY()));
		final StringBounder stringBounder = ug.getStringBounder();
		final XDimension2D dim = new XDimension2D(maxX, comp.getPreferredHeight(stringBounder));
		comp.drawU(ug, new Area(dim), context);
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		return comp.getPreferredHeight(stringBounder);
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		return comp.getPreferredWidth(stringBounder);
	}

	@Override
	public double getStartingX(StringBounder stringBounder) {
		return 0;
	}

}
