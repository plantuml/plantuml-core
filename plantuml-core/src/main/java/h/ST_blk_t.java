package h;

import smetana.core.CArrayOfStar;
import smetana.core.UnsupportedStarStruct;

final public class ST_blk_t extends UnsupportedStarStruct {
	
	public CArrayOfStar<ST_Agnode_s> data;
	public CArrayOfStar<ST_Agnode_s> endp;
	public ST_blk_t prev;
	public ST_blk_t next;



	
}

//typedef struct blk_t {
//Agnode_t **data;
//Agnode_t **endp;
//struct blk_t *prev;
//struct blk_t *next;
//} blk_t;
