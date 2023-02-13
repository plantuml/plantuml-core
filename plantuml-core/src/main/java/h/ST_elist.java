package h;

import smetana.core.CArrayOfStar;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

//typedef struct elist {
//edge_t **list;
//int size;
//} elist;

final public class ST_elist extends UnsupportedStarStruct {

	public int size;
	public CArrayOfStar<ST_Agedge_s> list;



	@Override
	public void ___(__struct__ other) {
		ST_elist other2 = (ST_elist) other;
		this.size = other2.size;
		this.list = other2.list;
	}

	@Override
	public ST_elist copy() {
		final ST_elist result = new ST_elist();
		result.size = this.size;
		result.list = this.list;
		return result;
	}


}

