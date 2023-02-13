package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.sequencediagram.AbstractEvent;
import net.sourceforge.plantuml.sequencediagram.Event;
import net.sourceforge.plantuml.sequencediagram.Participant;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class EmptyTile extends AbstractTile implements Tile {

	private final double height;
	private final Tile position;
	private final YGauge yGauge;

	public EmptyTile(double height, Tile position, YGauge currentY) {
		super(((AbstractTile) position).getStringBounder(), currentY);
		if (YGauge.USE_ME)
			throw new UnsupportedOperationException();
		this.height = height;
		this.position = position;
		this.yGauge = YGauge.create(currentY.getMax(), getPreferredHeight());
	}

	@Override
	public YGauge getYGauge() {
		return yGauge;
	}

	public void drawU(UGraphic ug) {
	}

	@Override
	public double getPreferredHeight() {
		return height;
	}

	public void addConstraints() {
	}

	public Real getMinX() {
		return position.getMinX();
	}

	public Real getMaxX() {
		return position.getMaxX();
	}

	public Event getEvent() {
		return new AbstractEvent() {
			public boolean dealWith(Participant someone) {
				return false;
			}
		};
	}

}
