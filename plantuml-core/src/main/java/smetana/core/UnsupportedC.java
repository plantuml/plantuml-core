
package smetana.core;

import java.util.concurrent.atomic.AtomicInteger;

public class UnsupportedC implements __ptr__ {
	
	public final static AtomicInteger CPT = new AtomicInteger();
	public final int UID;
	
	public UnsupportedC() {
		this.UID = CPT.decrementAndGet();
	}

	public boolean isSameThan(__ptr__ other) {
		throw new UnsupportedOperationException(getClass().toString());
	}

	public __ptr__ castTo(Class dest) {
		throw new UnsupportedOperationException(getClass().toString());
	}

	public Object getTheField(OFFSET bytes) {
		throw new UnsupportedOperationException(getClass().toString());
	}

	final public __ptr__ unsupported() {
		throw new UnsupportedOperationException(getClass().toString());
	}

}
