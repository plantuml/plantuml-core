
package smetana.core.debug;

public class SmetanaDebug {

	public static boolean TRACE = false;
	public static boolean TRACE_FINAL_CALL = false;
	public static boolean VERY_VERBOSE = false;

	private static Purify purify;

	private static Purify purify() {
		if (purify == null)
			purify = new Purify();
		return purify;
	}

	static public void LOG(String s) {
		if (TRACE)
			purify().logline(s);

	}

	static public void ENTERING(String signature, String methodName) {
		if (TRACE)
			purify().entering(signature, methodName);
	}

	static public void LEAVING(String signature, String methodName) {
		if (TRACE)
			purify().leaving(signature, methodName);
	}

	public static void reset() {
		if (TRACE)
			purify().reset();
	}

	public static void printMe() {
		if (TRACE && TRACE_FINAL_CALL)
			purify().printMe();
	}

}
