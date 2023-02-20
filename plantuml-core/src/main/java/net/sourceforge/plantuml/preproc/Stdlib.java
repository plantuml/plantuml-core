package net.sourceforge.plantuml.preproc;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import net.sourceforge.plantuml.klimt.creole.atom.AtomImg;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.utils.Base64Coder;
import net.sourceforge.plantuml.utils.Log;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static com.plantuml.api.cheerpj.StaticMemory.cheerpjPath;

public class Stdlib {

	public static InputStream getResourceAsStream(String fullname) {
		fullname = fullname.replace(".puml", "");
		fullname = fullname.replace("awslib/", "awslib14/");

		final String fullpath = cheerpjPath + "stdlib/" + fullname + ".puml";
		System.err.println("Trying to read " + fullpath);
		// See https://docs.leaningtech.com/cheerpj/File-System-support
		try {
			return new FileInputStream(fullpath);
		} catch (FileNotFoundException e) {
			System.err.println("Cannot load " + fullpath);
			return null;
		}
	}

}
