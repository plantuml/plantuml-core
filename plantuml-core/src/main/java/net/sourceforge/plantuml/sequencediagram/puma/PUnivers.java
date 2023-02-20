package net.sourceforge.plantuml.sequencediagram.puma;

import java.util.ArrayList;
import java.util.Collection;

public class PUnivers {

	private final Collection<PSegment> all = new ArrayList<>();
	private final Collection<FixedLink> links = new ArrayList<>();

	public PSegment createPSegment(double minsize) {
		final PSegment result = new PSegment(minsize);
		all.add(result);
		return result;
	}

	public void addFixedLink(PSegment segment1, double position1, PSegment segment2, double position2) {
		final FixedLink link = new FixedLink(new SegmentPosition(segment1, position1),
				new SegmentPosition(segment2, position2));
		links.add(link);

	}

	public void solve() {
		boolean changed = false;
		do {
			changed = false;
			for (FixedLink link : links) {
				if (link.pushIfNeed()) {
					changed = true;
				}
			}
		} while (changed);

	}
}
