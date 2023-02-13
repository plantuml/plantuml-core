package net.sourceforge.plantuml.regex;

import java.util.Collections;
import java.util.Map;

public class RegexResult {

	private final Map<String, RegexPartialMatch> data;

	public RegexResult(Map<String, RegexPartialMatch> data) {
		this.data = Collections.unmodifiableMap(data);
	}

	@Override
	public String toString() {
		return data.toString();
	}

	public RegexPartialMatch get(String key) {
		return data.get(key);
	}

	public String get(String key, int num) {
		final RegexPartialMatch reg = data.get(key);
		if (reg == null)
			return null;

		return reg.get(num);
	}

	public String getLazzy(String key, int num) {
		for (Map.Entry<String, RegexPartialMatch> ent : data.entrySet()) {
			if (ent.getKey().startsWith(key) == false)
				continue;

			final RegexPartialMatch match = ent.getValue();
			if (num >= match.size())
				continue;

			if (match.get(num) != null)
				return ent.getValue().get(num);

		}
		return null;
	}

	public int size() {
		return data.size();
	}

}
