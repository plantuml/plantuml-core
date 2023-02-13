package net.sourceforge.plantuml.ugraphic;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class PlacementStrategyVisibility extends AbstractPlacementStrategy {

	private final int col2;

	public PlacementStrategyVisibility(StringBounder stringBounder, int col2) {
		super(stringBounder);
		this.col2 = col2;
	}

	public Map<TextBlock, XPoint2D> getPositions(double width, double height) {
		final Map<TextBlock, XPoint2D> result = new LinkedHashMap<>();
		double y = 0;
		for (final Iterator<Map.Entry<TextBlock, XDimension2D>> it = getDimensions().entrySet().iterator(); it
				.hasNext();) {
			final Map.Entry<TextBlock, XDimension2D> ent1 = it.next();
			final Map.Entry<TextBlock, XDimension2D> ent2 = it.next();

			final double height1 = ent1.getValue().getHeight();
			final double height2 = ent2.getValue().getHeight();
			final double maxHeight12 = Math.max(height1, height2);

			result.put(ent1.getKey(), new XPoint2D(0, 2 + y + (maxHeight12 - height1) / 2));
			result.put(ent2.getKey(), new XPoint2D(col2, y + (maxHeight12 - height2) / 2));
			y += maxHeight12;
		}
		return result;
	}

}
