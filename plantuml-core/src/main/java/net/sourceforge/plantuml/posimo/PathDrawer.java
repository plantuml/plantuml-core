package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.klimt.geom.Positionable;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public interface PathDrawer {

	public void drawPathBefore(UGraphic ug, Positionable start, Positionable end, Path path);

	public void drawPathAfter(UGraphic ug, Positionable start, Positionable end, Path path);

}
