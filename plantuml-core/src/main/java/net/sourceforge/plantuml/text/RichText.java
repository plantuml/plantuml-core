package net.sourceforge.plantuml.text;

import net.sourceforge.plantuml.StringUtils;

public class RichText {

	public static boolean isRich(String text) {
		if (text.indexOf(StringUtils.BOLD_START) != -1)
			return true;

		if (text.indexOf(StringUtils.BOLD_END) != -1)
			return true;

		return false;
	}

}
