package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_pathend_t extends UnsupportedStarStruct {


	public final ST_boxf nb = new ST_boxf();
	public final ST_pointf np = new ST_pointf();
	public int sidemask;
	public int boxn[] = new int[1];

	public final ST_boxf boxes[] = new ST_boxf[] { new ST_boxf(), new ST_boxf(), new ST_boxf(), new ST_boxf(),
			new ST_boxf(), new ST_boxf(), new ST_boxf(), new ST_boxf(), new ST_boxf(), new ST_boxf(), new ST_boxf(),
			new ST_boxf(), new ST_boxf(), new ST_boxf(), new ST_boxf(), new ST_boxf(), new ST_boxf(), new ST_boxf(),
			new ST_boxf(), new ST_boxf() };



}

// typedef struct pathend_t {
// boxf nb; /* the node box */
// pointf np; /* node port */
// int sidemask;
// int boxn;
// boxf boxes[20];
// } pathend_t;
