
package smetana.core;

/**
 * "Pseudo size" of a C structure. In C, this is the actual size of the structure. In Java, this is an indication to
 * know which structure we are going to allocate.
 * 
 * @author Arnaud Roques
 * 
 */
public class size_t {

	public final Class tobeAllocated;
	
	@Override
	public String toString() {
		return super.toString()+" "+tobeAllocated;
	}

	public size_t(Class tobeAllocated) {
		this.tobeAllocated = tobeAllocated;
	}


	public size_t negate() {
		throw new UnsupportedOperationException();
	}


	public size_t multiply(int sz) {
		throw new UnsupportedOperationException();
	}

	public boolean isStrictPositive() {
		return true;
	}

	public boolean isStrictNegative() {
		throw new UnsupportedOperationException();
	}



	public final Class getTobeAllocated() {
		return tobeAllocated;
	}

	public __ptr__ malloc() {
		if (tobeAllocated != null) {
			return Memory.malloc(tobeAllocated);
		}
		throw new UnsupportedOperationException();
	}

	public size_t plus(int strlen) {
		throw new UnsupportedOperationException();
	}

	public boolean isZero() {
		return false;
	}

	
	public __ptr__ realloc(Object old) {
		throw new UnsupportedOperationException();
	}


	public int getInternalNb() {
		throw new UnsupportedOperationException();
	}


}
