package net.sourceforge.plantuml.cucadiagram;

class Elected {

	private final String shortName;
	private final int score;

	@Override
	public String toString() {
		return shortName + "/" + score;
	}

	public Elected(String shortName, int score) {
		this.shortName = shortName;
		this.score = score;
	}

	public final String getShortName() {
		return shortName;
	}

	public final int getScore() {
		return score;
	}

}
