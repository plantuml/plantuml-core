package net.sourceforge.plantuml.klimt.shape;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.utils.SignatureUtils;

public class UImageSvg implements UShape {

	private final String svg;
	private final double scale;

	public UImageSvg(String svg, double scale) {
		this.svg = Objects.requireNonNull(svg);
		this.scale = scale;
	}

	public String getMD5Hex() {
		return SignatureUtils.getMD5Hex(svg);
	}

	public boolean containsXlink() {
		return svg.contains("xmlns:xlink=\"http://www.w3.org/1999/xlink\"");
	}

	public String getSvg(boolean raw) {
		String result = svg;
		if (raw)
			return result;

		if (result.startsWith("<?xml")) {
			final int idx = result.indexOf("<svg");
			result = result.substring(idx);
		}
		if (result.startsWith("<svg")) {
			final int idx = result.indexOf(">");
			result = "<svg>" + result.substring(idx + 1);
		}
		final String style = extractSvgStyle();
		if (style != null) {
			final String background = extractBackground(style);
			if (background != null)
				result = result.replaceFirst("<g>", "<g><rect fill=\"" + background + "\" style=\"" + style + "\" /> ");

		}
		if (result.startsWith("<svg>") == false)
			throw new IllegalArgumentException();

		return result;
	}

	private String extractBackground(String style) {
		final Pattern p = Pattern.compile("background:([^;]+)");
		final Matcher m = p.matcher(style);
		if (m.find())
			return m.group(1);

		return null;
	}

	private String extractSvgStyle() {
		final Pattern p = Pattern.compile("(?i)\\<svg[^>]+style=\"([^\">]+)\"");
		final Matcher m = p.matcher(svg);
		if (m.find())
			return m.group(1);

		return null;
	}

	public int getData(String name) {
		final Pattern p2 = Pattern.compile("viewBox[= \"\']+([0-9.]+)[\\s,]+([0-9.]+)[\\s,]+([0-9.]+)[\\s,]+([0-9.]+)");
		final Matcher m2 = p2.matcher(svg);
		if (m2.find()) {
			if ("width".equals(name)) {
				final String s = m2.group(3);
				final int width = (int) Double.parseDouble(s);
				return width;
			}
			if ("height".equals(name)) {
				final String s = m2.group(4);
				final int result = (int) Double.parseDouble(s);
				return result;
			}
		}
		final Pattern p = Pattern.compile("(?i)<svg[^>]+" + name + "\\W+(\\d+)");
		final Matcher m = p.matcher(svg);
		if (m.find()) {
			final String s = m.group(1);
			return Integer.parseInt(s);
		}

		throw new IllegalStateException("Cannot find " + name);
	}

	public double getHeight() {
		return this.getData("height") * scale;
	}

	public double getWidth() {
		return this.getData("width") * scale;
	}

	public double getScale() {
		return scale;
	}

}