package h;

import smetana.core.CArray;
import smetana.core.UnsupportedStarStruct;

final public class ST_XLabels_t extends UnsupportedStarStruct {

	public CArray<ST_object_t> objs;
	public int n_objs;
	public CArray<ST_xlabel_t> lbls;
	public int n_lbls;
	public ST_label_params_t params;
	public ST_dt_s hdx;
	public ST_RTree spdx;



	



}

// typedef struct XLabels_s {
// object_t *objs;
// int n_objs;
// xlabel_t *lbls;
// int n_lbls;
// label_params_t *params;
//
// Dt_t *hdx; // splay tree keyed with hilbert spatial codes
// RTree_t *spdx; // rtree
//
// } XLabels_t;
