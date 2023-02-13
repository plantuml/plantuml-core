package net.sourceforge.plantuml.command;

import java.util.Objects;

import net.sourceforge.plantuml.klimt.geom.Rankdir;
import net.sourceforge.plantuml.utils.Direction;

public enum Position {
	RIGHT, LEFT, BOTTOM, TOP;

	public static Position fromString(String s) {
		return Position.valueOf(s.toUpperCase());
	}

	public Position withRankdir(Rankdir rankdir) {
		if (Objects.requireNonNull(rankdir) == Rankdir.TOP_TO_BOTTOM) {
			// Default
			return this;
		}
		if (this == RIGHT) {
			return BOTTOM;
		}
		if (this == LEFT) {
			return TOP;
		}
		if (this == BOTTOM) {
			return RIGHT;
		}
		if (this == TOP) {
			return LEFT;
		}
		throw new IllegalStateException();
	}

	public Direction reverseDirection() {
		if (this == LEFT) {
			return Direction.RIGHT;
		}
		if (this == RIGHT) {
			return Direction.LEFT;
		}
		throw new UnsupportedOperationException();
	}
}
