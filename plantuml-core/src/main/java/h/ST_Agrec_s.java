// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package h;

import smetana.core.CString;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__ptr__;

public class ST_Agrec_s extends UnsupportedStarStruct {

	public CString name;
	public ST_Agrec_s next;
	/* following this would be any programmer-defined data */

	@Override
	public boolean isSameThan(__ptr__ other) {
		ST_Agrec_s other2 = (ST_Agrec_s) other;
		return this == other2;
	}

}

// struct Agrec_s {
// char *name;
// Agrec_t *next;
// /* following this would be any programmer-defined data */
// };
