package net.sourceforge.plantuml.klimt.shape;

import net.sourceforge.plantuml.klimt.UShape;

public class UComment implements UShape {

	private final String comment;

	public UComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}
}