package com.plantuml.api.cheerpj;

public class Utils {

	public static String cleanText(String text) {
		if (text.endsWith("\n") == false)
			text = text + "\n";
		if (text.endsWith("@start") == false)
			text = "@startuml\n" + text + "@enduml\n";

		return text;
	}

}