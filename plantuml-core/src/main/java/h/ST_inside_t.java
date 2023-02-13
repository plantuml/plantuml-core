package h;

import smetana.core.CArray;
import smetana.core.UnsupportedStarStruct;

final public class ST_inside_t extends UnsupportedStarStruct {

	public CArray<ST_pointf> a_p;
	public double a_r[];
	public ST_Agnode_s s_n;
	public ST_boxf s_bp;



}

// typedef union inside_t {
// struct {
// pointf* p;
// double* r;
// } a;
// struct {
// node_t* n;
// boxf* bp;
// } s;
// } inside_t;
