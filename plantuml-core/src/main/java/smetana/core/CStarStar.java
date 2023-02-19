
package smetana.core;

final public class CStarStar<O> extends UnsupportedC {

	private final ACCESS<O> access;

	public static <O> CStarStar<O> BUILD(ACCESS<O> access) {
		return new CStarStar<O>(access);
	}

	private CStarStar(ACCESS<O> access) {
		this.access = access;
	}
	
	
	public O star() {
		return access.get();
	}
	
	public void star(O data) {
		access.set(data);
	}


}
