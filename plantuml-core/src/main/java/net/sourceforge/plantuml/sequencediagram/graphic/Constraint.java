package net.sourceforge.plantuml.sequencediagram.graphic;

import java.util.Objects;

public class Constraint {

	private final Pushable p1;
	private final Pushable p2;
	private double value;

	public Constraint(Pushable p1, Pushable p2) {
		this.p1 = Objects.requireNonNull(p1);
		this.p2 = Objects.requireNonNull(p2);
	}

	public final Pushable getParticipant1() {
		return p1;
	}

	public final Pushable getParticipant2() {
		return p2;
	}

	public final double getValue() {
		return value;
	}

	public final void ensureValue(double newValue) {
		if (newValue > value) {
			this.value = newValue;
		}
	}

	public void push(double delta) {
		value += delta;
	}

	@Override
	public String toString() {
		return "Constraint " + p1 + " " + p2 + " " + value;
	}

}
