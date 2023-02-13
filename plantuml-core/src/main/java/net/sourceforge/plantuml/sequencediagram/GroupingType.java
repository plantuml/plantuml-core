package net.sourceforge.plantuml.sequencediagram;

public enum GroupingType {
	START, ELSE, END;
	public static GroupingType getType(String s) {
		if (s.equalsIgnoreCase("opt")) {
			return GroupingType.START;
		} else if (s.equalsIgnoreCase("alt")) {
			return GroupingType.START;
		} else if (s.equalsIgnoreCase("loop")) {
			return GroupingType.START;
		} else if (s.equalsIgnoreCase("par")) {
			return GroupingType.START;
		} else if (s.equalsIgnoreCase("par2")) {
			return GroupingType.START;
		} else if (s.equalsIgnoreCase("break")) {
			return GroupingType.START;
		} else if (s.equalsIgnoreCase("group")) {
			return GroupingType.START;
		} else if (s.equalsIgnoreCase("critical")) {
			return GroupingType.START;
		} else if (s.equalsIgnoreCase("also")) {
			return GroupingType.ELSE;
		} else if (s.equalsIgnoreCase("else")) {
			return GroupingType.ELSE;
		} else if (s.equalsIgnoreCase("end")) {
			return GroupingType.END;
		}

		throw new IllegalArgumentException();
	}
}
