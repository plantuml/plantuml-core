package net.sourceforge.plantuml.ugraphic;

import java.util.Map;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.TextBlock;

public interface PlacementStrategy {

	public void add(TextBlock block);

	public Map<TextBlock, XPoint2D> getPositions(double width, double height);

}
