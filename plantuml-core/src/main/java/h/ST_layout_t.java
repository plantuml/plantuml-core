package h;

import smetana.core.CString;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__ptr__;

final public class ST_layout_t extends UnsupportedStarStruct {

	public double quantum;
	public double scale;
	public double ratio;
	public double dpi;

	public ST_pointf margin = new ST_pointf();
	public ST_pointf page = new ST_pointf();
	public ST_pointf size = new ST_pointf();

	public boolean filled;
	public boolean landscape;
	public boolean centered;

	// "ratio_t ratio_kind",
	public EN_ratio_t ratio_kind = EN_ratio_t.R_NONE;
	public __ptr__ xdots; // Always null
	public CString id; // Not used



	
}

// typedef struct layout_t {
// double quantum;
// double scale;
// double ratio; /* set only if ratio_kind == R_VALUE */
// double dpi;
// pointf margin;
// pointf page;
// pointf size;
// boolean filled;
// boolean landscape;
// boolean centered;
// ratio_t ratio_kind;
// void* xdots;
// char* id;
// } layout_t;
