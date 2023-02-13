
package smetana.core;

import java.util.ArrayList;
import java.util.List;

public class CArrayOfStar<O> extends UnsupportedC {

	private final List<O> data;
	private final int offset;
	
	
	@Override
	public String toString() {
		return "*Array offset=" + offset + " [" + data.size() + "]" + data;
	}


	private CArrayOfStar(List<O> data, int offset) {
		this.data = data;
		this.offset = offset;
	}

	public static <O> CArrayOfStar<O> ALLOC(int size, Class cl) {
		final CArrayOfStar<O> result = new CArrayOfStar<O>(new ArrayList<O>(), 0);
		result.realloc(size);
		return result;
	}

	public static <O> CArrayOfStar<O> REALLOC(int size, CArrayOfStar<O> old, Class<O> cl) {
		if (old==null) {
			return ALLOC(size, cl);
		}
		old.realloc(size);
		return old;
	}


	public int comparePointer_(CArrayOfStar<O> other) {
		if (this.data != other.data) {
			throw new IllegalArgumentException();
		}
		return this.offset - other.offset;
	}

	public O get_(int i) {
		return data.get(i + offset);
	}

	public void set_(int i, O value) {
		data.set(i + offset, value);
	}

	public void realloc(int size) {
		if (offset != 0) {
			throw new IllegalStateException();
		}
		for (int i = 0; i < size; i++) {
			data.add(null);
		}
	}

	public CArrayOfStar<O> plus_(int delta) {
		return new CArrayOfStar<O>(data, offset + delta);
	}

	public void _swap(int i, int j) {
		if (offset != 0) {
			throw new IllegalStateException();
		}
		final O e1 = data.get(i);
		final O e2 = data.get(j);
		data.set(i, e2);
		data.set(j, e1);
	}

}
