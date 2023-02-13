package h;

import smetana.core.CArrayOfStar;
import smetana.core.UnsupportedStarStruct;

final public class ST_nodequeue extends UnsupportedStarStruct {


	public CArrayOfStar<ST_Agnode_s> store;
	public CArrayOfStar<ST_Agnode_s> tail;
	public CArrayOfStar<ST_Agnode_s> head;
	public CArrayOfStar<ST_Agnode_s> limit;



}

// typedef struct nodequeue {
// node_t **store, **limit, **head, **tail;
// } nodequeue;
