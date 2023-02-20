package net.sourceforge.plantuml.sequencediagram.graphic;

import java.util.Objects;

import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.sequencediagram.InGroupableList;

abstract class GroupingGraphicalElement extends GraphicalElement {

	private final InGroupableList inGroupableList;

	public GroupingGraphicalElement(double currentY, InGroupableList inGroupableList) {
		super(currentY);
		this.inGroupableList = Objects.requireNonNull(inGroupableList);
	}

	final public double getActualWidth(StringBounder stringBounder) {
		return Math.max(getPreferredWidth(stringBounder), inGroupableList.getMaxX(stringBounder)
				- inGroupableList.getMinX(stringBounder) + 2 * InGroupableList.MARGIN10);
	}

	@Override
	final public double getStartingX(StringBounder stringBounder) {
		return inGroupableList.getMinX(stringBounder) - InGroupableList.MARGIN10;
	}

	protected final InGroupableList getInGroupableList() {
		return inGroupableList;
	}

}
