package net.sourceforge.plantuml.salt.element;

public class Segment {

	private final int row;
	private final int col;

	public Segment(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public int hashCode() {
		return row * 47 + col;
	}

	@Override
	public boolean equals(Object obj) {
		final Segment this2 = (Segment) obj;
		return row == this2.row && col == this2.col;
	}

	public final int getRow() {
		return row;
	}

	public final int getCol() {
		return col;
	}

}
