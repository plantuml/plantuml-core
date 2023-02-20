package net.sourceforge.plantuml.core;

public class DiagramDescription {
	private final String description;

	public DiagramDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return description;
	}

}
