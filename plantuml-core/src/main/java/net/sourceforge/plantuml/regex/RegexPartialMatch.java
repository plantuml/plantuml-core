package net.sourceforge.plantuml.regex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RegexPartialMatch implements Iterable<String> {

	private final List<String> data = new ArrayList<>();
	
	public RegexPartialMatch(String name) {
		
	}

	public void add(String group) {
		data.add(group);
	}

	public int size() {
		return data.size();
	}

	public String get(int i) {
		return data.get(i);
	}

	public Iterator<String> iterator() {
		return Collections.unmodifiableCollection(data).iterator();
	}

	@Override
	public String toString() {
		return "{" + data + "}";
	}

}
