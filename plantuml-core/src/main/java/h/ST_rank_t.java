// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package h;

import smetana.core.CArrayOfStar;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

//typedef struct rank_t {
//int n; /* number of nodes in this rank */
//node_t **v; /* ordered list of nodes in rank */
//int an; /* globally allocated number of nodes */
//node_t **av; /* allocated list of nodes in rank */
//double ht1, ht2; /* height below/above centerline */
//double pht1, pht2; /* as above, but only primitive nodes */
//boolean candidate; /* for transpose () */
//boolean valid;
//int cache_nc; /* caches number of crossings */
//adjmatrix_t *flat;
//} rank_t;

final public class ST_rank_t extends UnsupportedStarStruct {

	public int n;
	public CArrayOfStar<ST_Agnode_s> v;
	public int an;
	public CArrayOfStar<ST_Agnode_s> av;

	public double ht1, ht2;
	public double pht1, pht2;
	public boolean candidate;
	public int valid;

	public int cache_nc;
	public ST_adjmatrix_t flat;

	@Override
	public String toString() {
		return "RANK n=" + n + " v=" + v + " an=" + an + " av=" + av;
	}

	@Override
	public void ___(__struct__ other) {
		ST_rank_t this2 = (ST_rank_t) other;
		this.n = this2.n;
		this.v = this2.v;
		this.an = this2.an;
		this.av = this2.av;
		this.ht1 = this2.ht1;
		this.ht2 = this2.ht2;
		this.pht1 = this2.pht1;
		this.pht2 = this2.pht2;
		this.candidate = this2.candidate;
		this.valid = this2.valid;
		this.cache_nc = this2.cache_nc;
		this.flat = this2.flat;
	}

}
