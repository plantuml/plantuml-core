package net.sourceforge.plantuml.sequencediagram;

import net.sourceforge.plantuml.url.Url;

public interface Event {

	boolean dealWith(Participant someone);

	Url getUrl();

	boolean hasUrl();

	boolean isParallel();

	void setY(double y);

}
