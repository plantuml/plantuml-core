package net.sourceforge.plantuml.klimt;

public class UComment implements UShape {

	private final String comment;

	public UComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}
}
