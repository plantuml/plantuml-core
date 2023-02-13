package h;

import smetana.core.CArray;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__ptr__;

final public class ST_object_t extends UnsupportedStarStruct implements ST_Node_t___or_object_t {

	public final ST_pointf pos = new ST_pointf();
	public final ST_pointf sz = new ST_pointf();
	public CArray<ST_xlabel_t> lbl;


	@Override
	public boolean isSameThan(__ptr__ other) {
		ST_object_t other2 = (ST_object_t) other;
		return this == other2;
	}

}

// typedef struct {
// pointf pos; /* Position of lower-left corner of object */
// pointf sz; /* Size of object; may be zero for a point */
// xlabel_t *lbl; /* Label attached to object, or NULL */
// } object_t;
