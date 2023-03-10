// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.project.core;

import net.sourceforge.plantuml.project.GanttConstraintMode;
import net.sourceforge.plantuml.project.LoadPlanable;
import net.sourceforge.plantuml.project.time.Day;

public class TaskInstant {

	private final Moment task;
	private final TaskAttribute attribute;
	private final int delta;
	private final GanttConstraintMode mode;
	private final LoadPlanable calendar;

	public TaskInstant(Moment task, TaskAttribute attribute) {
		this(task, attribute, 0, GanttConstraintMode.IGNORE_CALENDAR, null);
	}

	private TaskInstant(Moment task, TaskAttribute attribute, int delta, GanttConstraintMode mode,
			LoadPlanable calendar) {
		this.task = task;
		this.attribute = attribute;
		this.delta = delta;
		this.mode = mode;
		this.calendar = calendar;
		if (attribute != TaskAttribute.START && attribute != TaskAttribute.END)
			throw new IllegalArgumentException();

	}

	public TaskInstant withDelta(int newDelta, GanttConstraintMode mode, LoadPlanable calendar) {
		return new TaskInstant(task, attribute, newDelta, mode, calendar);
	}

	private Day manageDelta(Day value) {
		if (delta > 0)
			for (int i = 0; i < delta; i++) {
				if (mode == GanttConstraintMode.DO_NOT_COUNT_CLOSE_DAY) {
					int tmp = 0;
					while (calendar.getLoadAt(value) == 0 && tmp++ < 1000)
						value = value.increment();
				}

				value = value.increment();
			}

		if (delta < 0)
			for (int i = 0; i < -delta; i++)
				value = value.decrement();

		return value;
	}

	public Day getInstantPrecise() {
		if (attribute == TaskAttribute.START)
			return manageDelta(task.getStart());

		if (attribute == TaskAttribute.END)
			return manageDelta(task.getEnd().increment());

		throw new IllegalStateException();
	}

	public Day getInstantTheorical() {
		if (attribute == TaskAttribute.START)
			return manageDelta(task.getStart());

		if (attribute == TaskAttribute.END)
			return manageDelta(task.getEnd());

		throw new IllegalStateException();
	}

	@Override
	public String toString() {
		return attribute.toString() + " of " + task;
	}

	public final Moment getMoment() {
		return task;
	}

	public final boolean isTask() {
		return task instanceof AbstractTask;
	}

	public final TaskAttribute getAttribute() {
		return attribute;
	}

	public boolean sameRowAs(TaskInstant dest) {
		if (this.isTask() && dest.isTask()) {
			final AbstractTask t1 = (AbstractTask) this.getMoment();
			final AbstractTask t2 = (AbstractTask) dest.getMoment();
			if (t1 == t2.getRow() || t2 == t1.getRow())
				return true;

		}
		return false;
	}

}
