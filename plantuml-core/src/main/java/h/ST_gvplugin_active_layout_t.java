package h;

import smetana.core.CString;
import smetana.core.UnsupportedStarStruct;

final public class ST_gvplugin_active_layout_t extends UnsupportedStarStruct {

	public ST_gvlayout_engine_s engine;
	public EN_layout_type id;
	public ST_gvlayout_features_t features;
	public CString type;


}

// typedef struct gvplugin_active_layout_s {
// gvlayout_engine_t *engine;
// int id;
// gvlayout_features_t *features;
// const char *type;
// } gvplugin_active_layout_t;
