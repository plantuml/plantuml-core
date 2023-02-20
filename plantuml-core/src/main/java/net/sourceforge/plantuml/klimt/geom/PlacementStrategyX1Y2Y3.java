package net.sourceforge.plantuml.klimt.geom;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.shape.TextBlock;

public class PlacementStrategyX1Y2Y3 extends AbstractPlacementStrategy {

	public PlacementStrategyX1Y2Y3(StringBounder stringBounder) {
		super(stringBounder);
	}

	public Map<TextBlock, XPoint2D> getPositions(double width, double height) {
		final XDimension2D first = getDimensions().values().iterator().next();

		final double maxWidthButFirst = getMaxWidth(butFirst());
		final double sumHeightButFirst = getSumHeight(butFirst());

		final double space = (width - first.getWidth() - maxWidthButFirst) / 3;

		final Map<TextBlock, XPoint2D> result = new LinkedHashMap<>();
		// double x = space * 2;

		final Iterator<Map.Entry<TextBlock, XDimension2D>> it = getDimensions().entrySet().iterator();
		final Map.Entry<TextBlock, XDimension2D> ent = it.next();
		double y = (height - ent.getValue().getHeight()) / 2;
		result.put(ent.getKey(), new XPoint2D(space, y));

		// x += ent.getValue().getWidth() + space;

		y = (height - sumHeightButFirst) / 2;
		while (it.hasNext()) {
			final Map.Entry<TextBlock, XDimension2D> ent2 = it.next();
			final TextBlock textBlock = ent2.getKey();
			final XDimension2D dim = getDimensions().get(textBlock);
			final double x = 2 * space + first.getWidth() + (maxWidthButFirst - dim.getWidth()) / 2;
			result.put(textBlock, new XPoint2D(x, y));
			y += ent2.getValue().getHeight();
		}
		return result;
	}

	private Iterator<XDimension2D> butFirst() {
		final Iterator<XDimension2D> iterator = getDimensions().values().iterator();
		iterator.next();
		return iterator;
	}

}
