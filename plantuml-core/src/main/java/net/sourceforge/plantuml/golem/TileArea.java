package net.sourceforge.plantuml.golem;

public class TileArea {

	private final Tile tile;
	private final TileGeometry position;

	TileArea(Tile tile, TileGeometry position) {
		this.tile = tile;
		this.position = position;
	}

	public Tile getTile() {
		return tile;
	}

	public TileGeometry getGeometry() {
		return position;
	}
}
