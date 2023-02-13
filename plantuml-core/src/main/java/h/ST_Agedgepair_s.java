package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_Agedgepair_s extends UnsupportedStarStruct {

	public final ST_Agedge_s out = new ST_Agedge_s();
	public final ST_Agedge_s in = new ST_Agedge_s();
	
	public ST_Agedgepair_s() {
		this.out.NEXT = in;
		this.in.PREV = out;
	}


}

// struct Agedgepair_s {
// Agedge_t out, in;
// };
