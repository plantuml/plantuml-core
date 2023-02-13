package net.sourceforge.plantuml.ugraphic.comp;

public class Expand implements Comparable<Expand> {

	private final double position;
	private final double extend;
	private final ExpandType type;

	public Expand(ExpandType type, double position, double extend) {
		if (extend <= 0) {
			throw new IllegalArgumentException();
		}
		this.type = type;
		this.position = position;
		this.extend = extend;
	}

	@Override
	public String toString() {
		return "(" + position + "==>+" + extend + " " + type + ")";
	}

	public int compareTo(Expand other) {
		if (this.position < other.position) {
			return -1;
		}
		if (this.position > other.position) {
			return 1;
		}
		return 0;
	}

	public final double getPosition() {
		return position;
	}

	public final double getExtend() {
		return extend;
	}

	public final ExpandType getType() {
		return type;
	}

}
