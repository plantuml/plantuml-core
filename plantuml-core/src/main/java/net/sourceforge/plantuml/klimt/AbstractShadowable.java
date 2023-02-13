package net.sourceforge.plantuml.klimt;

public abstract class AbstractShadowable implements Shadowable {

	private double deltaShadow;

	public double getDeltaShadow() {
		return deltaShadow;
	}

	public void setDeltaShadow(double deltaShadow) {
		this.deltaShadow = deltaShadow;
	}

}
