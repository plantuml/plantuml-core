package gen.lib.common;
import static smetana.core.JUtils.tolower;
import static smetana.core.debug.SmetanaDebug.ENTERING;
import static smetana.core.debug.SmetanaDebug.LEAVING;

import gen.annotation.Original;
import gen.annotation.Reviewed;
import smetana.core.CString;

public class strcasecmp__c {


@Reviewed(when = "13/11/2020")
@Original(version="2.38.0", path="lib/common/strcasecmp.c", name="strcasecmp", key="22n1uekxezky6gx3cn22ansew", definition="int strcasecmp(const char *s1, const char *s2)")
public static int strcasecmp(CString s1, CString s2) {
ENTERING("22n1uekxezky6gx3cn22ansew","strcasecmp");
try {
    while ((s1.charAt(0) != '\0')
	   && (tolower(s1.charAt(0)) ==
	       tolower(s2.charAt(0)))) {
	s1=s1.plus_(1);
	s2=s2.plus_(1);
    }
    return tolower(s1.charAt(0)) - tolower(s2.charAt(0));
} finally {
LEAVING("22n1uekxezky6gx3cn22ansew","strcasecmp");
}
}


}
