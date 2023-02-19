package gen.lib.gvc;
import static gen.lib.cgraph.attr__c.agattr;
import static gen.lib.common.textspan__c.textfont_dict_open;
import static gen.lib.gvc.gvcontext__c.gvNEWcontext;
import static gen.lib.gvc.gvtextlayout__c.gvtextlayout_select;
import static smetana.core.Macro.AGNODE;
import static smetana.core.debug.SmetanaDebug.ENTERING;
import static smetana.core.debug.SmetanaDebug.LEAVING;

import gen.annotation.Original;
import gen.annotation.Unused;
import h.ST_GVC_s;
import smetana.core.CString;
import smetana.core.Globals;

public class gvc__c {


//3 f3vdhir2c7dz3pvmx9d3m4lx1
// GVC_t *gvContext(void) 
@Unused
@Original(version="2.38.0", path="lib/gvc/gvc.c", name="", key="f3vdhir2c7dz3pvmx9d3m4lx1", definition="GVC_t *gvContext(void)")
public static ST_GVC_s gvContext(Globals zz, Object... arg_) {
ENTERING("f3vdhir2c7dz3pvmx9d3m4lx1","gvContext");
try {
	ST_GVC_s gvc;
    agattr(zz, null, AGNODE, new CString("label"), new CString("\\N"));
    /* default to no builtins, demand loading enabled */
    gvc = (ST_GVC_s) gvNEWcontext(null, (true));
    /* builtins don't require LTDL */
    gvc.config_found = 0;
    gvtextlayout_select(gvc);   /* choose best available textlayout plugin immediately */
    textfont_dict_open(gvc);    /* initialize font dict */
    return gvc;
} finally {
LEAVING("f3vdhir2c7dz3pvmx9d3m4lx1","gvContext");
}
}


}
