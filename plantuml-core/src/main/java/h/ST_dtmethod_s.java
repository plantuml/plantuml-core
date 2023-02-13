package h;

import smetana.core.CFunction;
import smetana.core.UnsupportedStarStruct;

final public class ST_dtmethod_s extends UnsupportedStarStruct {

	public CFunction searchf;
	public int type;


	@Override
	public ST_dtmethod_s copy() {
		final ST_dtmethod_s result = new ST_dtmethod_s();
		result.searchf = this.searchf;
		result.type = this.type;
		return result;
	}
}
// public static List<String> DEFINITION = Arrays.asList(
// "struct _dtmethod_s",
// "{",
// "Dtsearch_f searchf",
// "int  type",
// "}");
