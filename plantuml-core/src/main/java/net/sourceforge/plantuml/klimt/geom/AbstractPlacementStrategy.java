package net.sourceforge.plantuml.klimt.geom;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.shape.TextBlock;

public abstract class AbstractPlacementStrategy implements PlacementStrategy {

	private final StringBounder stringBounder;
	private final Map<TextBlock, XDimension2D> dimensions = new LinkedHashMap<TextBlock, XDimension2D>();

	public AbstractPlacementStrategy(StringBounder stringBounder) {
		this.stringBounder = stringBounder;
	}

	public void add(TextBlock block) {
		this.dimensions.put(block, block.calculateDimension(stringBounder));
	}

	protected Map<TextBlock, XDimension2D> getDimensions() {
		return dimensions;
	}

	protected double getSumWidth() {
		return getSumWidth(dimensions.values().iterator());
	}

	protected double getSumHeight() {
		return getSumHeight(dimensions.values().iterator());
	}

	protected double getMaxHeight() {
		return getMaxHeight(dimensions.values().iterator());
	}

	protected double getMaxWidth() {
		return getMaxWidth(dimensions.values().iterator());
	}

	protected double getSumWidth(Iterator<XDimension2D> it) {
		double result = 0;
		while (it.hasNext()) {
			result += it.next().getWidth();
		}
		return result;
	}

	protected double getSumHeight(Iterator<XDimension2D> it) {
		double result = 0;
		while (it.hasNext()) {
			result += it.next().getHeight();
		}
		return result;
	}

	protected double getMaxWidth(Iterator<XDimension2D> it) {
		double result = 0;
		while (it.hasNext()) {
			result = Math.max(result, it.next().getWidth());
		}
		return result;
	}

	protected double getMaxHeight(Iterator<XDimension2D> it) {
		double result = 0;
		while (it.hasNext()) {
			result = Math.max(result, it.next().getHeight());
		}
		return result;
	}

	protected final StringBounder getStringBounder() {
		return stringBounder;
	}

}
