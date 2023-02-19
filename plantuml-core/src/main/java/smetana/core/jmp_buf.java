
package smetana.core;

import static smetana.core.Macro.UNSUPPORTED;

final public class jmp_buf {

	private boolean hasBeenCalled;

	private boolean hasBeenCalled() {
		UNSUPPORTED("hasBeenCalled");
		return hasBeenCalled;
	}

	public void saveCallingEnvironment() {
		// System.err.println("jmp_buf::saveCallingEnvironment");
		hasBeenCalled = true;
	}

}
