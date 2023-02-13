package gen.lib.cgraph;
import static smetana.core.Macro.AGEDGE;
import static smetana.core.Macro.AGINEDGE;
import static smetana.core.Macro.UNSUPPORTED;
import static smetana.core.debug.SmetanaDebug.ENTERING;
import static smetana.core.debug.SmetanaDebug.LEAVING;

import gen.annotation.Original;
import gen.annotation.Reviewed;
import gen.annotation.Unused;
import h.ST_Agraph_s;
import h.ST_IMapEntry_t;
import h.ST_dt_s;
import smetana.core.CString;

public class imap__c {


//3 mx2krtbgfhcihopw9rw8kcv3
// int aginternalmaplookup(Agraph_t * g, int objtype, char *str, 			unsigned long *result) 
@Unused
@Original(version="2.38.0", path="lib/cgraph/imap.c", name="aginternalmaplookup", key="mx2krtbgfhcihopw9rw8kcv3", definition="int aginternalmaplookup(Agraph_t * g, int objtype, char *str, 			unsigned long *result)")
public static int aginternalmaplookup(ST_Agraph_s g, int objtype, CString str, int result[]) {
ENTERING("mx2krtbgfhcihopw9rw8kcv3","aginternalmaplookup");
try {
 UNSUPPORTED("9xuzgjxqveawe6v2n4x48r93l"); // int aginternalmaplookup(Agraph_t * g, int objtype, char *str,
UNSUPPORTED("a9jw0mphzrt0q739cxcgk2hxw"); // 			unsigned long *result)
UNSUPPORTED("erg9i1970wdri39osu8hx2a6e"); // {
UNSUPPORTED("l4y6zpshfefue2m18wlswfkp"); //     Dict_t *d;
UNSUPPORTED("6ich6qfkkifpsux1v4vgzhiyb"); //     IMapEntry_t *sym, template;
UNSUPPORTED("4uffdlbjda8w15jyto7gd77bw"); //     char *search_str;
UNSUPPORTED("84sccu12ven74lipf2dljgik4"); //     if (objtype == AGINEDGE)
UNSUPPORTED("5q9qhv35w1rsuiuzqkwgshm3p"); // 	objtype = AGEDGE;
UNSUPPORTED("drm2n6i20x3uimml5ooxm9u25"); //     if ((d = g->clos->lookup_by_name[objtype])) {
UNSUPPORTED("9ysphludc93c139uov8ximaj2"); // 	if ((search_str = agstrbind(g, str))) {
UNSUPPORTED("73apfmwxngxpf2twqiokd75ph"); // 	    template.str = search_str;
UNSUPPORTED("1r11yngj3z66q9h8k7rx0ifra"); // 	    sym = (IMapEntry_t *) (*(((Dt_t*)(d))->searchf))((d),(void*)(&template),0000004);
UNSUPPORTED("8watgmdse1o9uhfuhoexemnl2"); // 	    if (sym) {
UNSUPPORTED("68xn6zrkilfqqsosou3z2ym7o"); // 		*result = sym->id;
UNSUPPORTED("a1a1uhff21noh1htwzn6yp831"); // 		return (!(0));
UNSUPPORTED("6t98dcecgbvbvtpycwiq2ynnj"); // 	    }
UNSUPPORTED("flupwh3kosf3fkhkxllllt1"); // 	}
UNSUPPORTED("dvgyxsnyeqqnyzq696k3vskib"); //     }
UNSUPPORTED("297p5iu8oro94tdg9v29bbgiw"); //     return (0);
UNSUPPORTED("c24nfmv9i7o5eoqaymbibp7m7"); // }

throw new UnsupportedOperationException();
} finally {
LEAVING("mx2krtbgfhcihopw9rw8kcv3","aginternalmaplookup");
}
}




//3 ce8fo5gya95enhgssezqs3vav
// void aginternalmapinsert(Agraph_t * g, int objtype, char *str, 			 unsigned long id) 
@Unused
@Original(version="2.38.0", path="lib/cgraph/imap.c", name="aginternalmapinsert", key="ce8fo5gya95enhgssezqs3vav", definition="void aginternalmapinsert(Agraph_t * g, int objtype, char *str, 			 unsigned long id)")
public static Object aginternalmapinsert(Object... arg_) {
UNSUPPORTED("bk4ucrzua03gr9lak6zfm3orp"); // void aginternalmapinsert(Agraph_t * g, int objtype, char *str,
throw new UnsupportedOperationException();
}




@Reviewed(when = "12/11/2020")
@Original(version="2.38.0", path="lib/cgraph/imap.c", name="", key="3r16pkjiksv8i7o961ltxyge6", definition="static IMapEntry_t *find_isym(Agraph_t * g, int objtype, unsigned long id)")
public static ST_IMapEntry_t find_isym(ST_Agraph_s g, int objtype, int id) {
ENTERING("3r16pkjiksv8i7o961ltxyge6","find_isym");
try {
    ST_dt_s d;
    ST_IMapEntry_t isym, itemplate = new ST_IMapEntry_t();
    if (objtype == AGINEDGE)
	objtype = AGEDGE;
    if ((d = g.clos.lookup_by_id[objtype])!=null) {
    UNSUPPORTED("itemplate.id = id;");
	isym = (ST_IMapEntry_t) UNSUPPORTED("(IMapEntry_t *) (*(((Dt_t*)(d))->searchf))((d),(void*)(&itemplate),0000004)");
    } else
	isym = null;
    return isym;
} finally {
LEAVING("3r16pkjiksv8i7o961ltxyge6","find_isym");
}
}




@Reviewed(when = "12/11/2020")
@Original(version="2.38.0", path="lib/cgraph/imap.c", name="", key="foe6bvtujfevsc0f3m8aqln8", definition="char *aginternalmapprint(Agraph_t * g, int objtype, unsigned long id)")
public static CString aginternalmapprint(ST_Agraph_s g, int objtype, int id) {
ENTERING("foe6bvtujfevsc0f3m8aqln8","aginternalmapprint");
try {
	ST_IMapEntry_t isym;
    if ((isym = find_isym(g, objtype, id))!=null)
	return isym.str;
    return null;
} finally {
LEAVING("foe6bvtujfevsc0f3m8aqln8","aginternalmapprint");
}
}


}
