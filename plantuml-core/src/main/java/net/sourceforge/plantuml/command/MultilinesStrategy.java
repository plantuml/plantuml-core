package net.sourceforge.plantuml.command;

import java.util.Iterator;
import java.util.List;

import net.sourceforge.plantuml.text.StringLocated;

public enum MultilinesStrategy {
	REMOVE_STARTING_QUOTE, KEEP_STARTING_QUOTE;

	public void cleanList(List<StringLocated> lines) {
		if (this == REMOVE_STARTING_QUOTE)
			filterQuote(lines);

	}

	private void filterQuote(List<StringLocated> lines) {
		for (final Iterator<StringLocated> it = lines.iterator(); it.hasNext();) {
			final StringLocated s = it.next();
			if (hasStartingQuote(s))
				it.remove();

		}
	}

	private boolean hasStartingQuote(StringLocated s) {
		return s.getTrimmed().getString().startsWith("\'");
	}
}
