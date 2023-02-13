package net.sourceforge.plantuml.real;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class RealMin extends AbstractReal implements Real {

	private final List<Real> all = new ArrayList<>();

	RealMin(Collection<Real> reals) {
		super(RealMax.line(reals));
		this.all.addAll(reals);
	}

	public String getName() {
		return "min " + all;
	}

	@Override
	double getCurrentValueInternal() {
		double result = all.get(0).getCurrentValue();
		for (int i = 1; i < all.size(); i++) {
			final double v = all.get(i).getCurrentValue();
			if (v < result) {
				result = v;
			}
		}
		return result;
	}

	public Real addFixed(double delta) {
		return new RealDelta(this, delta);
	}

	public Real addAtLeast(double delta) {
		throw new UnsupportedOperationException();
	}

	public void ensureBiggerThan(Real other) {
		for (Real r : all) {
			r.ensureBiggerThan(other);
		}
	}

	public int size() {
		return all.size();
	}

	public void printCreationStackTrace() {
		throw new UnsupportedOperationException();
	}

}
