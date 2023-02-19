
package smetana.core;

import com.plantuml.api.cheerpj.WasmLog;

final public class CArray<O> extends UnsupportedC {

	private final ZType type;
	private final Object[] data;
	private final int offset;

	@Override
	public String toString() {
		return "Array " + type + " offset=" + offset + " [" + data.length + "]" + data;
	}

	public static <O> CArray<O> ALLOC__(int size, ZType type) {
		// WasmLog.log("ALLOC " + size);
		final CArray<O> result = new CArray<O>(new Object[size], 0, type);
		for (int i = 0; i < size; i++)
			result.data[i] = type.create();
		return result;
	}

	public static <O> CArray<O> REALLOC__(int size, CArray<O> old, ZType type) {
		// WasmLog.log("REALLOC " + size);
		if (old == null)
			return ALLOC__(size, type);

		if (size <= old.data.length)
			return old;

		if (old.offset != 0)
			throw new IllegalStateException();

		WasmLog.log("Realloc from " + old.data.length + " to " + size);

		final CArray<O> result = new CArray<O>(new Object[size], 0, type);
		System.arraycopy(old.data, 0, result.data, 0, old.data.length);
		for (int i = old.data.length; i < result.data.length; i++)
			result.data[i] = type.create();
		return result;
	}

	private CArray(Object[] data, int offset, ZType type) {
		this.data = data;
		this.offset = offset;
		this.type = type;
	}

	public CArray<O> plus_(int delta) {
		return new CArray<O>(data, offset + delta, type);
	}

	public int minus_(CArray<O> other) {
		if (this.data != other.data) {
			throw new IllegalArgumentException();
		}
		return this.offset - other.offset;
	}

	public O get__(int i) {
		return (O) data[i + offset];
	}

}
