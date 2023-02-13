package net.sourceforge.plantuml.real;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sourceforge.plantuml.log.Logme;

class RealMax extends AbstractReal implements Real {

	private final List<Real> all = new ArrayList<>();
	private final Throwable creationPoint;

	RealMax(Collection<Real> reals) {
		super(line(reals));
		this.all.addAll(reals);
		this.creationPoint = new Throwable();
		this.creationPoint.fillInStackTrace();
	}

	static RealLine line(Collection<Real> reals) {
		return ((AbstractReal) reals.iterator().next()).getLine();
	}

	public String getName() {
		return "max " + all;
	}

	@Override
	double getCurrentValueInternal() {
		double result = all.get(0).getCurrentValue();
		for (int i = 1; i < all.size(); i++) {
			Throwable t = new Throwable();
			t.fillInStackTrace();
			final int stackLength = t.getStackTrace().length;
			if (stackLength > 1000) {
				System.err.println("The faulty RealMax " + getName());
				System.err.println("has been created here:");
				printCreationStackTrace();
				throw new IllegalStateException("Infinite recursion?");
			}
			final double v = all.get(i).getCurrentValue();
			if (v > result) {
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
		throw new UnsupportedOperationException();
	}

	public void printCreationStackTrace() {
		Logme.error(creationPoint);
	}

}
