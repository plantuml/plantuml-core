package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_Agclos_s extends UnsupportedStarStruct {

	public final ST_Agdisc_s disc = new ST_Agdisc_s(); /* resource discipline functions */
	public final ST_Agdstate_s state = new ST_Agdstate_s(); /* resource closures */
	public ST_dt_s strdict;
	public final int[] seq = new int[3];
	// "unsigned long seq[3]",
	public ST_Agcbstack_s cb;
	public boolean callbacks_enabled; /* issue user callbacks or hold them? */

	// "Dict_t *lookup_by_name[3]",
	// "Dict_t *lookup_by_id[3]",
	public final ST_dt_s[] lookup_by_id = new ST_dt_s[3];


}

// struct Agclos_s {
// Agdisc_t disc; /* resource discipline functions */
// Agdstate_t state; /* resource closures */
// Dict_t *strdict; /* shared string dict */
// unsigned long seq[3]; /* local object sequence number counter */
// Agcbstack_t *cb; /* user and system callback function stacks */
// unsigned char callbacks_enabled; /* issue user callbacks or hold them? */
// Dict_t *lookup_by_name[3];
// Dict_t *lookup_by_id[3];
// };
