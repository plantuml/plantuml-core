package net.atmp;

public enum InnerStrategy {
	STRICT, LAZZY;

	public boolean check(final String data, String candidate) {
		if (this == STRICT)
			return data.startsWith(candidate);

		if (this == LAZZY)
			return data.contains(candidate);

		throw new IllegalStateException();
	}

}
