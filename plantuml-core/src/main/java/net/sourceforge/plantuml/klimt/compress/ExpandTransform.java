package net.sourceforge.plantuml.klimt.compress;

import java.util.Set;
import java.util.TreeSet;

public class ExpandTransform implements PiecewiseAffineTransform {

	private final Set<Expand> all = new TreeSet<>();

	@Override
	public String toString() {
		return all.toString();
	}

	public void addExpandIncludingLimit(double position, double extend) {
		this.all.add(new Expand(ExpandType.INCLUDING_LIMIT, position, extend));
	}

	public void addExpandExcludingLimit(double position, double extend) {
		this.all.add(new Expand(ExpandType.EXCLUDING_LIMIT, position, extend));
	}

	public double transform(final double init) {
		double result = init;
		for (Expand expand : all) {
			if (ExpandType.INCLUDING_LIMIT == expand.getType() && init >= expand.getPosition()) {
				result += expand.getExtend();
			}
			if (ExpandType.EXCLUDING_LIMIT == expand.getType() && init > expand.getPosition()) {
				result += expand.getExtend();
			}
		}
		return result;
	}

}