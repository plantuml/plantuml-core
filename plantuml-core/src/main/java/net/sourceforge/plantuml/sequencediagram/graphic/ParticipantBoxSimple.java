package net.sourceforge.plantuml.sequencediagram.graphic;

import java.util.Collection;
import java.util.Collections;

import net.sourceforge.plantuml.klimt.font.StringBounder;

public class ParticipantBoxSimple implements Pushable {

	private double pos = 0;
	private final String name;

	public ParticipantBoxSimple(double pos) {
		this(pos, null);
	}

	public ParticipantBoxSimple(double pos, String name) {
		this.pos = pos;
		this.name = name;
	}

	@Override
	public String toString() {
		return name == null ? super.toString() : name;
	}

	public double getCenterX(StringBounder stringBounder) {
		return pos;
	}

	public void pushToLeft(double deltaX) {
		pos += deltaX;
	}

	public double getPreferredWidth(StringBounder stringBounder) {
		return 0;
	}

	public Collection<Segment> getDelays(StringBounder stringBounder) {
		return Collections.emptyList();
	}

}
