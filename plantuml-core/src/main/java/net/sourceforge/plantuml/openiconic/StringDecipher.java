package net.sourceforge.plantuml.openiconic;

public class StringDecipher {

	public static String decipher(String path) {
		path = path.trim();
		path = path.replace(',', ' ');
		path = path.replaceAll("\\s+", " ");
		path = path.replaceAll("([^e\\s])-", "$1 -");
		path = path.replaceAll("([a-df-zA-Z])(\\S)", "$1 $2");
		path = path.replaceAll("(\\S)([a-df-zA-Z])", "$1 $2");
		path = path.replaceAll("([a-df-zA-Z])(\\S)", "$1 $2");
		while (path.matches(".*\\.\\d+\\..*")) {
			path = path.replaceAll("(\\.\\d+)\\.", "$1 .");
		}
		return path;
	}

}
