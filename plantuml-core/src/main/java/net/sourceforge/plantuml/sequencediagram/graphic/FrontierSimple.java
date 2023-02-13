package net.sourceforge.plantuml.sequencediagram.graphic;

import java.util.Objects;

class FrontierSimple implements Frontier {

	private final double freeY;

	public FrontierSimple(double freeY) {
		this.freeY = freeY;
	}

	public double getFreeY(ParticipantRange range) {
		Objects.requireNonNull(range);
		return freeY;
	}

	public FrontierSimple add(double delta, ParticipantRange range) {
		Objects.requireNonNull(range);
		return new FrontierSimple(freeY + delta);
	}

}
