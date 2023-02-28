package net.sourceforge.plantuml.klimt.creole.command;

import java.awt.image.BufferedImage;
import java.io.IOException;

import net.sourceforge.plantuml.FileSystem;
import net.sourceforge.plantuml.FileUtils;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.klimt.geom.ImgValign;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.TileImage;
import net.sourceforge.plantuml.klimt.shape.TileImageSvg;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.security.SURL;

public class Img implements HtmlCommand {

	final static private Pattern2 srcPattern = MyPattern.cmpile("src[%s]*=[%s]*[\"%q]?([^%s\">]+)[\"%q]?");
	final static private Pattern2 vspacePattern = MyPattern.cmpile("vspace[%s]*=[%s]*[\"%q]?(\\d+)[\"%q]?");
	final static private Pattern2 valignPattern = MyPattern
			.cmpile("valign[%s]*=[%s]*[\"%q]?(top|bottom|middle)[\"%q]?");
	final static private Pattern2 noSrcColonPattern = MyPattern.cmpile(Splitter.imgPatternNoSrcColon);

	private final TextBlock tileImage;

	private Img(TextBlock image) {
		this.tileImage = image;
	}

	static int getVspace(String html) {
		final Matcher2 m = vspacePattern.matcher(html);
		if (m.find() == false)
			return 0;

		return Integer.parseInt(m.group(1));
	}

	static ImgValign getValign(String html) {
		final Matcher2 m = valignPattern.matcher(html);
		if (m.find() == false)
			return ImgValign.TOP;

		return ImgValign.valueOf(StringUtils.goUpperCase(m.group(1)));
	}

	static HtmlCommand getInstance(String html, boolean withSrc) {
		if (withSrc) {
			final Matcher2 m = srcPattern.matcher(html);
			final int vspace = getVspace(html);
			final ImgValign valign = getValign(html);
			return build(m, valign, vspace);
		}
		final Matcher2 m = noSrcColonPattern.matcher(html);
		return build(m, ImgValign.TOP, 0);
	}

	private static HtmlCommand build(final Matcher2 m, final ImgValign valign, final int vspace) {
		if (m.find() == false) {
			return new PlainText("(SYNTAX ERROR)");
		}
		final String src = m.group(1);
		try {
			final SFile f = FileSystem.getInstance().getFile(src);
			if (f.exists() == false) {
				// Check if valid URL
				if (src.startsWith("http:") || src.startsWith("https:")) {
					final SURL tmp = SURL.create(src);
					if (tmp == null)
						return new PlainText("(Cannot decode: " + src + ")");

					final BufferedImage read = tmp.readRasterImageFromURL();
					if (read == null)
						return new PlainText("(Cannot decode: " + src + ")");

					return new Img(new TileImage(read, valign, vspace));
				}
				return new PlainText("(Cannot decode: " + f + ")");
			}
			if (f.getName().endsWith(".svg")) {
				final String tmp = FileUtils.readSvg(f);
				if (tmp == null)
					return new PlainText("(Cannot decode: " + f + ")");

				return new Img(new TileImageSvg(tmp, 1));
			}
			final BufferedImage read = f.readRasterImageFromFile();
			if (read == null)
				return new PlainText("(Cannot decode: " + f + ")");

			return new Img(new TileImage(f.readRasterImageFromFile(), valign, vspace));
		} catch (IOException e) {
			Logme.error(e);
			return new PlainText("ERROR " + e.toString());
		}
	}

	public TextBlock createMonoImage() {
		return tileImage;
	}

}