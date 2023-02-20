package net.sourceforge.plantuml.sequencediagram.graphic;

import java.util.Collection;

import net.sourceforge.plantuml.klimt.font.StringBounder;

public interface Pushable {

	double getPreferredWidth(StringBounder stringBounder);

	double getCenterX(StringBounder stringBounder);

	void pushToLeft(double deltaX);

	public Collection<Segment> getDelays(StringBounder stringBounder);

}
