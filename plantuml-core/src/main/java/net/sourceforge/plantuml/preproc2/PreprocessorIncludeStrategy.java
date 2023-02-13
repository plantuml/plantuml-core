package net.sourceforge.plantuml.preproc2;

public enum PreprocessorIncludeStrategy {
	ONCE, MANY, DEFAULT;

	public static PreprocessorIncludeStrategy fromString(String group) {
		if ("once".equalsIgnoreCase(group)) {
			return ONCE;
		}
		return MANY;
	}

}
