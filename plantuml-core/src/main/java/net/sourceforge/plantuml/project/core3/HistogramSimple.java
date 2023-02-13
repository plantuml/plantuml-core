package net.sourceforge.plantuml.project.core3;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class HistogramSimple implements Histogram {

	private final Map<Long, Long> events = new TreeMap<Long, Long>();

	public long getNext(long moment) {
		for (long e : events.keySet()) {
			if (e > moment) {
				return e;
			}
		}
		return TimeLine.MAX_TIME;
	}

	public long getPrevious(long moment) {
		long last = -TimeLine.MAX_TIME;
		for (long e : events.keySet()) {
			if (e >= moment) {
				return last;
			}
			last = e;
		}
		return last;
	}

	public void put(long event, long value) {
		this.events.put(event, value);
	}

	@Override
	public String toString() {
		return events.toString();
	}

	public long getValueAt(long moment) {
		long last = 0;
		for (Entry<Long, Long> ent : events.entrySet()) {
			if (ent.getKey() > moment) {
				return last;
			}
			last = ent.getValue();
		}
		return last;
	}

}
