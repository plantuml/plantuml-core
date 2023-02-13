package net.sourceforge.plantuml.openiconic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.openiconic.data.DummyIcon;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class OpenIcon {

	private SvgPath svgPath;
	private List<String> rawData = new ArrayList<>();
	private final String id;

	public static OpenIcon retrieve(String name) {
		final InputStream is = getResource(name);
		if (is == null)
			return null;

		try {
			return new OpenIcon(is, name);
		} catch (IOException e) {
			Logme.error(e);
			return null;
		}
	}

	OpenIcon(String name) throws IOException {
		this(getResource(name), name);
	}

	private static InputStream getResource(String name) {
		// System.err.println("OPENING " + name);
		return DummyIcon.class.getResourceAsStream(name + ".svg");
	}

	private OpenIcon(InputStream is, String id) throws IOException {
		this.id = id;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String s = null;
			while ((s = br.readLine()) != null) {
				rawData.add(s);
				if (s.contains("<path")) {
					final int x1 = s.indexOf('"');
					final int x2 = s.indexOf('"', x1 + 1);
					svgPath = new SvgPath(s.substring(x1 + 1, x2));
				}
			}
		}
		if (rawData.size() != 3 && rawData.size() != 4)
			throw new IllegalStateException();

	}


	private XDimension2D getDimension(double factor) {
		final String width = getNumber(rawData.get(0), "width");
		final String height = getNumber(rawData.get(0), "height");
		return new XDimension2D(Integer.parseInt(width) * factor, Integer.parseInt(height) * factor);
	}

	private String getNumber(String s, String arg) {
		int x1 = s.indexOf(arg);
		if (x1 == -1)
			throw new IllegalArgumentException();

		x1 = s.indexOf("\"", x1);
		if (x1 == -1)
			throw new IllegalArgumentException();

		final int x2 = s.indexOf("\"", x1 + 1);
		if (x2 == -1)
			throw new IllegalArgumentException();

		return s.substring(x1 + 1, x2);
	}

	public TextBlock asTextBlock(final HColor color, final double factor) {
		return new AbstractTextBlock() {
			public void drawU(UGraphic ug) {
				HColor textColor = color.getAppropriateColor(ug.getParam().getBackcolor());
				svgPath.drawMe(ug.apply(textColor), factor);
			}

			public XDimension2D calculateDimension(StringBounder stringBounder) {
				return getDimension(factor);
			}
		};
	}

}
