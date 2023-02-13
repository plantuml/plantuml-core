package h;

import smetana.core.CFunction;
import smetana.core.OFFSET;
import smetana.core.UnsupportedStarStruct;

final public class ST_dtdisc_s extends UnsupportedStarStruct {

	public OFFSET key; /* where the key begins in an object */
	public int size; /* key size and type */
	public OFFSET link; /* offset to Dtlink_t field */
	public CFunction makef; /* object constructor */
	public CFunction freef; /* object destructor */
	public CFunction comparf;/* to compare two objects */
	public CFunction hashf; /* to compute hash value of an object */
	public CFunction memoryf;/* to allocate/free memory */
	public CFunction eventf; /* to process events */



}

// struct _dtdisc_s
// { int key; /* where the key begins in an object */
// int size; /* key size and type */
// int link; /* offset to Dtlink_t field */
// Dtmake_f makef; /* object constructor */
// Dtfree_f freef; /* object destructor */
// Dtcompar_f comparf;/* to compare two objects */
// Dthash_f hashf; /* to compute hash value of an object */
// Dtmemory_f memoryf;/* to allocate/free memory */
// Dtevent_f eventf; /* to process events */
// };
