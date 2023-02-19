package h;

import smetana.core.FieldOffset;
import smetana.core.__ptr__;
import smetana.core.__struct__;

final public class ST_Agedge_s extends ST_Agobj_s {

	public ST_Agedge_s PREV;
	public ST_Agedge_s NEXT;

	public final ST_Agobj_s base = this;
	public final ST_dtlink_s id_link = new ST_dtlink_s(this);
	public final ST_dtlink_s seq_link = new ST_dtlink_s(this);
	public ST_Agnode_s node;

	public String NAME;

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void ___(__struct__ arg) {
		ST_Agedge_s this2 = (ST_Agedge_s) arg;
		this.tag.___(this2.tag);
		this.data = this2.data;
		this.id_link.___(this2.id_link);
		this.seq_link.___(this2.seq_link);
		this.node = this2.node;
	}

	@Override
	public boolean isSameThan(__ptr__ other) {
		ST_Agedge_s other2 = (ST_Agedge_s) other;
		return this == other2;
	}

	@Override
	public Object getTheField(FieldOffset offset) {
		if (offset == null || offset.getSign() == 0) {
			return this;
		}
		if (offset == FieldOffset.seq_link)
			return seq_link;

		if (offset == FieldOffset.id_link)
			return id_link;

		throw new UnsupportedOperationException();
	}

	public ST_Agedge_s plus_(int pointerMove) {
		if (pointerMove == 1 && NEXT != null) {
			return NEXT;
		}
		if (pointerMove == -1 && PREV != null) {
			return PREV;
		}
		throw new UnsupportedOperationException();
	}
}

// struct Agedge_s {
// Agobj_t base;
// Dtlink_t id_link; /* main graph only */
// Dtlink_t seq_link;
// Agnode_t *node; /* the endpoint node */
// };
