
package smetana.core;

public class OFFSET {

	private final String field;
	private final int sign;

	public int getSign() {
		return sign;
	}

	public OFFSET(String field) {
		this.field = field;
		this.sign = 1;
	}

	private OFFSET(int sign) {
		this.field = null;
		this.sign = sign;
	}

	@Override
	public String toString() {
		if (field == null)
			return "[" + sign + "]";
		return "[" + field + "]";
	}

	public static OFFSET externalHolder() {
		return new OFFSET(-1);
	}

	public static OFFSET zero() {
		return new OFFSET(0);
	}

	public OFFSET negative() {
		// Just to know when we go negative
		return this;
	}

	public String getField() {
		return field;
	}

}
