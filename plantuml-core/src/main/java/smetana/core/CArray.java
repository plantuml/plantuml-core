
package smetana.core;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.log.Logme;

public class CArray<O> extends UnsupportedC {

	private final Class<O> cl;
	private final List<O> data;
	private final int offset;

	@Override
	public String toString() {
		return "Array " + cl + " offset=" + offset + " [" + data.size() + "]" + data;
	}

	public static <O> CArray<O> ALLOC__(int size, Class<O> cl) {
		final CArray<O> result = new CArray<O>(new ArrayList<O>(), 0, cl);
		result.reallocWithStructure(size);
		return result;
	}

	public static <O> CArray<O> REALLOC__(int size, CArray<O> old, Class<O> cl) {
		if (old == null) {
			return ALLOC__(size, cl);
		}
		old.reallocWithStructure(size);
		return old;
	}

	private CArray(List<O> data, int offset, Class<O> cl) {
		if (offset > 0) {
			// JUtilsDebug.LOG("offset=" + offset);
		}
		this.data = data;
		this.offset = offset;
		this.cl = cl;
	}

	public CArray<O> plus_(int delta) {
		return new CArray<O>(data, offset + delta, cl);
	}

	public int minus_(CArray<O> other) {
		if (this.data != other.data) {
			throw new IllegalArgumentException();
		}
		return this.offset - other.offset;
	}

	public O get__(int i) {
		return data.get(i + offset);
	}

	private void reallocWithStructure(int size) {
		if (offset != 0) {
			throw new IllegalStateException();
		}
		try {
			for (int i = 0; i < size; i++) {
				data.add(cl.getDeclaredConstructor().newInstance());
			}
		} catch (Exception e) {
			Logme.error(e);
			throw new UnsupportedOperationException();
		}
	}

}
