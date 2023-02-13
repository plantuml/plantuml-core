package net.sourceforge.plantuml.sequencediagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Notes extends AbstractEvent implements Event, Iterable<Note> {

	private final List<Note> notes = new ArrayList<>();

	public Notes(Note n1, Note n2) {
		notes.add(n1);
		notes.add(n2);
	}

	public void add(Note n) {
		notes.add(n);
	}

	public boolean dealWith(Participant someone) {
		for (Note n : notes) {
			if (n.dealWith(someone)) {
				return true;
			}
		}
		return false;
	}

	public Iterator<Note> iterator() {
		return notes.iterator();
	}

	public Note get(int i) {
		return notes.get(i);
	}

	public List<Note> asList() {
		return Collections.unmodifiableList(notes);
	}
}
