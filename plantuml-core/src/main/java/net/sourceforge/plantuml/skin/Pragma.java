package net.sourceforge.plantuml.skin;

import java.util.LinkedHashMap;
import java.util.Map;

public class Pragma {

	private final Map<String, String> values = new LinkedHashMap<String, String>();

	public void define(String name, String value) {
		values.put(name, value);
	}

	public boolean isDefine(String name) {
		return values.containsKey(name);
	}

	public void undefine(String name) {
		values.remove(name);
	}

	public String getValue(String name) {
		return values.get(name);
	}

	public boolean horizontalLineBetweenDifferentPackageAllowed() {
		return isDefine("horizontallinebetweendifferentpackageallowed");
	}

	public boolean backToLegacyPackage() {
		return isDefine("backtolegacypackage");
	}

	public boolean useNewPackage() {
		return isDefine("usenewpackage");
	}

	private boolean isTrue(final String s) {
		return "true".equalsIgnoreCase(s) || "on".equalsIgnoreCase(s);
	}

	public boolean useVerticalIf() {
		return isTrue(getValue("useverticalif"));
	}

	public boolean useTeozLayout() {
		return isTrue(getValue("teoz"));
	}

	public boolean useKermor() {
		return isTrue(getValue("kermor"));
	}

}
