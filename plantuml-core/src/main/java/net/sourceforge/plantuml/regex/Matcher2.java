package net.sourceforge.plantuml.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.utils.Log;

public class Matcher2 {

	private final static boolean INSTRUMENT = false;
	private final Matcher matcher;
	private final String id;

	private Matcher2(Matcher matcher, String id) {
		this.matcher = matcher;
		this.id = id;
	}

	public static Matcher2 build(Pattern pattern, CharSequence input) {
		final long now = System.currentTimeMillis();
		final String id = pattern.pattern();
		try {
			final Matcher matcher2 = pattern.matcher(input);
			return new Matcher2(matcher2, id);
		} finally {
			if (INSTRUMENT) {
				addTime(id, System.currentTimeMillis() - now);
			}
		}
	}

	public boolean matches() {
		final long now = System.currentTimeMillis();
		try {
			return matcher.matches();
		} finally {
			addTime(System.currentTimeMillis() - now);
		}
	}

	private void addTime(long duration) {
		if (INSTRUMENT == false) {
			return;
		}
		addTime(id, duration);
	}

	private static final Map<String, Long> durations = new HashMap<String, Long>();
	private static long printed;

	private static synchronized void addTime(String id, long duration) {
		Long total = durations.get(id);
		if (total == null) {
			total = 0L;
		}
		total += duration;
		durations.put(id, total);
		final String longest = getLongest();
		if (longest == null) {
			return;
		}
		if (durations.get(longest) > printed) {
			Log.info("---------- Regex " + longest + " " + durations.get(longest) + "ms (" + durations.size() + ")");
			printed = durations.get(longest);
		}

	}

	private static String getLongest() {
		long max = 0;
		String result = null;
		for (Map.Entry<String, Long> ent : durations.entrySet()) {
			if (ent.getValue() > max) {
				max = ent.getValue();
				result = ent.getKey();
			}
		}
		return result;
	}

	public String group(int n) {
		final long now = System.currentTimeMillis();
		try {
			return matcher.group(n);
		} finally {
			addTime(System.currentTimeMillis() - now);
		}
	}

	public String group() {
		final long now = System.currentTimeMillis();
		try {
			return matcher.group();
		} finally {
			addTime(System.currentTimeMillis() - now);
		}
	}

	public int groupCount() {
		final long now = System.currentTimeMillis();
		try {
			return matcher.groupCount();
		} finally {
			addTime(System.currentTimeMillis() - now);
		}
	}

	public boolean find() {
		final long now = System.currentTimeMillis();
		try {
			return matcher.find();
		} finally {
			addTime(System.currentTimeMillis() - now);
		}
	}

	public int end() {
		final long now = System.currentTimeMillis();
		try {
			return matcher.end();
		} finally {
			addTime(System.currentTimeMillis() - now);
		}
	}

	public int start() {
		final long now = System.currentTimeMillis();
		try {
			return matcher.start();
		} finally {
			addTime(System.currentTimeMillis() - now);
		}
	}

}
