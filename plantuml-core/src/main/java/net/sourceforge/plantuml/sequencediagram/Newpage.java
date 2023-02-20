package net.sourceforge.plantuml.sequencediagram;

import net.sourceforge.plantuml.klimt.creole.Display;

public class Newpage extends AbstractEvent implements Event {

	private final Display title;

	public Newpage(Display strings) {
		this.title = strings;
	}

	public final Display getTitle() {
		return title;
	}

	public boolean dealWith(Participant someone) {
		return false;
	}
}
