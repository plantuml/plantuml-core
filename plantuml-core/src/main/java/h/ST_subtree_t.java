package h;

import smetana.core.UnsupportedStarStruct;
import smetana.core.__ptr__;

final public class ST_subtree_t extends UnsupportedStarStruct {

	public ST_Agnode_s rep;		/* some node in the tree */
	public int size; 			/* total tight tree size */
	public int heap_index; 		/* required to find non-min elts when merged */
	public ST_subtree_t par; 	/* union find */
	
	
	@Override
	public boolean isSameThan(__ptr__ other) {
		ST_subtree_t other2 = (ST_subtree_t) other;
		return this == other2;
	}

}

//typedef struct subtree_s {
//node_t *rep;            /* some node in the tree */
//int    size;            /* total tight tree size */
//int    heap_index;      /* required to find non-min elts when merged */
//struct subtree_s *par;  /* union find */
//} subtree_t;
