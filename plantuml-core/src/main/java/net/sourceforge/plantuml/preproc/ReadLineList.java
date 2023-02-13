package net.sourceforge.plantuml.preproc;

import java.util.Iterator;
import java.util.List;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.LineLocation;

public class ReadLineList implements ReadLine {

	private final Iterator<String> iterator;
	private final LineLocation location;

	public ReadLineList(List<String> definition, LineLocation location) {
		this.iterator = definition.iterator();
		this.location = location;
	}

	public void close() {
	}

	public StringLocated readLine() {
		if (iterator.hasNext() == false) {
			return null;
		}
		return new StringLocated(iterator.next(), location);
	}

}
