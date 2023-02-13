package net.sourceforge.plantuml.klimt.geom;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XPoint2D;

public interface Positionable extends Moveable {

	public XDimension2D getSize();

	public XPoint2D getPosition();

}
