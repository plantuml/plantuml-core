package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.sequencediagram.Event;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class TileMarged extends AbstractTile implements Tile {

	private final Tile tile;
	private final double x1;
	private final double x2;
	private final double y1;
	private final double y2;

	public TileMarged(Tile tile, double x1, double x2, double y1, double y2) {
		super(((AbstractTile) tile).getStringBounder());
		this.tile = tile;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	public void drawU(UGraphic ug) {
		((UDrawable) tile).drawU(ug.apply(new UTranslate(x1, y1)));

	}

	public double getPreferredHeight() {
		return tile.getPreferredHeight() + y1 + y2;
	}

	public void addConstraints() {
		tile.addConstraints();
	}

	public Real getMinX() {
		return tile.getMinX();
	}

	public Real getMaxX() {
		return tile.getMaxX().addFixed(x1 + x2);
	}

	public Event getEvent() {
		return tile.getEvent();
	}

}
