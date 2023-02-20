package net.sourceforge.plantuml.theme;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import net.sourceforge.plantuml.klimt.sprite.ResourcesUtils;
import net.sourceforge.plantuml.preproc.ReadLine;
import net.sourceforge.plantuml.preproc.ReadLineReader;
import net.sourceforge.plantuml.preproc.Stdlib;
import net.sourceforge.plantuml.utils.Log;

import static com.plantuml.api.cheerpj.StaticMemory.cheerpjPath;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ThemeUtils {

	private static final String THEME_FILE_PREFIX = "puml-theme-";

	private static final String THEME_FILE_SUFFIX = ".puml";

	private static final String THEME_PATH = "themes";

	public static ReadLine getReaderTheme(String filename) throws FileNotFoundException {
	Log.info("Loading theme " + filename);
	final String fullpath = cheerpjPath + THEME_PATH + "/" + THEME_FILE_PREFIX + filename
			+ THEME_FILE_SUFFIX;

	final String res = "/" + THEME_PATH + "/" + THEME_FILE_PREFIX + filename + THEME_FILE_SUFFIX;
	final String description = "<" + res + ">";
	final InputStream is = new FileInputStream(fullpath);
	return ReadLineReader.create(new InputStreamReader(is), description);
}


	public static String getFullPath(String from, String filename) {
		final StringBuilder sb = new StringBuilder(from);
		if (from.endsWith("/") == false) {
			sb.append("/");
		}
		return sb + getFilename(filename);
	}

	public static String getFilename(String filename) {
		return THEME_FILE_PREFIX + filename + THEME_FILE_SUFFIX;
	}

}
