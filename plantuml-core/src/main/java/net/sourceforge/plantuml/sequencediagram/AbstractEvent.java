package net.sourceforge.plantuml.sequencediagram;

import net.sourceforge.plantuml.url.Url;

public abstract class AbstractEvent implements Event {

	public boolean isParallel() {
		return false;
	}

	public Url getUrl() {
		return null;
	}

	public boolean hasUrl() {
		return false;
	}

	public final void setY(double y) {

	}

}
