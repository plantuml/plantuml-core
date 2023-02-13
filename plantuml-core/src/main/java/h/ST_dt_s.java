package h;

import smetana.core.CFunction;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__ptr__;

final public class ST_dt_s extends UnsupportedStarStruct {

	public CFunction searchf;/* search function */

	public ST_dtdisc_s disc; /* method to manipulate objs */
	public ST_dtdata_s data; /* sharable data */
	public CFunction memoryf;/* function to alloc/free memory */
	public ST_dtmethod_s meth; /* dictionary method */

	public int type; /* type information */
	public int nview; /* number of parent view dictionaries */
	public ST_dt_s view; /* next on viewpath */
	public ST_dt_s walk; /* dictionary being walked */
	public __ptr__ user; /* for user's usage */



	@Override
	public boolean isSameThan(__ptr__ other) {
		ST_dt_s other2 = (ST_dt_s) other;
		return this == other2;
	}


}

// struct _dt_s
// { Dtsearch_f searchf;/* search function */
// Dtdisc_t* disc; /* method to manipulate objs */
// Dtdata_t* data; /* sharable data */
// Dtmemory_f memoryf;/* function to alloc/free memory */
// Dtmethod_t* meth; /* dictionary method */
// int type; /* type information */
// int nview; /* number of parent view dictionaries */
// Dt_t* view; /* next on viewpath */
// Dt_t* walk; /* dictionary being walked */
// void* user; /* for user's usage */
// };
