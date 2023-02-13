package h;

import smetana.core.CArray;
import smetana.core.UnsupportedStarStruct;

final public class ST_splines extends UnsupportedStarStruct {


	public CArray<ST_bezier> list;
	public int size;


}

// typedef struct splines {
// bezier *list;
// int size;
// boxf bb;
// } splines;
