package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.sequencediagram.AbstractMessage;
import net.sourceforge.plantuml.sequencediagram.Event;

public abstract class AbstractTile extends CommonTile implements Tile {

	public AbstractTile(StringBounder stringBounder, YGauge currentY) {
		super(stringBounder);
		if (YGauge.USE_ME)
			System.err.println("CREATING " + getClass());
	}

	public AbstractTile(StringBounder stringBounder) {
		super(stringBounder);
	}

	@Override
	public YGauge getYGauge() {
		throw new UnsupportedOperationException(getClass().toString());
	}

	public double getContactPointRelative() {
		return -1;
	}

	final public double getZZZ() {
		final double result = getPreferredHeight() - getContactPointRelative();
		assert result >= 0;
		return result;
	}

	public boolean matchAnchor(String anchor) {
		final Event event = this.getEvent();
		if (event instanceof AbstractMessage) {
			final AbstractMessage msg = (AbstractMessage) event;
			if (anchor.equals(msg.getAnchor()))
				return true;

		}
		return false;
	}

}
