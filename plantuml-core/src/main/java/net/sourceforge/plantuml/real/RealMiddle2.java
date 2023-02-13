package net.sourceforge.plantuml.real;

class RealMiddle2 extends RealMoveable {

	private final RealMoveable p1;
	private final RealMoveable p2;

	RealMiddle2(RealMoveable p1, RealMoveable p2) {
		super(p1.getLine(), "middle");
		assert p1.getLine() == p2.getLine();
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	double getCurrentValueInternal() {
		return (p1.getCurrentValue() + p2.getCurrentValue()) / 2;
	}

	// public Real addFixed(double diff) {
	// return new RealMiddle2(p1, p2, delta + diff);
	// }

	public Real addAtLeast(double delta) {
		throw new UnsupportedOperationException();
	}

	public void ensureBiggerThan(Real other) {
		getLine().addForce(new PositiveForce(other, this, 0));
	}

	@Override
	void move(double delta) {
		p1.move(delta / 2);
		p2.move(delta / 2);
	}

}
