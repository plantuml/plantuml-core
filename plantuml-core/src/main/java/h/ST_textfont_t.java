package h;

import smetana.core.CString;
import smetana.core.UnsupportedStarStruct;

final public class ST_textfont_t extends UnsupportedStarStruct {

	public CString name;
	public double size;





}

// typedef struct {
// char* name;
// char* color;
// PostscriptAlias *postscript_alias;
// double size;
// unsigned int flags:7; /* HTML_UL, HTML_IF, HTML_BF, etc. */
// unsigned int cnt:(sizeof(unsigned int) * 8 - 7); /* reference count */
// } textfont_t;
