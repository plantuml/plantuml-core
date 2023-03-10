// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package gen.lib.cdt;
import static smetana.core.Macro.DT_LIST;
import static smetana.core.Macro.DT_OBAG;
import static smetana.core.Macro.DT_OSET;
import static smetana.core.Macro.DT_QUEUE;
import static smetana.core.Macro.DT_STACK;
import static smetana.core.Macro.UNFLATTEN;
import static smetana.core.debug.SmetanaDebug.ENTERING;
import static smetana.core.debug.SmetanaDebug.LEAVING;

import gen.annotation.Original;
import gen.annotation.Reviewed;
import h.ST_dt_s;
import h.ST_dtlink_s;

public class dtsize__c {

/*	Return the # of objects in the dictionary
**
**	Written by Kiem-Phong Vo (5/25/96)
*/
@Reviewed(when = "14/11/2020")
@Original(version="2.38.0", path="lib/cdt/dtsize.c", name="treecount", key="6j49zum5hqto1t7fyrz8qjv1u", definition="static int treecount(register Dtlink_t* e)")
public static int treecount(ST_dtlink_s e) {
ENTERING("6j49zum5hqto1t7fyrz8qjv1u","treecount");
try {
	return e!=null ? treecount(e._left) + treecount(e.right) + 1 : 0;
} finally {
LEAVING("6j49zum5hqto1t7fyrz8qjv1u","treecount");
}
}




@Reviewed(when = "12/11/2020")
@Original(version="2.38.0", path="lib/cdt/dtsize.c", name="dtsize", key="bci0ea1fa7egf4aads6gdgvsq", definition="int dtsize(Dt_t* dt)")
public static int dtsize_(ST_dt_s dt) {
ENTERING("bci0ea1fa7egf4aads6gdgvsq","dtsize_");
try {
	ST_dtlink_s	t;
	int		size;

	UNFLATTEN(dt);

	if(dt.data.size < 0) /* !(dt->data->type&(DT_SET|DT_BAG)) */
	{	if((dt.data.type&(DT_OSET|DT_OBAG))!=0)
			dt.data.size = treecount(dt.data.here);
		else if((dt.data.type&(DT_LIST|DT_STACK|DT_QUEUE))!=0)
		{	size=0;
		    for(t = dt.data._head; t!=null; t = t.right)
				size += 1;
			dt.data.size = size;
		}
	}
	return dt.data.size;

} finally {
LEAVING("bci0ea1fa7egf4aads6gdgvsq","dtsize_");
}
}


}
