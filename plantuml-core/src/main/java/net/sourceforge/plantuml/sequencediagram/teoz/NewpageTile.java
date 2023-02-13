package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.sequencediagram.Event;
import net.sourceforge.plantuml.sequencediagram.Newpage;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class NewpageTile extends AbstractTile {

	private final Newpage newpage;
	private final TileArguments tileArguments;

	@Override
	public double getContactPointRelative() {
		return 0;
	}

	public NewpageTile(Newpage newpage, TileArguments tileArguments, YGauge currentY) {
		super(tileArguments.getStringBounder(), currentY);
		this.newpage = newpage;
		this.tileArguments = tileArguments;
	}

	public void drawU(UGraphic ug) {
	}

	public double getPreferredHeight() {
		return 0;
	}

	public void addConstraints() {
	}

	public Real getMinX() {
		return tileArguments.getXOrigin();
	}

	public Real getMaxX() {
		return tileArguments.getXOrigin();
	}

	public Event getEvent() {
		return newpage;
	}

}
