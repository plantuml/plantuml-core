package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

class ColorAndSizeChange implements FontChange {

	static final Pattern2 colorPattern = MyPattern.cmpile("color\\s*=\\s*[%g]?(#[0-9a-fA-F]{6}|\\w+)[%g]?");

	static final Pattern2 sizePattern = MyPattern.cmpile("size\\s*=\\s*[%g]?(\\d+)[%g]?");

	private final HColor color;
	private final Integer size;

	ColorAndSizeChange(String s) {
		final Matcher2 matcherColor = colorPattern.matcher(s);
		if (matcherColor.find()) {
			final String s1 = matcherColor.group(1);
			color = HColorSet.instance().getColorOrWhite(s1);
		} else {
			color = null;
		}
		final Matcher2 matcherSize = sizePattern.matcher(s);
		if (matcherSize.find()) {
			size = Integer.valueOf(matcherSize.group(1));
		} else {
			size = null;
		}
	}

	HColor getColor() {
		return color;
	}

	Integer getSize() {
		return size;
	}

	public FontConfiguration apply(FontConfiguration initial) {
		FontConfiguration result = initial;
		if (color != null) {
			result = result.changeColor(color);
		}
		if (size != null) {
			result = result.changeSize(size);
		}
		return result;
	}

}
