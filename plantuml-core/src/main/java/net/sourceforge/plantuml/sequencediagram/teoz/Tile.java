package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.sequencediagram.Event;

public interface Tile {

	public double getPreferredHeight();

	public void callbackY(TimeHook y);

	public YGauge getYGauge();

	public void addConstraints();

	public Real getMinX();

	public Real getMaxX();

	public double getMiddleX();

	public Event getEvent();

	public double getContactPointRelative();

	public double getZZZ();

	public boolean matchAnchor(String anchor);

}
