package net.sourceforge.plantuml.creole.command;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

abstract class CommandCreoleCache implements Command {

	private static final Map<String, Pattern2> cache = new ConcurrentHashMap<>();

	protected final Pattern2 mypattern;

	protected CommandCreoleCache(String p) {
		Pattern2 result = cache.get(p);
		if (result == null) {
			result = MyPattern.cmpile(p);
			cache.put(p, result);
		}
		this.mypattern = result;
	}

}
