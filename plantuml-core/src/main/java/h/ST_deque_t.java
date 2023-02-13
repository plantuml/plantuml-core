package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_deque_t extends UnsupportedStarStruct {

	// ---------------
	public ST_pointnlink_t pnlps[];
	public int pnlpn, fpnlpi, lpnlpi, apex;

	public boolean malloc(int newdqn) {
		this.pnlps = new ST_pointnlink_t[newdqn];
		return true;
	}

	public boolean realloc(int newdqn) {
		if (pnlps.length >= newdqn) {
			return true;
		}
		ST_pointnlink_t pnlps2[] = new ST_pointnlink_t[newdqn];
		for (int i = 0; i < pnlps.length; i++) {
			pnlps2[i] = pnlps[i];
		}
		this.pnlps = pnlps2;
		return true;
	}
	// ---------------






}

// typedef struct deque_t {
// pointnlink_t **pnlps;
// int pnlpn, fpnlpi, lpnlpi, apex;
// } deque_t;
