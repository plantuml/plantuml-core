package gen.lib.common;
import static smetana.core.debug.SmetanaDebug.ENTERING;
import static smetana.core.debug.SmetanaDebug.LEAVING;

import gen.annotation.Original;
import gen.annotation.Unused;
import h.ST_GVC_s;
import h.ST_dt_s;
import h.ST_pointf;
import h.ST_textspan_t;

public class textspan__c {

//3 n8tcl06mifdn779rzenam44z
// pointf textspan_size(GVC_t *gvc, textspan_t * span) 
@Unused
@Original(version="2.38.0", path="lib/common/textspan.c", name="textspan_size", key="n8tcl06mifdn779rzenam44z", definition="pointf textspan_size(GVC_t *gvc, textspan_t * span)")
public static ST_pointf textspan_size(ST_GVC_s gvc, ST_textspan_t span) {
// WARNING!! STRUCT
return textspan_size_w_(gvc, span).copy();
}
private static ST_pointf textspan_size_w_(ST_GVC_s gvc, ST_textspan_t span) {
ENTERING("n8tcl06mifdn779rzenam44z","textspan_size");
try {
	System.err.println("Warning:textspan_size "+span);
	span.size.x = 30;
	span.size.y = 20;
    return span.size.copy();
} finally {
LEAVING("n8tcl06mifdn779rzenam44z","textspan_size");
}
}



//3 9mfrgcpzz2d9f7nxfgx4nxj2q
// Dt_t * textfont_dict_open(GVC_t *gvc) 
@Unused
@Original(version="2.38.0", path="lib/common/textspan.c", name="textfont_dict_open", key="9mfrgcpzz2d9f7nxfgx4nxj2q", definition="Dt_t * textfont_dict_open(GVC_t *gvc)")
public static ST_dt_s textfont_dict_open(ST_GVC_s gvc) {
ENTERING("9mfrgcpzz2d9f7nxfgx4nxj2q","textfont_dict_open");
try {
	return null;
//UNSUPPORTED("nexd6tbei8przmonjwzag8uf"); // Dt_t * textfont_dict_open(GVC_t *gvc)
//UNSUPPORTED("erg9i1970wdri39osu8hx2a6e"); // {
//UNSUPPORTED("cdeb412fjgrtibum4qt0yxhc7"); //     ( (&(gvc->textfont_disc))->key = (0), (&(gvc->textfont_disc))->size = (sizeof(textfont_t)), (&(gvc->textfont_disc))->link = (-1), (&(gvc->textfont_disc))->makef = (textfont_makef), (&(gvc->textfont_disc))->freef = (textfont_freef), (&(gvc->textfont_disc))->comparf = (textfont_comparf), (&(gvc->textfont_disc))->hashf = (NULL), (&(gvc->textfont_disc))->memoryf = (NULL), (&(gvc->textfont_disc))->eventf = (NULL) );
//UNSUPPORTED("d1t3xr23spgfbbggquvg4nodm"); //     gvc->textfont_dt = dtopen(&(gvc->textfont_disc), Dtoset);
//UNSUPPORTED("6ynzkfpi9sy9wbln45o4jajhp"); //     return gvc->textfont_dt;
//UNSUPPORTED("c24nfmv9i7o5eoqaymbibp7m7"); // }
} finally {
LEAVING("9mfrgcpzz2d9f7nxfgx4nxj2q","textfont_dict_open");
}
}



}
