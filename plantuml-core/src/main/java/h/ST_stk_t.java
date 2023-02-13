package h;

import smetana.core.CArrayOfStar;
import smetana.core.UnsupportedStarStruct;

final public class ST_stk_t extends UnsupportedStarStruct {
	
	public ST_blk_t fstblk;
	public ST_blk_t curblk;
	public CArrayOfStar<ST_Agnode_s> curp;


}

//typedef struct {
//blk_t *fstblk;
//blk_t *curblk;
//Agnode_t **curp;
//} stk_t;
