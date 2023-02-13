package net.sourceforge.plantuml.security;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.json.Json;
import net.sourceforge.plantuml.json.JsonValue;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.utils.Log;

public class SecurityUtils {

	public static SecurityProfile getSecurityProfile() {
		return SecurityProfile.UNSECURE;
	}

	public static boolean ignoreThisLink(String url) {
		return false;
	}

	/**
	 * Indicates, that we have no authentication and credentials to access the URL.
	 */
	public static final String NO_CREDENTIALS = "<none>";

	public synchronized static BufferedImage readRasterImage(final ImageIcon imageIcon) {
		final Image tmpImage = imageIcon.getImage();
		if (imageIcon.getIconWidth() == -1)
			return null;

		final BufferedImage image = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
				BufferedImage.TYPE_INT_ARGB);
		image.getGraphics().drawImage(tmpImage, 0, 0, null);
		tmpImage.flush();
		return image;
	}


}
