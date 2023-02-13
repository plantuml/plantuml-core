package net.sourceforge.plantuml.salt;

import java.util.Objects;

public class Terminated<O> {

	private final O element;
	private final Terminator terminator;

	public Terminated(O element, Terminator terminator) {
		this.element = element;
		this.terminator = Objects.requireNonNull(terminator);
	}

	public O getElement() {
		return element;
	}

	public Terminator getTerminator() {
		return terminator;
	}

	public String toString() {
		return element.toString() + " " + terminator;
	}

}
