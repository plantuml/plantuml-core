package net.sourceforge.plantuml.activitydiagram;

import java.util.Objects;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.utils.Direction;

public class ConditionalContext {

	private final Entity branch;
	private final Direction direction;
	private final ConditionalContext parent;

	public ConditionalContext(ConditionalContext parent, Entity branch, Direction direction) {
		this.branch = Objects.requireNonNull(branch);
		if (branch.getLeafType() != LeafType.BRANCH) {
			throw new IllegalArgumentException();
		}
		this.direction = direction;
		this.parent = parent;
	}

	public Direction getDirection() {
		return direction;
	}

	public final ConditionalContext getParent() {
		return parent;
	}

	public final Entity getBranch() {
		return branch;
	}

}
