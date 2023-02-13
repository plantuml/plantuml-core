package net.sourceforge.plantuml.project;

public class Failable<O> {

	private final O data;
	private final String error;

	public static <O> Failable<O> ok(O data) {
		return new Failable<>(data, null);
	}

	public static <O> Failable<O> error(String error) {
		return new Failable<>(null, error);
	}

	private Failable(O data, String error) {
		if (data == null && error == null) {
			throw new IllegalArgumentException();
		}
		if (data != null && error != null) {
			throw new IllegalArgumentException();
		}
		this.data = data;
		this.error = error;
	}

	public O get() {
		if (data == null) {
			throw new IllegalStateException();
		}
		return data;
	}

	public boolean isFail() {
		return data == null;
	}

	public String getError() {
		if (error == null) {
			throw new IllegalStateException();
		}
		return error;
	}

}
