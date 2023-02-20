package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

class FontFamilyChange implements FontChange {

	static private final Pattern2 colorPattern = MyPattern.cmpile(Splitter.fontFamilyPattern);

	private final String family;

	FontFamilyChange(String s) {
		final Matcher2 matcherColor = colorPattern.matcher(s);
		if (matcherColor.find() == false) {
			throw new IllegalArgumentException();
		}
		this.family = StringUtils.trin(matcherColor.group(1));
	}

	public FontConfiguration apply(FontConfiguration initial) {
		return initial.changeFamily(family);
	}

}
