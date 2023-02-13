package net.sourceforge.plantuml.real;

class RealDelta extends RealMoveable {

	private final Real delegated;
	private final double diff;

	RealDelta(Real delegated, double diff) {
		super(((AbstractReal) delegated).getLine(), "[Delegated {" + delegated.getName() + "} d=" + diff + "]");
		this.delegated = delegated;
		this.diff = diff;
	}

	@Override
	double getCurrentValueInternal() {
		return delegated.getCurrentValue() + diff;
	}

	public Real addAtLeast(double delta) {
		return new RealDelta(delegated.addAtLeast(delta), diff);
	}

	public void ensureBiggerThan(Real other) {
		delegated.ensureBiggerThan(new RealDelta(other, -diff));
	}

	void move(double delta) {
		((RealMoveable) delegated).move(delta);
	}

}
