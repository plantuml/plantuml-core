package net.sourceforge.plantuml.klimt.geom;

import java.util.Map;

import net.sourceforge.plantuml.klimt.shape.TextBlock;

public interface PlacementStrategy {

	public void add(TextBlock block);

	public Map<TextBlock, XPoint2D> getPositions(double width, double height);

}
