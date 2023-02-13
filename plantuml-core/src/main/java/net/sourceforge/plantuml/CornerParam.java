package net.sourceforge.plantuml;

public enum CornerParam {
	DEFAULT, diagramBorder, titleBorder, rectangle, person, hexagon, archimate, component, card, agent;

	public String getRoundKey() {
		if (this == DEFAULT) {
			return "roundcorner";
		}
		return name() + "roundcorner";
	}

	public String getDiagonalKey() {
		if (this == DEFAULT) {
			return "diagonalcorner";
		}
		return name() + "diagonalcorner";
	}

}
