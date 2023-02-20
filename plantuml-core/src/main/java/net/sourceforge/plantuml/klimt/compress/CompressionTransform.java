package net.sourceforge.plantuml.klimt.compress;

import java.util.List;

public class CompressionTransform implements PiecewiseAffineTransform {

	private final List<Slot> all;

	public CompressionTransform(SlotSet slotSet) {
		this.all = slotSet.getSlots();
	}

	public double transform(double v) {
		return v - getCompressDelta(v);
	}

	private double getCompressDelta(double v) {
		double result = 0;
		for (Slot s : all) {
			if (s.getStart() > v) {
				continue;
			}
			if (v > s.getEnd()) {
				result += s.size();
			} else {
				result += v - s.getStart();
			}
		}
		return result;
	}

}
