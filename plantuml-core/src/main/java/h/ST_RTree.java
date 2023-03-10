// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_RTree extends UnsupportedStarStruct {


	public ST_Node_t___ root;
	// "Node_t *root",
	public final ST_SplitQ_t split = new ST_SplitQ_t();
	public int MinFill;
	// "long ElapsedTime",
	// "float UserTime, SystemTime",
	public int Deleting;
	public int StatFlag;
	// "int InsertCount",
	// "int DeleteCount",
	// "int ReInsertCount",
	// "int InSplitCount",
	// "int DeSplitCount",
	public int ElimCount;
	// "int EvalCount",
	// "int InTouchCount",
	// "int DeTouchCount",
	public int SeTouchCount;
	// "int CallCount",
	// "float SplitMeritSum",
	public int RectCount;
	public int NodeCount;
	public int LeafCount, NonLeafCount;

	public int EntryCount;



}

// struct RTree {
// Node_t *root;
//
// SplitQ_t split;
//
// /* balance criterion for node splitting */
// int MinFill;
//
// /* times */
// long ElapsedTime;
// float UserTime, SystemTime;
//
// int Deleting;
//
// /* variables for statistics */
// int StatFlag; /* tells if we are counting or not */
// /* counters affected only when StatFlag set */
// int InsertCount;
// int DeleteCount;
// int ReInsertCount;
// int InSplitCount;
// int DeSplitCount;
// int ElimCount;
// int EvalCount;
// int InTouchCount;
// int DeTouchCount;
// int SeTouchCount;
// int CallCount;
// float SplitMeritSum;
//
// /* counters used even when StatFlag not set */
// int RectCount;
// int NodeCount;
// int LeafCount, NonLeafCount;
// int EntryCount;
// int SearchCount;
// int HitCount;
//
// };
