package net.sourceforge.plantuml.sequencediagram;

import net.sourceforge.plantuml.klimt.Fashion;

public class LifeEvent extends AbstractEvent implements Event {

	private final Participant p;
	private final LifeEventType type;
	private final Fashion backcolor;

	public LifeEvent(Participant p, LifeEventType type, Fashion backcolor) {
		this.p = p;
		this.type = type;
		this.backcolor = backcolor;
	}

	@Override
	public String toString() {
		return "LifeEvent:" + p + " " + type;
	}

	public Participant getParticipant() {
		return p;
	}

	public LifeEventType getType() {
		return type;
	}

	public Fashion getSpecificColors() {
		return backcolor;
	}

	public boolean dealWith(Participant someone) {
		return this.p == someone;
	}

	public boolean isActivate() {
		return type == LifeEventType.ACTIVATE;
	}

	public boolean isDeactivateOrDestroy() {
		return type == LifeEventType.DEACTIVATE || type == LifeEventType.DESTROY;
	}

	public boolean isDeactivate() {
		return type == LifeEventType.DEACTIVATE;
	}

	public boolean isDestroy(Participant p) {
		return this.p == p && type == LifeEventType.DESTROY;
	}

	private AbstractMessage message;

	public void setMessage(AbstractMessage message) {
		this.message = message;
	}

	public AbstractMessage getMessage() {
		return message;
	}

}
