package h;

import smetana.core.CArrayOfStar;
import smetana.core.CString;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

final public class ST_field_t extends UnsupportedStarStruct implements SHAPE_INFO {

	public final ST_pointf size = new ST_pointf(); /* its dimension */
	public final ST_boxf b = new ST_boxf(); /* its placement in node's coordinates */
	public int n_flds;
	public ST_textlabel_t lp; /* n_flds == 0 */
	// struct field_t **fld; /* n_flds > 0 */
	public CArrayOfStar<ST_field_t> fld;
	public CString id; /* user's identifier */
	public boolean LR; /* if box list is horizontal (left to right) */
	public int sides; /* sides of node exposed to field */

	@Override
	public String toString() {
		return id + " sides=" + sides + " n_flds=" + n_flds + " lp=" + lp + " fld=" + fld;
	}

	@Override
	public ST_field_t castTo(Class dest) {
		if (dest == ST_field_t.class) {
			return this;
		}
		return (ST_field_t) super.castTo(dest);
	}

	@Override
	public void ___(__struct__ other) {
		ST_field_t other2 = (ST_field_t) other;
		this.size.___(other2.size);
		this.b.___(other2.b);
		this.n_flds = other2.n_flds;
		this.lp = other2.lp;
		this.fld = other2.fld;
		this.id = other2.id;
		this.LR = other2.LR;
		this.sides = other2.sides;
	}

}
// typedef struct field_t {
// pointf size;		/* its dimension */
// boxf b;			/* its placement in node's coordinates */
// int n_flds;
// textlabel_t *lp;	/* n_flds == 0 */
// struct field_t **fld;	/* n_flds > 0 */
// char *id;		/* user's identifier */
// unsigned char LR;	/* if box list is horizontal (left to right) */
// unsigned char sides;    /* sides of node exposed to field */
// } field_t;
