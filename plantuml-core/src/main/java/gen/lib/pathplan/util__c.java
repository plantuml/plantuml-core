package gen.lib.pathplan;
import static smetana.core.debug.SmetanaDebug.ENTERING;
import static smetana.core.debug.SmetanaDebug.LEAVING;

import gen.annotation.Original;
import gen.annotation.Unused;
import h.ST_Ppoly_t;
import h.ST_pointf;
import smetana.core.CArray;
import smetana.core.Z;

public class util__c {


//3 ct6tszngugakbl42zkaqrt7p1
// void make_polyline(Ppolyline_t line, Ppolyline_t* sline) 
@Unused
@Original(version="2.38.0", path="lib/pathplan/util.c", name="make_polyline", key="ct6tszngugakbl42zkaqrt7p1", definition="void make_polyline(Ppolyline_t line, Ppolyline_t* sline)")
public static void make_polyline(ST_Ppoly_t line, ST_Ppoly_t sline) {
	make_polyline_(line.copy(), sline);
}


private static void make_polyline_(ST_Ppoly_t line, ST_Ppoly_t sline) {
ENTERING("ct6tszngugakbl42zkaqrt7p1","make_polyline_");
try {

    int i, j;
    int npts = 4 + 3*(line.pn-2);

    if (npts > Z.z().isz) {
	Z.z().ispline = CArray.<ST_pointf>REALLOC__(npts, Z.z().ispline, ST_pointf.class); 
	Z.z().isz = npts;
    }

    j = i = 0;
    Z.z().ispline.get__(j+1).___(line.ps.get__(i));
    Z.z().ispline.get__(j  ).___(line.ps.get__(i));
    j += 2;
    i++;
    for (; i < line.pn-1; i++) {
        Z.z().ispline.get__(j+2).___(line.ps.get__(i));
        Z.z().ispline.get__(j+1).___(line.ps.get__(i));
        Z.z().ispline.get__(j  ).___(line.ps.get__(i));
	j += 3;
    }
    Z.z().ispline.get__(j+1).___(line.ps.get__(i));
    Z.z().ispline.get__(j  ).___(line.ps.get__(i));
    sline.pn = npts;
    sline.ps = Z.z().ispline;

} finally {
LEAVING("ct6tszngugakbl42zkaqrt7p1","make_polyline_");
}
}


}
