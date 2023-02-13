package h;

import smetana.core.CString;
import smetana.core.UnsupportedStarStruct;

// UNUSED ?
final public class ST_textspan_t extends UnsupportedStarStruct {


	public CString str;

	// "textfont_t *font",
	// "void *layout",
	// "void (*free_layout) (void *layout)",
	// "double yoffset_layout, yoffset_centerline",
	public final ST_pointf size = new ST_pointf();
	public int just;




}

// typedef struct {
// char *str; /* stored in utf-8 */
// textfont_t *font;
// void *layout;
// void (*free_layout) (void *layout); /* FIXME - this is ugly */
// double yoffset_layout, yoffset_centerline;
// pointf size;
// char just; /* 'l' 'n' 'r' */ /* FIXME */
// } textspan_t;
