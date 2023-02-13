package net.sourceforge.plantuml.project.core;

import net.sourceforge.plantuml.project.time.Day;

public class MomentImpl implements Moment {

	private final Day start;
	private final Day end;

	public MomentImpl(Day start, Day end) {
		this.start = start;
		this.end = end;
	}

	public Day getStart() {
		return start;
	}

	public Day getEnd() {
		return end;
	}

}
