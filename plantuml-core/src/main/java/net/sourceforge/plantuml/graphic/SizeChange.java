package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

class SizeChange implements FontChange {

	static private final Pattern2 sizePattern = MyPattern.cmpile(Splitter.fontSizePattern2);

	private final Integer size;

	SizeChange(String s) {
		final Matcher2 matcherSize = sizePattern.matcher(s);
		if (matcherSize.find() == false) {
			throw new IllegalArgumentException();
		}
		size = Integer.valueOf(matcherSize.group(1));
	}

	Integer getSize() {
		return size;
	}

	public FontConfiguration apply(FontConfiguration initial) {
		return initial.changeSize(size);
	}

}
