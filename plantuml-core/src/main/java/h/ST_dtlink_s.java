package h;

import smetana.core.OFFSET;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__ptr__;
import smetana.core.__struct__;

public class ST_dtlink_s extends UnsupportedStarStruct {

	public ST_dtlink_s right;
	public ST_dtlink_s _left;
	private final __ptr__ container;

	@Override
	public void ___(__struct__ other) {
		ST_dtlink_s this2 = (ST_dtlink_s) other;
		this.right = this2.right;
		this._left = this2._left;
	}

	public ST_dtlink_s() {
		this(null);
	}

	public ST_dtlink_s(__ptr__ parent) {
		this.container = parent;
	}

	public __ptr__ getParent() {
		return container;
	}

	@Override
	public __ptr__ castTo(Class dest) {
		if (dest == ST_dtlink_s.class) {
			return this;
		}
		if (dest == ST_dthold_s.class && getParent() instanceof ST_dthold_s) {
			return (ST_dthold_s) getParent();

		}
		System.err.println("dest=" + dest);
		return super.castTo(dest);
	}



	@Override
	public Object getTheField(OFFSET offset) {
		if (offset == null || offset.getSign() == 0) {
			return this;
		}
		if (offset.getField().equals("s") && container instanceof ST_refstr_t) {
			return ((ST_refstr_t) container).s;

		}
		// Negative because we go back to the parent
		offset = offset.negative();

		return container;
//		if (offset.getKey().equals("h.ST_Agsubnode_s::id_link")) {
//			return ((ST_Agsubnode_s) parent);
//		}
//		if (offset.getKey().equals("h.ST_Agsubnode_s::seq_link")) {
//			return ((ST_Agsubnode_s) parent);
//		}
//		if (offset.getKey().equals("h.ST_Agsym_s::link")) {
//			return ((ST_Agsym_s) parent);
//		}
//		if (offset.getKey().equals("h.ST_Agedge_s::seq_link")) {
//			return ((ST_Agedge_s) parent);
//		}
//		if (offset.getKey().equals("h.ST_Agedge_s::id_link")) {
//			return ((ST_Agedge_s) parent);
//		}
//		if (offset.getKey().equals("h.ST_Agraph_s::link")) {
//			return ((ST_Agraph_s) parent);
//		}
	}

}

// struct _dtlink_s
// { Dtlink_t* right; /* right child */
// union
// { unsigned int _hash; /* hash value */
// Dtlink_t* _left; /* left child */
// } hl;
// };
