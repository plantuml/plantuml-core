package h;

import smetana.core.CString;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

final public class ST_arrowname_t extends UnsupportedStarStruct {

	public CString name;
	public int type;

	@Override
	public void ___(__struct__ other) {
		ST_arrowname_t other2 = (ST_arrowname_t) other;
		this.name = other2.name == null ? null : other2.name.duplicate();
		this.type = other2.type;
	}


}

// typedef struct arrowname_t {
// char *name;
// int type;
// } arrowname_t;
