package net.sourceforge.plantuml.sequencediagram.teoz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Stairs {

	private final List<Step> values = new ArrayList<>();

	public void addStep(Step step) {
		if (step.getIndent() < 0) {
			throw new IllegalArgumentException();
		}
		if (values.size() > 0) {
			final double lastY = values.get(values.size() - 1).getValue();
			if (step.getValue() <= lastY) {
				// throw new IllegalArgumentException();
				return;
			}
		}
		values.add(step);
	}

	public int getMaxIndent() {
		int max = Integer.MIN_VALUE;
		for (Step step : values) {
			final int v = step.getIndent();
			if (v > max) {
				max = v;
			}
		}
		return max;
	}

	public Collection<Step> getSteps() {
		return Collections.unmodifiableCollection(values);
	}

}
