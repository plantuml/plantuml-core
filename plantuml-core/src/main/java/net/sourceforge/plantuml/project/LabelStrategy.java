package net.sourceforge.plantuml.project;

import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;

public class LabelStrategy {

	private final LabelPosition position;
	private final HorizontalAlignment alignment;

	public LabelStrategy(LabelPosition position, HorizontalAlignment alignment) {
		this.position = position;
		this.alignment = alignment;
	}

	public boolean titleInFirstColumn() {
		return position == LabelPosition.FIRST_COLUMN;
	}

	public boolean titleInLastColumn() {
		return position == LabelPosition.LAST_COLUMN;
	}

	public boolean titleInside() {
		return position == LabelPosition.LEGACY;
	}

	public boolean rightAligned() {
		return alignment == HorizontalAlignment.RIGHT;
	}

}
