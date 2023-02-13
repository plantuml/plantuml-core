package net.sourceforge.plantuml.sequencediagram.teoz;

public class TileUtils {

	public static Tile withMargin(Tile tile, double x1, double x2, double y1, double y2) {
		return new TileMarged(tile, x1, x2, y1, y2);
	}

}
