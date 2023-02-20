package net.sourceforge.plantuml.real;

abstract class AbstractReal implements Real {

	private final RealLine line;

	AbstractReal(RealLine line) {
		this.line = line;
		this.line.register2(this);
	}

	final RealLine getLine() {
		return line;
	}

	abstract double getCurrentValueInternal();

	final public double getCurrentValue() {
		final double result = getCurrentValueInternal();
		line.register(result);
		return result;
	}

	public Real getMaxAbsolute() {
		return line.asMaxAbsolute();
	}

	public Real getMinAbsolute() {
		return line.asMinAbsolute();
	}

}
