package net.sourceforge.plantuml.sequencediagram.graphic;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.sequencediagram.InGroupable;
import net.sourceforge.plantuml.sequencediagram.InGroupableList;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.Context2D;

public class GroupingGraphicalElementElse extends GroupingGraphicalElement implements InGroupable {

	private final Component compElse;
	private final Lazy afterY;
	private final boolean parallel;

	public GroupingGraphicalElementElse(double startingY, Component compElse, InGroupableList inGroupableList,
			boolean parallel, Lazy afterY) {
		super(startingY, inGroupableList);
		this.parallel = parallel;
		this.compElse = compElse;
		this.afterY = afterY;
	}

	@Override
	protected void drawInternalU(UGraphic ug, double maxX, Context2D context) {
		final StringBounder stringBounder = ug.getStringBounder();
		final double x1 = getInGroupableList().getMinX(stringBounder);
		final double x2 = getInGroupableList().getMaxX(stringBounder) - getInGroupableList().getHack2();
		ug = ug.apply(new UTranslate(x1, getStartingY()));

		final double height = afterY.getNow() - getStartingY();
		if (height <= 0) {
			return;
		}
		final XDimension2D dim = new XDimension2D(x2 - x1, height);

		if (parallel == false) {
			compElse.drawU(ug, new Area(dim), context);
		}
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		if (parallel) {
			return 0;
		}
		return compElse.getPreferredHeight(stringBounder);
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		return compElse.getPreferredWidth(stringBounder);
	}

	public double getMinX(StringBounder stringBounder) {
		return getStartingX(stringBounder);
	}

	public double getMaxX(StringBounder stringBounder) {
		return getMinX(stringBounder) + getPreferredWidth(stringBounder);
	}

	public String toString(StringBounder stringBounder) {
		return toString();
	}

}
