// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package h;

import smetana.core.FieldOffset;
import smetana.core.UnsupportedStarStruct;

final public class ST_HDict_t extends UnsupportedStarStruct {

	public final ST_dtlink_s link = new ST_dtlink_s(this);
	public int key;
	public final ST_Branch_t d = new ST_Branch_t(); /* Should be ST_Leaf_t */

	@Override
	public Object getTheField(FieldOffset offset) {
		if (offset == null || offset.getSign() == 0)
			return this;

		if (offset == FieldOffset.key)
			return key;

		throw new UnsupportedOperationException();
	}

}

// typedef struct obyh {
// Dtlink_t link;
// int key;
// Leaf_t d;
// } HDict_t;
