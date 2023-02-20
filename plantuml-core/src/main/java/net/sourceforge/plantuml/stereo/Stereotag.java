package net.sourceforge.plantuml.stereo;

import java.util.Objects;

public class Stereotag {

	private static final String SINGLE = "(\\$[^%s{}%g<>$]+)";

	public static String pattern() {
		return "(" + SINGLE + "([%s]+" + SINGLE + ")*)";
	}

	private String name;

	public Stereotag(String name) {
		if (Objects.requireNonNull(name).startsWith("$")) {
			throw new IllegalArgumentException(name);
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object arg0) {
		return name.equals(((Stereotag) arg0).name);
	}

	@Override
	public String toString() {
		return "$" + name;
	}

}
