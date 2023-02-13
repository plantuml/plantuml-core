package net.sourceforge.plantuml.nwdiag.next;

public class NwArray {

	private final NServerDraw data[][];

	public NwArray(int lines, int cols) {
		this.data = new NServerDraw[lines][cols];
	}

	@Override
	public String toString() {
		return "lines=" + getNbLines() + " cols=" + getNbCols();
	}

	public int getNbLines() {
		return data.length;
	}

	public int getNbCols() {
		return data[0].length;
	}

	public NServerDraw get(int i, int j) {
		return data[i][j];
	}

	public NServerDraw[] getLine(int i) {
		return data[i];
	}

	public void set(int i, int j, NServerDraw value) {
		data[i][j] = value;
	}

}
