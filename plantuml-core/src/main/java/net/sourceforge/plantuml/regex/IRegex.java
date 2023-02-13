package net.sourceforge.plantuml.regex;

import java.util.Iterator;
import java.util.Map;

import net.sourceforge.plantuml.text.StringLocated;

public interface IRegex {

	public String getPattern();

	public int count();

	public Map<String, RegexPartialMatch> createPartialMatch(Iterator<String> it);

	public boolean match(StringLocated full);

	public RegexResult matcher(String full);
}
