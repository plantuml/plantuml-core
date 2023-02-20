package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.StringUtils;

public enum PaddingParam {
	PARTICIPANT, BOX;

	public String getSkinName() {
		return StringUtils.goLowerCase(name()) + "Padding";
	}

}
