package net.sourceforge.plantuml.version;

import static net.sourceforge.plantuml.graphic.GraphicPosition.BACKGROUND_CORNER_BOTTOM_RIGHT;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.OptionFlags;
import net.sourceforge.plantuml.PlainStringsDiagram;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.preproc.Stdlib;
import net.sourceforge.plantuml.preproc2.PreprocessorUtils;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.security.SImageIO;
import net.sourceforge.plantuml.security.SecurityProfile;
import net.sourceforge.plantuml.security.SecurityUtils;
import net.sourceforge.plantuml.svek.GraphvizCrash;

public class PSystemVersion extends PlainStringsDiagram {

	PSystemVersion(UmlSource source, boolean withImage, List<String> args) {
		super(source);
		this.strings.addAll(args);
		if (withImage) {
			this.image = getPlantumlImage();
			this.imagePosition = BACKGROUND_CORNER_BOTTOM_RIGHT;
		}
	}

	private PSystemVersion(UmlSource source, List<String> args, BufferedImage image) {
		super(source);
		this.strings.addAll(args);
		this.image = image;
		this.imagePosition = BACKGROUND_CORNER_BOTTOM_RIGHT;
	}

	public static BufferedImage getPlantumlImage() {
		return getImage("logo.png");
	}

	public static BufferedImage getCharlieImage() {
		return getImage("charlie.png");
	}

	public static BufferedImage getTime01() {
		return getImage("time01.png");
	}

	public static BufferedImage getTime15() {
		return getImage("time15.png");
	}

	public static BufferedImage getPlantumlSmallIcon() {
		return getImage("favicon.png");
	}


	private static BufferedImage getImage(final String name) {
		try {
			final InputStream is = PSystemVersion.class.getResourceAsStream(name);
			final BufferedImage image = SImageIO.read(is);
			is.close();
			return image;
		} catch (IOException e) {
			Logme.error(e);
		}
		return new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
	}


	private static BufferedImage transparentIcon;

	public static BufferedImage getPlantumlSmallIcon2() {
		if (transparentIcon != null) {
			return transparentIcon;
		}
		final BufferedImage ico = getPlantumlSmallIcon();
		if (ico == null) {
			return new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		}
		transparentIcon = new BufferedImage(ico.getWidth(), ico.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
		for (int i = 0; i < ico.getWidth(); i++) {
			for (int j = 0; j < ico.getHeight(); j++) {
				final int col = ico.getRGB(i, j);
				if (col != ico.getRGB(0, 0)) {
					transparentIcon.setRGB(i, j, col);
				}
			}
		}
		return transparentIcon;
	}

	public static PSystemVersion createShowVersion2(UmlSource source) {
		final List<String> strings = new ArrayList<>();
		strings.add("<b>PlantUML version " + Version.versionString() + "</b> (" + Version.compileTimeString() + ")");

		return new PSystemVersion(source, true, strings);
	}


	public static PSystemVersion createShowAuthors2(UmlSource source) {
		// Duplicate in OptionPrint
		final List<String> strings = getAuthorsStrings(true);
		return new PSystemVersion(source, true, strings);
	}

	public static List<String> getAuthorsStrings(boolean withTag) {
		final List<String> strings = new ArrayList<>();
		add(strings, "<b>PlantUML version " + Version.versionString() + "</b> (" + Version.compileTimeString() + ")",
				withTag);
		add(strings, "(" + License.getCurrent() + " source distribution)", withTag);
		add(strings, " ", withTag);
		add(strings, "<u>Original idea</u>: Arnaud Roques", withTag);
		add(strings, "<u>Word Macro</u>: Alain Bertucat & Matthieu Sabatier", withTag);
		add(strings, "<u>Word Add-in</u>: Adriaan van den Brand", withTag);
		add(strings, "<u>J2V8 & viz.js integration</u>: Andreas Studer", withTag);
		add(strings, "<u>Official Eclipse Plugin</u>: Hallvard Tr\u00E6tteberg", withTag);
		add(strings, "<u>Original Eclipse Plugin</u>: Claude Durif & Anne Pecoil", withTag);
		add(strings, "<u>Servlet & XWiki</u>: Maxime Sinclair", withTag);
		add(strings, "<u>Docker</u>: David Ducatel", withTag);
		add(strings, "<u>AWS lib</u>: Chris Passarello", withTag);
		add(strings, "<u>Stdlib Icons</u>: tupadr3", withTag);
		add(strings, "<u>Site design</u>: Raphael Cotisson", withTag);
		add(strings, "<u>Logo</u>: Benjamin Croizet", withTag);
		add(strings, "<u>Web Assembly</u>: Sakir Temel", withTag);

		add(strings, " ", withTag);
		add(strings, "https://plantuml.com", withTag);
		add(strings, " ", withTag);
		return strings;
	}

	private static void add(List<String> result, String s, boolean withTag) {
		if (withTag == false)
			s = s.replaceAll("\\</?\\w+\\>", "");

		result.add(s);

	}


//	public static PSystemVersion createDumpStackTrace() throws IOException {
//		final List<String> strings = new ArrayList<>();
//		final Throwable creationPoint = new Throwable();
//		creationPoint.fillInStackTrace();
//		for (StackTraceElement ste : creationPoint.getStackTrace()) {
//			strings.add(ste.toString());
//		}
//		return new PSystemVersion(false, strings);
//	}


//	public static PSystemVersion createPath(UmlSource source) throws IOException {
//		final List<String> strings = new ArrayList<>();
//		strings.add("<u>Current Dir</u>: " + new SFile(".").getPrintablePath());
//		strings.add(" ");
//		strings.add("<u>Default path</u>:");
//		for (SFile f : ImportedFiles.createImportedFiles(null).getPath()) {
//			strings.add(f.getPrintablePath());
//		}
//		return new PSystemVersion(source, true, strings);
//	}

	public DiagramDescription getDescription() {
		return new DiagramDescription("(Version)");
	}

	public List<String> getLines() {
		return Collections.unmodifiableList(strings);
	}

}
