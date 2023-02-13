package smetana.core;

public class Memory {

	public static __ptr__ malloc(Class theClass) {
		JUtils.LOG("MEMORY::malloc " + theClass);
		return JUtils.create(theClass, null);
	}

	public static __ptr__ malloc(size_t size) {
		return (__ptr__) size.malloc();
	}

	public static __ptr__ realloc(__ptr__ old, size_t size) {
		throw new UnsupportedOperationException("" + old.getClass());
	}

	public static void free(Object arg) {
	}

	public static int identityHashCode(CString data) {
		if (data == null) {
			return 0;
		}
		// int result = 2 * System.identityHashCode(data);
		int result = data.getUid();
		Z.z().all.put(result, data);
		// System.err.println("Memory::identityHashCode data=" + data);
		// System.err.println("Memory::identityHashCode result=" + result + " " + Z.z().all.size());
		return result;
	}

	public static Object fromIdentityHashCode(int hash) {
		// System.err.println("Memory::fromIdentityHashCode hash=" + hash);
		if (hash % 2 != 0) {
			throw new IllegalArgumentException();
		}
		Object result = Z.z().all.get(hash);
		// System.err.println("Memory::fromIdentityHashCode result=" + result);
		if (result == null) {
			throw new UnsupportedOperationException();
		}
		return result;
	}

}
