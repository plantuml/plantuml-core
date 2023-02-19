
package smetana.core;

import java.util.concurrent.atomic.AtomicInteger;

public class UnsupportedStarStruct implements __struct__, __ptr__ {

	 public static int CPT;
	public final int UID;

	public static UnsupportedStarStruct SPY_ME;

	public UnsupportedStarStruct() {
		 this.UID = CPT++;
	}

	final public __ptr__ unsupported() {
		throw new UnsupportedOperationException(getClass().toString());
	}

	public boolean isSameThan(__ptr__ other) {
		throw new UnsupportedOperationException(getClass().toString());
	}

	public __ptr__ castTo(Class dest) {
		System.err.println("I am " + toString() + " " + UID);
		throw new UnsupportedOperationException(dest + " " + getClass().toString());
	}

	public Object getTheField(FieldOffset virtualBytes) {
		throw new UnsupportedOperationException(getClass().toString());
	}

	public __struct__ copy() {
		throw new UnsupportedOperationException(getClass().toString());
	}

	public void ___(__struct__ other) {
		throw new UnsupportedOperationException(getClass().toString());
	}

}
