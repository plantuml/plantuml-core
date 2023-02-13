package h;

import static smetana.core.Macro.ND_order;
import static smetana.core.Macro.ND_rank;

import smetana.core.__ptr__;
import smetana.core.__struct__;

final public class ST_Agnode_s extends ST_Agobj_s {

	public final ST_Agobj_s base = this;
	public ST_Agraph_s root;
	public final ST_Agsubnode_s mainsub = new ST_Agsubnode_s();

	public String NAME;

	@Override
	public String toString() {
		try {
			return NAME + " rank=" + ND_rank(this) + " order=" + ND_order(this);
		} catch (Exception e) {
			return NAME;
		}
	}

	@Override
	public void ___(__struct__ arg) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSameThan(__ptr__ other) {
		ST_Agnode_s other2 = (ST_Agnode_s) other;
		return this == other2;
	}

}

// struct Agnode_s {
// Agobj_t base;
// Agraph_t *root;
// Agsubnode_t mainsub; /* embedded for main graph */
// };
