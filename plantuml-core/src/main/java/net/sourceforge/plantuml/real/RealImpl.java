package net.sourceforge.plantuml.real;

class RealImpl extends RealMoveable implements RealOrigin {

	private double currentValue;

	public RealImpl(String name, RealLine line, double currentValue) {
		super(line, name);
		this.currentValue = currentValue;
	}

	void move(double delta) {
		this.currentValue += delta;
	}

	@Override
	double getCurrentValueInternal() {
		return currentValue;
	}

	public Real addAtLeast(double delta) {
		final RealImpl result = new RealImpl(getName() + ".addAtLeast" + delta, getLine(), this.currentValue + delta);
		getLine().addForce(new PositiveForce(this, result, delta));
		return result;
	}

	public void ensureBiggerThan(Real other) {
		getLine().addForce(new PositiveForce(other, this, 0));
	}

	public void compileNow() {
		getLine().compile();
	}
}
