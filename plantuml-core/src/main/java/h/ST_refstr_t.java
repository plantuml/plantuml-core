package h;

import smetana.core.CString;
import smetana.core.FieldOffset;

final public class ST_refstr_t extends ST_dtlink_s {

	public int refcnt;
	public CString s;

	@Override
	public Object getTheField(FieldOffset offset) {
		if (offset == null || offset.getSign() == 0)
			return this;

		if (offset == FieldOffset.s)
			return s;

		throw new UnsupportedOperationException();
	}

	public void setString(CString newData) {
		this.s = newData;
		this.s.setParent(this);
	}

}

// typedef struct refstr_t {
// Dtlink_t link;
// unsigned long refcnt;
// char *s;
// char store[1]; /* this is actually a dynamic array */
// } refstr_t;
