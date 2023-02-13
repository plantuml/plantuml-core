package net.sourceforge.plantuml.salt;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class DataSourceImpl implements DataSource {

	private int i = 0;
	private final List<Terminated<String>> data = new ArrayList<Terminated<String>>();

	public DataSourceImpl(List<String> data) {
		final Pattern2 p = MyPattern.cmpile("\\{(?:[-+^#!*/]|S-|SI|S)?");

		for (String s : data) {
			final StringTokenizer st = new StringTokenizer(s, "|}", true);
			while (st.hasMoreTokens()) {
				final String token = StringUtils.trin(st.nextToken());
				if (token.equals("|")) {
					continue;
				}
				final Terminator terminator = st.hasMoreTokens() ? Terminator.NEWCOL : Terminator.NEWLINE;
				final Matcher2 m = p.matcher(token);
				final boolean found = m.find();
				if (found == false) {
					addInternal(token, terminator);
					continue;
				}
				int lastStart = 0;
				int end = 0;
				do {
					final int start = m.start();
					if (start > lastStart) {
						addInternal(token.substring(lastStart, start), Terminator.NEWCOL);
					}
					end = m.end();
					final Terminator t = end == token.length() ? terminator : Terminator.NEWCOL;
					addInternal(token.substring(start, end), t);
					lastStart = end;
				} while (m.find());
				if (end < token.length()) {
					addInternal(token.substring(end), terminator);
				}
			}
		}
	}


	private void addInternal(String s, Terminator t) {
		s = StringUtils.trin(s);
		if (s.length() > 0) {
			data.add(new Terminated<>(s, t));
		}
	}

	public Terminated<String> peek(int nb) {
		return data.get(i + nb);
	}

	public boolean hasNext() {
		return i < data.size();
	}

	public Terminated<String> next() {
		final Terminated<String> result = data.get(i);
		i++;
		return result;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public String toString() {
		return super.toString() + " " + (hasNext() ? peek(0) : "$$$");
	}

}
