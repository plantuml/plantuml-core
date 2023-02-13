package h;

import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

final public class ST_BestPos_t extends UnsupportedStarStruct {

	public int n;
	public double area;
	public final ST_pointf pos = new ST_pointf();

	@Override
	public __struct__ copy() {
		final ST_BestPos_t result = new ST_BestPos_t();
		result.n = this.n;
		result.area = this.area;
		result.pos.___((__struct__) this.pos);
		return result;
	}
	
	public void ___(__struct__ other) {
		ST_BestPos_t this2 = (ST_BestPos_t) other;
		this.n = this2.n;
		this.area = this2.area;
		this.pos.___((__struct__) this2.pos);
	}




	// typedef struct best_p_s {
	// int n;
	// double area;
	// pointf pos;
	// } BestPos_t;
}
