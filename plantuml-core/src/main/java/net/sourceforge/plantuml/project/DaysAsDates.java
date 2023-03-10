// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.project;

import java.util.Iterator;

import net.sourceforge.plantuml.project.time.Day;

public class DaysAsDates implements Iterable<Day> {

	private final Day date1;
	private final Day date2;

	public DaysAsDates(Day date1, Day date2) {
		this.date1 = date1;
		this.date2 = date2;
	}

	public DaysAsDates(GanttDiagram gantt, Day date1, int count) {
		this.date1 = date1;
		Day tmp = date1;
		while (count > 0) {
			if (gantt.isOpen(tmp)) {
				count--;
			}
			tmp = tmp.increment();
		}
		this.date2 = tmp;
	}

	class MyIterator implements Iterator<Day> {

		private Day current;

		public MyIterator(Day current) {
			this.current = current;
		}

		public boolean hasNext() {
			return current.compareTo(date2) <= 0;
		}

		public Day next() {
			final Day result = current;
			current = current.increment();
			return result;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public Iterator<Day> iterator() {
		return new MyIterator(date1);
	}

}
