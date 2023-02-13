package net.sourceforge.plantuml;

public enum PaddingParam {
	PARTICIPANT, BOX;

	public String getSkinName() {
		return StringUtils.goLowerCase(name()) + "Padding";
	}

}
