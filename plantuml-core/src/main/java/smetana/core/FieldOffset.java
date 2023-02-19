
package smetana.core;

public enum FieldOffset {
	zero(0), externalHolder(-1), //
	link(1), id_link(1), s(1), key(1), seq_link(1), name(1); //

	private final int sign;

	private FieldOffset(int sign) {
		this.sign = sign;
	}

	public int getSign() {
		return sign;
	}

	public FieldOffset negative() {
		// Just to know when we go negative
		return this;
	}

}
