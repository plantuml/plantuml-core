package net.sourceforge.plantuml.project;

public class Load implements Value {

	private final int winks;

	private Load(int winks) {
		this.winks = winks;
	}

	public static Load inWinks(int winks) {
		return new Load(winks);
	}

	public int getFullLoad() {
		return winks * 100;
	}

	@Override
	public String toString() {
		return "(" + winks + ")";
	}

}
