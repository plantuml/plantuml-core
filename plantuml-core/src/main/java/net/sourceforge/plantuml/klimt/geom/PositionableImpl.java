package net.sourceforge.plantuml.klimt.geom;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XPoint2D;

public final class PositionableImpl implements Positionable {

	private XPoint2D pos;

	private final XDimension2D dim;

	public PositionableImpl(double x, double y, XDimension2D dim) {
		this.pos = new XPoint2D(x, y);
		this.dim = dim;
	}

	public PositionableImpl(XPoint2D pt, XDimension2D dim) {
		this(pt.getX(), pt.getY(), dim);
	}

	public XPoint2D getPosition() {
		return pos;
	}

	public XDimension2D getSize() {
		return dim;
	}

	public void moveSvek(double deltaX, double deltaY) {
		this.pos = this.pos.move(deltaX, deltaY);
	}

}
