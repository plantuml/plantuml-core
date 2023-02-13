package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_aspect_t extends UnsupportedStarStruct {


	public int prevIterations;
	public int curIterations;
	public int nextIter;
	public int nPasses;
	public int badGraph;





}

// typedef struct aspect_t {
// double targetAR; /* target aspect ratio */
// double combiAR;
// int prevIterations; /* no. of iterations in previous pass */
// int curIterations; /* no. of iterations in current pass */
// int nextIter; /* dynamically adjusted no. of iterations */
// int nPasses; /* bound on no. of top-level passes */
// int badGraph; /* hack: set if graph is disconnected or has
// * clusters. If so, turn off aspect */
// } aspect_t;
