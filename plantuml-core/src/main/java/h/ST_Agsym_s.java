package h;

import smetana.core.CString;
import smetana.core.FieldOffset;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__ptr__;

//2 38c2s12koxcpi2c7vwl72qrsp

final public class ST_Agsym_s extends UnsupportedStarStruct {
	public final ST_dtlink_s link = new ST_dtlink_s(this);
	public CString name; /* attribute's name */
	public CString defval; /* its default value for initialization */

	public int id; /* its index in attr[] */
	public int kind; /* referent object type */
	public int fixed; /* immutable value */
	public int print; /* always print */

	@Override
	public Object getTheField(FieldOffset offset) {
		if (offset == null || offset.getSign() == 0) 
			return this;
		
		if (offset == FieldOffset.name) 
			return name;
		
		if (offset == FieldOffset.link) 
			return link;
		
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSameThan(__ptr__ other) {
		ST_Agsym_s other2 = (ST_Agsym_s) other;
		return this == other2;
	}

}

// struct Agsym_s { /* symbol in one of the above dictionaries */
// Dtlink_t link;
// char *name; /* attribute's name */
// char *defval; /* its default value for initialization */
// int id; /* its index in attr[] */
// unsigned char kind; /* referent object type */
// unsigned char fixed; /* immutable value */
// unsigned char print; /* always print */
// };
