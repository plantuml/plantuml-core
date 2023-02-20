package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;

public enum AlignmentParam {

	arrowMessageAlignment(HorizontalAlignment.LEFT), stateMessageAlignment(HorizontalAlignment.CENTER),
	sequenceMessageAlignment(HorizontalAlignment.LEFT), sequenceMessageTextAlignment(HorizontalAlignment.LEFT),
	sequenceReferenceAlignment(HorizontalAlignment.CENTER), packageTitleAlignment(HorizontalAlignment.CENTER),
	noteTextAlignment(HorizontalAlignment.LEFT);

	private final HorizontalAlignment defaultValue;

	private AlignmentParam(HorizontalAlignment defaultValue) {
		this.defaultValue = defaultValue;
	}

	public final HorizontalAlignment getDefaultValue() {
		return defaultValue;
	}
}
