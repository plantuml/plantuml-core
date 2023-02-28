package net.sourceforge.plantuml.cucadiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.klimt.LineBreakStrategy;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.Style;

public class BodierMap implements Bodier {

	private final List<CharSequence> rawBody = new ArrayList<>();
	private final Map<String, String> map = new LinkedHashMap<String, String>();
	private Entity leaf;

	@Override
	public void muteClassToObject() {
		throw new UnsupportedOperationException();
	}

	public BodierMap() {
	}

	@Override
	public void setLeaf(Entity leaf) {
		this.leaf = Objects.requireNonNull(leaf);

	}

	public static String getLinkedEntry(String s) {
		final Pattern p = Pattern.compile("(\\*-+_*\\>)");
		final Matcher m = p.matcher(s);
		if (m.find()) {
			return m.group(1);
		}
		return null;
	}

	@Override
	public boolean addFieldOrMethod(String s) {
		if (s.contains("=>")) {
			final int x = s.indexOf("=>");
			map.put(s.substring(0, x).trim(), s.substring(x + 2).trim());
			return true;
		} else if (getLinkedEntry(s) != null) {
			final String link = getLinkedEntry(s);
			final int x = s.indexOf(link);
			map.put(s.substring(0, x).trim(), "\0");
			return true;
		}
		return false;
	}

	@Override
	public Display getMethodsToDisplay() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Display getFieldsToDisplay() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasUrl() {
		return false;
	}

	@Override
	public TextBlock getBody(ISkinParam skinParam, final boolean showMethods, final boolean showFields,
			Stereotype stereotype, Style style, FontConfiguration fontConfiguration) {
		final LineBreakStrategy wordWrap = style.wrapWidth();
		return new TextBlockMap(fontConfiguration, skinParam, map, wordWrap);
	}

	@Override
	public List<CharSequence> getRawBody() {
		return Collections.unmodifiableList(rawBody);
	}

}
