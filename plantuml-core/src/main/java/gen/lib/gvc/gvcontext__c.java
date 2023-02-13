package gen.lib.gvc;
import static smetana.core.debug.SmetanaDebug.ENTERING;
import static smetana.core.debug.SmetanaDebug.LEAVING;

import gen.annotation.Original;
import gen.annotation.Unused;
import h.ST_GVC_s;
import smetana.core.__ptr__;

public class gvcontext__c {


//3 8jwauh4lo3kcvxhomy40s94b
// GVC_t *gvNEWcontext(const lt_symlist_t *builtins, int demand_loading) 
@Unused
@Original(version="2.38.0", path="lib/gvc/gvcontext.c", name="", key="8jwauh4lo3kcvxhomy40s94b", definition="GVC_t *gvNEWcontext(const lt_symlist_t *builtins, int demand_loading)")
public static ST_GVC_s gvNEWcontext(__ptr__ builtins, boolean demand_loading) {
ENTERING("8jwauh4lo3kcvxhomy40s94b","gvNEWcontext");
try {
	ST_GVC_s gvc = new ST_GVC_s();
    if (gvc!=null) {
	gvc.common.info = null;
	gvc.common.errorfn = gen.lib.cgraph.agerror__c.agerrorf;
	gvc.common.builtins = builtins;
	gvc.common.demand_loading = demand_loading;
    }
    return gvc;
} finally {
LEAVING("8jwauh4lo3kcvxhomy40s94b","gvNEWcontext");
}
}





}
