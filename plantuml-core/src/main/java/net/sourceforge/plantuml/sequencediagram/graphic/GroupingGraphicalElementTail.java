package net.sourceforge.plantuml.sequencediagram.graphic;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.sequencediagram.InGroupableList;
import net.sourceforge.plantuml.skin.Context2D;

class GroupingGraphicalElementTail extends GroupingGraphicalElement {

	public GroupingGraphicalElementTail(double currentY, InGroupableList inGroupableList, boolean parallel) {
		super(currentY, inGroupableList);
	}

	//
	@Override
	protected void drawInternalU(UGraphic ug, double maxX, Context2D context) {
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		return 0;
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		return 0;
	}

}
