package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.klimt.geom.XPoint2D;

public class Oscillator {

	private int n = 3;
	private int i = 0;
	private char seg = 'A';

	private int x = -1;
	private int y = -1;

	public XPoint2D nextPosition() {
		assert n % 2 == 1;
		final int halfN = (n - 1) / 2;
		final XPoint2D result = new XPoint2D(x, y);
		i++;
		if (seg == 'A') {
			x++;
			if (x > halfN) {
				seg = 'B';
				x = halfN;
				y = -halfN + 1;
			}
		} else if (seg == 'B') {
			y++;
			if (y > halfN) {
				seg = 'C';
				x = halfN - 1;
				y = halfN;
			}
		} else if (seg == 'C') {
			x--;
			if (x < -halfN) {
				seg = 'D';
				x = -halfN;
				y = halfN - 1;
			}
		} else if (seg == 'D') {
			y--;
			if (y == -halfN) {
				n += 2;
				i = 0;
				x = -((n - 1) / 2);
				y = x;
				seg = 'A';
			}
		} else {
			throw new UnsupportedOperationException();
		}
		return result;
	}
}
