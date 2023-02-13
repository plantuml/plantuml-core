package net.sourceforge.plantuml.skin;

public enum ArrowDirection {
	LEFT_TO_RIGHT_NORMAL, RIGHT_TO_LEFT_REVERSE, SELF, BOTH_DIRECTION;

	public ArrowDirection reverse() {
		switch (this) {
		case LEFT_TO_RIGHT_NORMAL:
			return RIGHT_TO_LEFT_REVERSE;
		case RIGHT_TO_LEFT_REVERSE:
			return LEFT_TO_RIGHT_NORMAL;
		default:
			throw new UnsupportedOperationException();
		}
	}

}
