// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class SvgAttributes {

	private final Map<String, String> attributes = new TreeMap<String, String>();

	private SvgAttributes cloneMe() {
		final SvgAttributes result = new SvgAttributes();
		result.attributes.putAll(this.attributes);
		return result;
	}

	private SvgAttributes() {
	}

	public static SvgAttributes empty() {
		return new SvgAttributes();
	}

	public static SvgAttributes build(String args) {
		final SvgAttributes result = new SvgAttributes();
		final Pattern2 p = MyPattern.cmpile("(\\w+)\\s*=\\s*([%g][^%g]*[%g]|(?:\\w+))");
		final Matcher2 m = p.matcher(args);
		while (m.find())
			result.attributes.put(m.group(1), StringUtils.eventuallyRemoveStartingAndEndingDoubleQuote(m.group(2)));

		return result;
	}

	public Map<String, String> attributes() {
		return Collections.unmodifiableMap(attributes);
	}

	public SvgAttributes add(String key, String value) {
		final SvgAttributes result = cloneMe();
		result.attributes.put(key, value);
		return result;
	}

	public SvgAttributes add(SvgAttributes toBeAdded) {
		final SvgAttributes result = cloneMe();
		result.attributes.putAll(toBeAdded.attributes);
		return result;
	}

}
