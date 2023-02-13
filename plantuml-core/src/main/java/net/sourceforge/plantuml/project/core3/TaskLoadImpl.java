package net.sourceforge.plantuml.project.core3;

public class TaskLoadImpl implements TaskLoad {

	private long start;
	private long end;
	private final Histogram load;

	public TaskLoadImpl(Histogram load) {
		this.load = load;
	}

	public final long getStart() {
		return start;
	}

	public final void setStart(long start) {
		this.start = start;
	}

	public final long getEnd() {
		return end;
	}

	public final void setEnd(long end) {
		this.end = end;
	}

	public final Histogram getLoad() {
		return load;
	}

}
