package gen.lib.common;
import static smetana.core.Macro.UNSUPPORTED;
import static smetana.core.debug.SmetanaDebug.ENTERING;
import static smetana.core.debug.SmetanaDebug.LEAVING;

import gen.annotation.Original;
import gen.annotation.Unused;
import h.ST_Agnode_s;
import h.ST_boxf;
import h.ST_textlabel_t;
import smetana.core.CString;
import smetana.core.__ptr__;

public class htmltable__c {


@Unused
@Original(version="2.38.0", path="lib/common/htmltable.c", name="html_port", key="9uxvepjmnfsmf2vy6czz4hgkq", definition="boxf *html_port(node_t * n, char *pname, int *sides)")
public static ST_boxf html_port(ST_Agnode_s n, CString pname, int sides[]) {
ENTERING("9uxvepjmnfsmf2vy6czz4hgkq","html_port");
try {
UNSUPPORTED("7w3zjklk3hfz7iikzn0ljroxx"); //     htmllabel_t *lbl = ND_label(n)->u.html;
UNSUPPORTED("aydqqzhyziv7lgf18ih236m1d"); //     boxf *rv = NULL;
UNSUPPORTED("bxxfl0a5dpbgktosi79rwsvee"); //     if (lbl->kind == 2)
UNSUPPORTED("11hwqop4xebvtcskop4uhpp01"); // 	return NULL;
UNSUPPORTED("avhthu8guhst319hhy3gzojcv"); //     tp = portToTbl(lbl->u.tbl, pname);
UNSUPPORTED("7hdtys3akqmtbw7kbkm786zc"); //     if (tp) {
UNSUPPORTED("bu515ksy06cwdblsq7802i1ex"); // 	rv = &tp->box;
UNSUPPORTED("1ah6txqnf2ldpui2wwgjncilp"); // 	*sides = tp->sides;
UNSUPPORTED("dvgyxsnyeqqnyzq696k3vskib"); //     }
UNSUPPORTED("v7vqc9l7ge2bfdwnw11z7rzi"); //     return rv;
UNSUPPORTED("c24nfmv9i7o5eoqaymbibp7m7"); // }

throw new UnsupportedOperationException();
} finally {
LEAVING("9uxvepjmnfsmf2vy6czz4hgkq","html_port");
}
}

//3 39z2hrj2uwkezxobreqxpgb10
// int make_html_label(void *obj, textlabel_t * lp) 
@Unused
@Original(version="2.38.0", path="lib/common/htmltable.c", name="make_html_label", key="39z2hrj2uwkezxobreqxpgb10", definition="int make_html_label(void *obj, textlabel_t * lp)")
public static int make_html_label(__ptr__ obj, ST_textlabel_t lp) {
ENTERING("39z2hrj2uwkezxobreqxpgb10","make_html_label");
try {
	UNSUPPORTED("3s2xr5n4swgcjctfsv6qg00p1"); // int make_html_label(void *obj, textlabel_t * lp)

throw new UnsupportedOperationException();
} finally {
LEAVING("39z2hrj2uwkezxobreqxpgb10","make_html_label");
}
}


}
