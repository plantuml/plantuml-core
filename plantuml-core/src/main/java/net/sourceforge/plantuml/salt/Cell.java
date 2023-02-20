package net.sourceforge.plantuml.salt;

public class Cell {

	private int minRow;
	private int maxRow;
	private int minCol;
	private int maxCol;

	public Cell(int row, int col) {
		minRow = row;
		maxRow = row;
		minCol = col;
		maxCol = col;
	}

	public void mergeLeft() {
		maxCol++;
	}

	public int getMinRow() {
		return minRow;
	}

	public int getMaxRow() {
		return maxRow;
	}

	public int getMinCol() {
		return minCol;
	}

	public int getMaxCol() {
		return maxCol;
	}

	public int getNbRows() {
		return maxRow - minRow + 1;
	}

	public int getNbCols() {
		return maxCol - minCol + 1;
	}

	@Override
	public String toString() {
		return "(" + minRow + "," + minCol + ")-(" + maxRow + "," + maxCol + ")";
	}

}
