package net.sourceforge.plantuml.klimt.color;

import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class ColorParser {

	private static final String COLOR_REGEXP = "#\\w+[-\\\\|/]?\\w+";

	private static final String PART2 = "#(?:\\w+[-\\\\|/]?\\w+;)?(?:(?:text|back|header|line|line\\.dashed|line\\.dotted|line\\.bold|shadowing)(?::\\w+[-\\\\|/]?\\w+)?(?:;|(?![\\w;:.])))+";
	private static final String COLORS_REGEXP = "(?:" + PART2 + ")|(?:" + COLOR_REGEXP + ")";

	private final RegexLeaf regex;
	private final String name;
	private final ColorType mainType;

	private ColorParser(String name, RegexLeaf regex, ColorType mainType) {
		this.regex = regex;
		this.name = name;
		this.mainType = mainType;
	}

	public Colors getColor(RegexResult arg, HColorSet set) throws NoSuchColorException {
		if (mainType == null) {
			throw new IllegalStateException();
		}
		final String data = arg.getLazzy(name, 0);
		if (data == null) {
			return Colors.empty();
		}
		return new Colors(data, set, mainType);
	}

	// New Parsers
	public static ColorParser simpleColor(ColorType mainType) {
		return simpleColor(mainType, "COLOR");
	}

	public static ColorParser simpleColor(ColorType mainType, String id) {
		return new ColorParser(id, new RegexLeaf(id, "(" + COLORS_REGEXP + ")?"), mainType);
	}

	public static ColorParser mandatoryColor(ColorType mainType) {
		return new ColorParser("COLOR", new RegexLeaf("COLOR", "(" + COLORS_REGEXP + ")"), mainType);
	}

	public static ColorParser simpleColor(String optPrefix, ColorType mainType) {
		return new ColorParser("COLOR", new RegexLeaf("COLOR", "(?:" + optPrefix + " (" + COLORS_REGEXP + "))?"),
				mainType);
	}

	// Old Parsers

	public static RegexLeaf exp1() {
		return simpleColor(null).regex;
	}

	public static RegexLeaf exp2() {
		return new RegexLeaf("BACKCOLOR", "(" + COLOR_REGEXP + ")?");
	}

	public static RegexLeaf exp3() {
		return new RegexLeaf("BACKCOLOR2", "(" + COLOR_REGEXP + ")?");
	}

	public static RegexLeaf exp4() {
		return new RegexLeaf("COLOR", "(?:(" + COLOR_REGEXP + "):)?");
	}

	public static RegexLeaf exp6() {
		return new RegexLeaf("COLOR", "(?:(" + COLOR_REGEXP + ")\\|)?");
	}

	public static RegexLeaf exp7() {
		return new RegexLeaf("COLOR", "(?:(" + COLOR_REGEXP + "))?");
	}

	public RegexLeaf getRegex() {
		return regex;
	}

}
