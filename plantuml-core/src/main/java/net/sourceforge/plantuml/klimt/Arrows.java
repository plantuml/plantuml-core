package net.sourceforge.plantuml.klimt;

import net.sourceforge.plantuml.klimt.shape.UPolygon;
import net.sourceforge.plantuml.utils.Direction;

public abstract class Arrows {

	public abstract UPolygon asToUp();

	public abstract UPolygon asToDown();

	public abstract UPolygon asToRight();

	public abstract UPolygon asToLeft();

	public final UPolygon asTo(Direction direction) {
		if (direction == Direction.UP)
			return asToUp();

		if (direction == Direction.DOWN)
			return asToDown();

		if (direction == Direction.LEFT)
			return asToLeft();

		if (direction == Direction.RIGHT)
			return asToRight();

		throw new IllegalArgumentException();
	}

}
