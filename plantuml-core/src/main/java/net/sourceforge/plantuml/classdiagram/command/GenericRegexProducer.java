// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.classdiagram.command;

public class GenericRegexProducer {

	public final static String PATTERN = "[^\\<\\>/]" + getGenericRegex(4);

	// \<[^\<\>]([^\<\>]|\<\>)*\>
	static final private String part1 = "(?:[^\\<\\>/]|\\<";
	static final private String part2 = "\\>)*";

	static String getGenericRegex(int level) {
		if (level < 0) {
			throw new IllegalArgumentException();
		}
		if (level == 0) {
			return part1 + part2;
		}
		return part1 + getGenericRegex(level - 1) + part2;
	}

}
