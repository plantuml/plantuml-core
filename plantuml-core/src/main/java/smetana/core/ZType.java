
package smetana.core;

import h.ST_Agattr_s;
import h.ST_Agclos_s;
import h.ST_Agdatadict_s;
import h.ST_Agedge_s;
import h.ST_Agedgeinfo_t;
import h.ST_Agedgepair_s;
import h.ST_Agnode_s;
import h.ST_Agnodeinfo_t;
import h.ST_Agraph_s;
import h.ST_Agraphinfo_t;
import h.ST_Agsubnode_s;
import h.ST_Agsym_s;
import h.ST_Pedge_t;
import h.ST_bezier;
import h.ST_dtdata_s;
import h.ST_dthold_s;
import h.ST_field_t;
import h.ST_object_t;
import h.ST_path;
import h.ST_pointf;
import h.ST_rank_t;
import h.ST_textspan_t;
import h.ST_tna_t;
import h.ST_triangle_t;
import h.ST_xlabel_t;

public enum ZType {
	ST_Agedgepair_s, //
	ST_Agsym_s, //
	ST_dthold_s, //
	ST_path, //
	ST_Agedgeinfo_t, //
	ST_Agnodeinfo_t, //
	ST_Agraphinfo_t, //
	ST_Agattr_s, //
	ST_Agdatadict_s, //
	ST_dtdata_s, //
	ST_Agraph_s, //
	ST_Agsubnode_s, //
	ST_Agnode_s, //
	ST_Agclos_s, //
	ST_pointf, ST_bezier, ST_Pedge_t, ST_object_t, ST_xlabel_t, ST_triangle_t, ST_Agedge_s, ST_tna_t, ST_rank_t,
	ST_textspan_t, ST_field_t; //

	public __ptr__ create() {
		switch (this) {
		case ST_field_t:
			return new ST_field_t();

		case ST_textspan_t:
			return new ST_textspan_t();

		case ST_rank_t:
			return new ST_rank_t();

		case ST_tna_t:
			return new ST_tna_t();

		case ST_Agedge_s:
			return new ST_Agedge_s();

		case ST_triangle_t:
			return new ST_triangle_t();

		case ST_object_t:
			return new ST_object_t();

		case ST_xlabel_t:
			return new ST_xlabel_t();

		case ST_Pedge_t:
			return new ST_Pedge_t();

		case ST_bezier:
			return new ST_bezier();

		case ST_pointf:
			return new ST_pointf();

		case ST_Agedgepair_s:
			return new ST_Agedgepair_s();

		case ST_Agsym_s:
			return new ST_Agsym_s();

		case ST_dthold_s:
			return new ST_dthold_s();

		case ST_path:
			return new ST_path();

		case ST_Agedgeinfo_t:
			return new ST_Agedgeinfo_t();

		case ST_Agnodeinfo_t:
			return new ST_Agnodeinfo_t();

		case ST_Agraphinfo_t:
			return new ST_Agraphinfo_t();

		case ST_Agattr_s:
			return new ST_Agattr_s();

		case ST_Agdatadict_s:
			return new ST_Agdatadict_s();

		case ST_dtdata_s:
			return new ST_dtdata_s();

		case ST_Agraph_s:
			return new ST_Agraph_s();

		case ST_Agsubnode_s:
			return new ST_Agsubnode_s();

		case ST_Agnode_s:
			return new ST_Agnode_s();

		case ST_Agclos_s:
			return new ST_Agclos_s();
		}

		throw new UnsupportedOperationException(this.toString());
	}
}
