package h;

import smetana.core.UnsupportedStarStruct;
import smetana.core.__ptr__;

final public class ST_dtdata_s extends UnsupportedStarStruct {

	public int type; /* type of dictionary */
	public ST_dtlink_s here; /* finger to last search element */
	public __ptr__ _htab; /* hash table */
	public ST_dtlink_s _head = null;
	// Dtlink_t* _head; /* linked list */
	// } hh;
	public int ntab; /* number of hash slots */
	public int size; /* number of objects */
	public int loop; /* number of nested loops */
	public int minp; /* min path before splay, always even */


}

// struct _dtdata_s
// { int type; /* type of dictionary */
// Dtlink_t* here; /* finger to last search element */
// union
// { Dtlink_t** _htab; /* hash table */
// Dtlink_t* _head; /* linked list */
// } hh;
// int ntab; /* number of hash slots */
// int size; /* number of objects */
// int loop; /* number of nested loops */
// int minp; /* min path before splay, always even */
// /* for hash dt, > 0: fixed table size */
// };
