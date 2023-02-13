package h;

import smetana.core.CFunction;
import smetana.core.UnsupportedStarStruct;

final public class ST_splineInfo extends UnsupportedStarStruct {

	public CFunction swapEnds;
	public CFunction splineMerge;
	public boolean ignoreSwap;
	public boolean isOrtho;




}

// typedef struct {
// boolean(*swapEnds) (edge_t * e); /* Should head and tail be swapped? */
// boolean(*splineMerge) (node_t * n); /* Is n a node in the middle of an edge? */
// boolean ignoreSwap; /* Test for swapped edges if false */
// boolean isOrtho; /* Orthogonal routing used */
// } splineInfo;
