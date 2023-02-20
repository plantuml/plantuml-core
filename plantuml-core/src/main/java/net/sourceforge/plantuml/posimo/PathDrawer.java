package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.Positionable;

public interface PathDrawer {

	public void drawPathBefore(UGraphic ug, Positionable start, Positionable end, Path path);

	public void drawPathAfter(UGraphic ug, Positionable start, Positionable end, Path path);

}
