package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.SvgAttributes;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

class SvgAttributesChange implements FontChange {

	static final Pattern2 pattern = MyPattern.cmpile(Splitter.svgAttributePattern);
	private final SvgAttributes attributes;

	SvgAttributesChange(String s) {
		final Matcher2 m = pattern.matcher(s);
		if (m.find() == false) {
			throw new IllegalStateException();
		}
		attributes = new SvgAttributes(m.group(1));
	}

	public FontConfiguration apply(FontConfiguration initial) {
		return initial.changeAttributes(attributes);
	}

}
