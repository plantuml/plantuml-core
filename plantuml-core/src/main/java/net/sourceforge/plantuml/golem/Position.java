package net.sourceforge.plantuml.golem;

public class Position {

	private final int xmin;
	private final int ymin;
	private final int xmax;
	private final int ymax;

	public Position(int xmin, int ymin, int xmax, int ymax) {
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
	}

	@Override
	public boolean equals(Object o) {
		final Position other = (Position) o;
		return this.xmin == other.xmin && this.xmax == other.xmax && this.ymin == other.ymin && this.ymax == other.ymax;
	}

	@Override
	public int hashCode() {
		return xmin + (ymin << 8) + (xmax << 16) + (ymax << 24);
	}

	@Override
	public String toString() {
		return "(" + xmin + "," + ymin + ")-(" + xmax + "," + ymax + ")";
	}

	public Position move(TileGeometry position, int sizeMove) {
		if (position == null || position == TileGeometry.CENTER) {
			throw new IllegalArgumentException();
		}
		switch (position) {
		case NORTH:
			return new Position(xmin, ymin - sizeMove, xmax, ymax - sizeMove);
		case SOUTH:
			return new Position(xmin, ymin + sizeMove, xmax, ymax + sizeMove);
		case WEST:
			return new Position(xmin - sizeMove, ymin, xmax - sizeMove, ymax);
		case EAST:
			return new Position(xmin + sizeMove, ymin, xmax + sizeMove, ymax);
		default:
			throw new IllegalStateException();
		}
	}

	public int getXmin() {
		return xmin;
	}

	public int getXmax() {
		return xmax;
	}

	public int getYmin() {
		return ymin;
	}

	public int getYmax() {
		return ymax;
	}

	public int getCenterX() {
		if ((xmin + xmax + 1) % 2 != 0) {
			throw new IllegalStateException();
		}
		return (xmin + xmax + 1) / 2;
	}

	public int getCenterY() {
		if ((ymin + ymax + 1) % 2 != 0) {
			throw new IllegalStateException();
		}
		return (ymin + ymax + 1) / 2;
	}
}
