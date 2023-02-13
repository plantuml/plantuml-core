package h;

import smetana.core.CArray;
import smetana.core.UnsupportedStarStruct;

final public class ST_Ppoly_t extends UnsupportedStarStruct {

	public CArray<ST_pointf> ps;
	public int pn;


	@Override
	public ST_Ppoly_t copy() {
		ST_Ppoly_t result = new ST_Ppoly_t();
		result.ps = this.ps;
		result.pn = this.pn;
		return result;
	}


}

// typedef struct Ppoly_t {
// Ppoint_t *ps;
// int pn;
// } Ppoly_t;
