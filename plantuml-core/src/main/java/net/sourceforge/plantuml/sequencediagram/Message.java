package net.sourceforge.plantuml.sequencediagram;

import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.skin.ArrowConfiguration;
import net.sourceforge.plantuml.style.StyleBuilder;

public final class Message extends AbstractMessage {

	final private Participant p1;
	final private Participant p2;

	public Message(StyleBuilder styleBuilder, Participant p1, Participant p2, Display label,
			ArrowConfiguration arrowConfiguration, String messageNumber) {
		super(styleBuilder, label, arrowConfiguration, messageNumber);
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public String toString() {
		return super.toString() + " " + p1 + "->" + p2 + " " + getLabel();
	}

	@Override
	public Participant getParticipant1() {
		return p1;
	}

	@Override
	public Participant getParticipant2() {
		return p2;
	}

	public boolean dealWith(Participant someone) {
		return someone == p1 || someone == p2;
	}

	@Override
	public boolean compatibleForCreate(Participant p) {
		return p1 != p && p2 == p;
	}

	public boolean isSelfMessage() {
		return p1 == p2;
	}

	private List<Participant> multicast = Collections.emptyList();

	public void setMulticast(List<Participant> multicast) {
		this.multicast = multicast;
	}

	public List<Participant> getMulticast() {
		return multicast;
	}

}
